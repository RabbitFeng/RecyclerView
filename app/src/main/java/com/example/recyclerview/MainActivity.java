package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ListChangeRegistry;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerview.data.Common;
import com.example.recyclerview.data.User;
import com.example.recyclerview.databinding.ActivityMainBinding;
import com.example.recyclerview.util.CommonAdapter;
import com.example.recyclerview.util.CustomAdapter;
import com.example.recyclerview.util.OnItemClickCallback;
import com.example.recyclerview.util.UserAdapter;
import com.example.recyclerview.util.UserBindingAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv);
//        List<Common> commonList = new ArrayList<>();
//        for (int i = 0; i < 40; i++) {
//            commonList.add(new Common("dong", i));
//        }
//        CommonAdapter<Common> commonAdapter = new CommonAdapter<Common>(commonList) {
//            @Override
//            public int getLayoutId(int viewType) {
//                return R.layout.item_common;
//            }
//
//            @Override
//            public void onBind(@NonNull ViewHolder holder, Common common, int position) {
//                holder.setText(R.id.tv_name, common.getName());
//                holder.setText(R.id.tv_age, common.getAge() + "");
//            }
//        };
//
//        recyclerView.setAdapter(commonAdapter);

        UserBindingAdapter adapter = new UserBindingAdapter(new UserBindingAdapter.ClickCallback() {
            @Override
            public void onClick(User user) {
                Log.d(TAG, "onClick: called "+user.getUsername());
            }
        });

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            userList.add(new User("dong" + i, "123"));
        }
        UserAdapter userAdapter =new UserAdapter(new OnItemClickCallback<User>() {
            @Override
            public void onClick(User user) {
                Log.d(TAG, "onClick: called");
            }

            @Override
            public void onLongClick(User user) {
                Log.d(TAG, "onLongClick: called");

            }
        });
        recyclerView.setAdapter(userAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        userAdapter.setList(userList);

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {

        });
    }
}