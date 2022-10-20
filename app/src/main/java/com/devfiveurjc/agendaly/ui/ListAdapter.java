package com.devfiveurjc.agendaly.ui;

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
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.model.Task;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final List<Task> tasks;
    private final LayoutInflater mInflater;
    final ListAdapter.OnItemClickListener listener;

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
        @SuppressLint("InflateParams") View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.bindData(tasks.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox check;
        TextView title, description, date;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.check = itemView.findViewById(R.id.taskCheckBox);
            this.title = itemView.findViewById(R.id.titleTextView);
            this.description = itemView.findViewById(R.id.descriptionTextView);
            this.date = itemView.findViewById(R.id.dateTextView);
            this.cv = itemView.findViewById(R.id.cv);
        }

        public void bindData(final Task task) {
            this.check.setChecked(task.isCheck());
            this.title.setText(task.getTitle());
            this.description.setText(task.getDescription());
            // TODO: display week day & hour
            this.date.setText("day");
            this.itemView.setOnClickListener(view -> listener.onItemClick(task));
            this.check.setOnCheckedChangeListener((compoundButton, b) -> {
                boolean alternateCheck = !task.isCheck();
                CRUDTask.updateTaskCheck(task, alternateCheck);
                this.check.setChecked(alternateCheck);
            });
        }

    }

}
