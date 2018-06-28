package crats.mvcbaseproject.view;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

import crats.mvcbaseproject.R;
import crats.mvcbaseproject.controller.IUserController;
import crats.mvcbaseproject.controller.UserController;

import crats.mvcbaseproject.model.User;
public class Users extends AppCompatActivity implements AdapterView.OnItemClickListener,IUserController{

    ListView userListView;

    ArrayList<String> userList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("User List Activity");

        userListView = (ListView) findViewById(R.id.userListView);




        UserController.shared().setupUserController(this, getBaseContext());
        UserController.shared().fetchList();
    }


    private void setupListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getBaseContext(),android.R.layout.simple_list_item_1,this.userList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);return textView;
            }
        };
        userListView.setOnItemClickListener(this);
        userListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = UserController.shared().getUserList().get(position);
        startActivity(new Intent(Users.this,UserDetail.class));
    }

    public ArrayList<String> getUserList(){

        ArrayList<String> personList_name = new ArrayList<String>();
        ArrayList<User> list = new ArrayList<>();
        String name;

        list = UserController.shared().getUserList();

        for (int i = 0; i <list.size() ; i++) {
            name = list.get(i).getFirstName();
            personList_name.add(name);
        }

        return personList_name;
    }


    @Override
    public void fetchSuccess() {
        userList = this.getUserList();
        this.setupListView();
    }

    @Override
    public void fetchFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
