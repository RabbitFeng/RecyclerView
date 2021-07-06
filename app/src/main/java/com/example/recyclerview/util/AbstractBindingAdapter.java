package com.example.recyclerview.util;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class AbstractBindingAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<AbstractBindingAdapter.ViewHolder<B>> {
    private static final String TAG = AbstractBindingAdapter.class.getName();
    protected List<T> list;

    public abstract int getLayoutId(int viewType);

    public abstract void onBind(ViewHolder<B> holder, T t, int position);

    @NonNull
    @Override
    public ViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(viewType), parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<B> holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        onBind(holder, list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        B binding;

        public ViewHolder(B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
