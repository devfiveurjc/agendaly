package com.devfiveurjc.agendaly.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devfiveurjc.agendaly.R;
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

        ViewHolder(View itemView) {
            super(itemView);
            this.check = itemView.findViewById(R.id.taskCheckBox);
            this.title = itemView.findViewById(R.id.titleTextView);
            this.description = itemView.findViewById(R.id.descriptionTextView);
            this.date = itemView.findViewById(R.id.dateTextView);
        }

        void bindData(final Task task) {
            this.check.setChecked(task.isChecked());
            this.title.setText(task.getTitle());
            this.description.setText(task.getDescription());
            this.date.setText("day");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(task);
                }
            });
        }

    }

}
