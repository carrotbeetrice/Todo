package com.example.todo.ui.addtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;

public class AddTaskFragment extends Fragment {

    private AddTaskViewModel addTaskViewModel;
    private final String[] MODS = new String[]{
            "shit","fuk","pee","poo","master of all 4 shitterments"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addTaskViewModel =
                ViewModelProviders.of(this).get(AddTaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_addtask, container, false);

        AutoCompleteTextView editText = (AutoCompleteTextView) root.findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_item,MODS);
        editText.setAdapter(adapter);

        return root;


    }
}