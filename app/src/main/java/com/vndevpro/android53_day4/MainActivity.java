package com.vndevpro.android53_day4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvDemo;
    private EditText edtPosition;
    private Button btnAdd, btnUpdate, btnRemove;
    private ArrayList<String> mListColor;
    private UserAdapter mUserAdapter;
    private ArrayList<User> mListUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        mListColor = new ArrayList<>();
//        mListColor.add("Red");
//        mListColor.add("Green");
//        mListColor.add("Blue");
//        mListColor.add("Yellow");
//        mListColor.add("Black");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, mListColor);
//        lvDemo.setAdapter(arrayAdapter);

        initData();
        initView();

    }

    private void initView() {
        lvDemo = findViewById(R.id.lvDemo);
        edtPosition = findViewById(R.id.edtPosition);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRemove = findViewById(R.id.btnRemove);
        mUserAdapter = new UserAdapter(mListUsers, this);

        lvDemo.setAdapter(mUserAdapter);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtPosition.setText(position + "");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addUser();
                break;
            case R.id.btnUpdate:
                updateUser();
                break;
            case R.id.btnRemove:
                removeUser();
                break;
        }
    }

    private void removeUser() {
        int position = Integer.parseInt(edtPosition.getText().toString());
        if (position >= 0 && position <= mListUsers.size() - 1) {

            mListUsers.remove(position);
            mUserAdapter.notifyDataSetChanged();
        }
    }

    private void updateUser() {
        int position = Integer.parseInt(edtPosition.getText().toString());
        if (position >= 0 && position <= mListUsers.size() - 1) {
            User user = mListUsers.get(position);
            user.setUserName("Update " + user.getUserName());

            mListUsers.set(position, user);
            mUserAdapter.notifyDataSetChanged();
            lvDemo.smoothScrollToPosition(position);
        }

    }

    private void addUser() {
        User user = new User();
        user.setUserName("New User name " + mListUsers.size());
        user.setAge(mListUsers.size() + 10);
        user.setEmail("email" + mListUsers.size() + "@gmail.com");
        user.setAvatar("https://img6.thuthuatphanmem.vn/uploads/2022/11/18/anh-avatar-don-gian-ma-dep_081757969.jpg");
        user.setAddress("HN");
        mListUsers.add(user);

        mUserAdapter.notifyDataSetChanged();
        lvDemo.smoothScrollToPosition(mListUsers.size() - 1);
    }

    private void initData() {
        mListUsers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setUserName("User name " + i);
            user.setAge(i + 10);
            user.setEmail("email" + i + "@gmail.com");
            user.setAvatar("https://img6.thuthuatphanmem.vn/uploads/2022/11/18/anh-avatar-don-gian-ma-dep_081757969.jpg");
            user.setAddress("HN");

            mListUsers.add(user);
        }
    }
}