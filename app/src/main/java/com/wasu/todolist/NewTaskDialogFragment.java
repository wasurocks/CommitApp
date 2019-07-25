package com.wasu.todolist;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class NewTaskDialogFragment extends DialogFragment {

    public static final String TAG = "example_dialog";

    private Toolbar toolbar;
    private Button button_done;
    private SwitchMaterial switch_pick_date;
    private EditText todo_input;
    private OnMyDialogResult mDialogResult;

    public static NewTaskDialogFragment display(FragmentManager fragmentManager) {
        NewTaskDialogFragment newTaskDialogFragment = new NewTaskDialogFragment();
        newTaskDialogFragment.show(fragmentManager, TAG);
        return newTaskDialogFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.example_dialog, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        button_done = view.findViewById(R.id.btn_add_task);
        switch_pick_date = view.findViewById(R.id.switch_pick_date);
        todo_input = view.findViewById(R.id.todo_input);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        button_done.setOnClickListener(this::onAdd);
        switch_pick_date.setOnClickListener(this::onSwitchPickDate);
        toolbar.setTitle("Add new task");
    }

    public void onAdd(View v) {
        if (mDialogResult != null) {
            mDialogResult.finish(String.valueOf(todo_input.getText()));
        }
        NewTaskDialogFragment.this.dismiss();
    }

    // Expand on the switch to open up a dialog fragment to select a date

    public void onSwitchPickDate(View v) {
        Toast test = Toast.makeText(getContext(), "Date Enabled", Toast.LENGTH_SHORT);
        test.show();
    }

    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(String result);
    }


}