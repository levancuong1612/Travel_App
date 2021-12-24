package com.example.apptravelvn;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FragmentHome extends Fragment {
    View view;
    EditText inputSearch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_home,container,false);
       anhXa();
       inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Intent intent= new Intent(view.getContext(),ResultSearchActivity.class);
                intent.putExtra("search",inputSearch.getText().toString());
                startActivity(intent);
               return false;
           }
       });
        return view;
    }
    void anhXa(){
        inputSearch =(EditText) view.findViewById(R.id.editTextTextPersonName);

    }

}
