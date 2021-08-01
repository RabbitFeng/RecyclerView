package com.rabbit.recycler;

import android.view.View;

import androidx.annotation.NonNull;

public interface OnItemClickListener<T> {
    /**
     * 点击事件
     */
    void onClick(View v,@NonNull T t,int position);

    /**
     * 长按事件
     */
    void onLongClick(View v, @NonNull T t,int position);
}
