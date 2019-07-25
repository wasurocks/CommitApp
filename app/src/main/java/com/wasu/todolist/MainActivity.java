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
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mToDoList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ToDoListAdapter mAdapter;
    private TextView mNoOfCommits;
    private int no_of_commits = 0;


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
        mNoOfCommits = findViewById(R.id.text_no_of_commit);
        mNoOfCommits.setText(R.string.text_initial_no_commits);

    }

    public void newTask(View view) {
        NewTaskDialogFragment task = new NewTaskDialogFragment();
        task.show(getSupportFragmentManager(), "test");
        task.setDialogResult(new NewTaskDialogFragment.OnMyDialogResult(){
            public void finish(String result){
                // Check to see if the added item from dialog is empty or not
                if(result != null && !result.isEmpty()) {
                    // Add the new task to the linked list
                    mToDoList.addLast(result.trim());
                    // Little spinning animation for logo
                    rotateLogo(R.id.app_logo);
                    no_of_commits++;
                }
                if (no_of_commits > 0) {
                    if (no_of_commits == 1) {
                        mNoOfCommits.setText("1 commitment");
                    } else {
                        mNoOfCommits.setText(no_of_commits + " commitments");
                    }

                }
            }
        });

    }

    public void rotateLogo(int id) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(0);
        findViewById(id).startAnimation(rotateAnimation);
    }
}
