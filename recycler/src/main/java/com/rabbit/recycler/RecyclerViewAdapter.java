package com.rabbit.recycler;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    /**
     * 数据源
     */
    private List<T> mData;

    public RecyclerViewAdapter(List<T> mData) {
        this.mData = mData;
    }

    public abstract int getLayoutId(int viewType);

    public abstract void onBind(@NonNull ViewHolder holder, @Nullable T t, int position);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBind(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ViewHolder";
        private final View mConvertView;
        private final SparseArray<View> mViews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mConvertView = itemView;
            this.mViews = new SparseArray<>();
        }

        public static ViewHolder get(ViewGroup parent, int layoutRes) {
            View convertView = LayoutInflater.from(parent.getContext())
                    .inflate(layoutRes, parent, false);
            return new ViewHolder(convertView);
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = mConvertView.findViewById(id);
                mViews.put(id, view);
            }
            return (T) view;
        }

        public void setText(int id, String text) {
            TextView t = getView(id);
            t.setText(text);
        }
    }
}
