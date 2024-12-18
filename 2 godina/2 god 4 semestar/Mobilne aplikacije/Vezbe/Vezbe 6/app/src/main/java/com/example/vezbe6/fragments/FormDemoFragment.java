package com.example.vezbe6.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vezbe6.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class FormDemoFragment extends Fragment {
    public static FormDemoFragment newInstance() {
        return new FormDemoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form, container, false);

        final TextInputEditText date = view.findViewById(R.id.dob);
        final MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> date.setText(datePicker.getHeaderText()));
        date.setFocusable(false);
        date.setOnClickListener(view1 -> datePicker.show(requireActivity().getSupportFragmentManager(), "Pick a date"));

        List<String> items = Arrays.asList("Serbian", "American", "German");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, items);
        final AutoCompleteTextView nationalities = view.findViewById(R.id.nationalities);
        nationalities.setAdapter(arrayAdapter);

        final SwitchMaterial toggle = view.findViewById(R.id.toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            TextInputLayout input = (TextInputLayout) nationalities.getParent().getParent();
            @Override
            public void onClick(View view) {
                input.setVisibility(toggle.isChecked() ? View.VISIBLE : View.GONE);
            }
        });

        final TextInputEditText email = view.findViewById(R.id.email);
        Button submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            final String regExp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            @Override
            public void onClick(View view) {
                TextInputLayout emailLayout = (TextInputLayout) email.getParent().getParent();
                if(email.getText().toString().trim().isEmpty())
                    emailLayout.setError(getString(R.string.required));
                else if(!email.getText().toString().matches(regExp))
                    emailLayout.setError(getString(R.string.email_err));
                else {
                    emailLayout.setError(null);
                    Toast.makeText(getActivity(), "Form is valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}