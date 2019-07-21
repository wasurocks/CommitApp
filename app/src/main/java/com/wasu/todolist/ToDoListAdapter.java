package com.wasu.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ToDoListAdapter extends
        RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder>  {
    private final LinkedList<String> mToDoList;
    private LayoutInflater mInflater;

    public ToDoListAdapter(Context context,
                           LinkedList<String> toDoList) {
        mInflater = LayoutInflater.from(context);
        this.mToDoList = toDoList;
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder {
        public final TextView toDoItemView;
        final ToDoListAdapter mAdapter;
        public ToDoViewHolder(View itemView, ToDoListAdapter adapter) {
            super(itemView);
            toDoItemView = itemView.findViewById(R.id.todo);
            this.mAdapter = adapter;
        }
    }
    @NonNull
    @Override
    public ToDoListAdapter.ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.todolist_item,
                parent, false);
        return new ToDoViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListAdapter.ToDoViewHolder holder, int position) {
        String mCurrent = mToDoList.get(position);
        holder.toDoItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }
}
