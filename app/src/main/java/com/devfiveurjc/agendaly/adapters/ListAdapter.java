package com.devfiveurjc.agendaly.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDSetting;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.models.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final List<Task> tasks;
    private final LayoutInflater mInflater;
    private final ListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    public ListAdapter(List<Task> tasks, Context context, ListAdapter.OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.tasks = tasks;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = this.mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.bindData(tasks.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox check;
        TextView title, description, day, date;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.check = itemView.findViewById(R.id.taskCheckBox);
            this.title = itemView.findViewById(R.id.titleTextView);
            this.description = itemView.findViewById(R.id.descriptionTextView);
            this.day = itemView.findViewById(R.id.dayTextView);
            this.date = itemView.findViewById(R.id.dateTextView);
            this.cv = itemView.findViewById(R.id.cv);
        }

        public void bindData(final Task task) {
            this.check.setChecked(task.isCheck());
            String titleText = cutText(task.getTitle());
            this.title.setText(titleText);
            String descriptionText = cutText(task.getDescription());
            this.description.setText(descriptionText);
            Date date = task.getDate();
            String language = CRUDSetting.getSetting().getLanguage();
            Locale locale = new Locale(language);
            String dayText = new SimpleDateFormat("EEEE", locale).format(date);
            this.day.setText(capitalize(dayText));
            String dateText = new SimpleDateFormat("dd/MM/yy", Locale.US).format(date);
            this.date.setText(dateText);
            this.itemView.setOnClickListener(view -> listener.onItemClick(task));
            this.check.setOnCheckedChangeListener((compoundButton, b) -> {
                boolean alternateCheck = !task.isCheck();
                CRUDTask.updateTaskCheck(task, alternateCheck);
                this.check.setChecked(alternateCheck);
            });
        }

    }

    private String cutText(String text) {
        if (text.length() > 25) return text.substring(0, 25).trim() + "...";
        return text;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

}
