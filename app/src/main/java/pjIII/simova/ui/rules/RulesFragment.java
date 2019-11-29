package pjIII.simova.ui.rules;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import pjIII.simova.R;

public class RulesFragment extends Fragment {

    private RulesViewModel rulesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rulesViewModel =
                ViewModelProviders.of(this).get(RulesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rules, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        rulesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}