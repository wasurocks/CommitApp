package com.wasu.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mToDoList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ToDoListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ToDoListAdapter(this, mToDoList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Add a border below each word to give space
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));


    }

    public void newTask(View view) {
        NewTaskDialogFragment task = new NewTaskDialogFragment();
        task.show(getSupportFragmentManager(), "test");
        task.setDialogResult(new NewTaskDialogFragment.OnMyDialogResult(){
            public void finish(String result){
                // Check to see if the added item from dialog is empty or not
                if(result != null && !result.isEmpty()) {
                    StringBuilder message = new StringBuilder("• ");
                    // Add the new task to the linked list
                    mToDoList.addLast(message.append(result).toString());
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);

                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    rotateAnimation.setDuration(500);
                    rotateAnimation.setRepeatCount(0);
                    findViewById(R.id.app_logo).startAnimation(rotateAnimation);
                }
            }
        });

    }
}
