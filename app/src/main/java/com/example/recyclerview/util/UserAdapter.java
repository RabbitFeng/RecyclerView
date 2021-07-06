package com.example.recyclerview.util;

import android.util.Log;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.recyclerview.R;
import com.example.recyclerview.data.User;
import com.example.recyclerview.databinding.ItemUserBinding;

import java.util.List;

public class UserAdapter extends AbstractBindingAdapter<User, ItemUserBinding> {
    private static final String TAG = UserAdapter.class.getName();
    private OnItemClickCallback<User> callback;

    public UserAdapter(OnItemClickCallback<User> callback) {
        this.callback = callback;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_user;
    }

    @Override
    public void onBind(ViewHolder<ItemUserBinding> holder, User user, int position) {
        Log.d(TAG, "onBind: called");
        holder.binding.setCallback(callback);
        holder.binding.getRoot().setOnLongClickListener(v -> {
            callback.onLongClick(user);
            return true;
        });
        holder.binding.setUser(user);
        holder.binding.executePendingBindings();
    }

    public void setList(List<User> userList) {
        this.list = userList;
    }
}
