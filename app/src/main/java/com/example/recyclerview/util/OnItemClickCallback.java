package com.example.recyclerview.util;

public interface OnItemClickCallback<T> {
    void onClick(T t);
    void onLongClick(T t);
}
