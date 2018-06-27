package crats.mvcbaseproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;

import crats.mvcbaseproject.R;

import crats.mvcbaseproject.controller.IUserController;

public abstract class Users extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
    }
}
