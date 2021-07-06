package com.example.recyclerview.util;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import org.w3c.dom.Text;

import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {

    private static final String TAG = CommonAdapter.class.getName();
    private List<T> mData;

    public CommonAdapter(List<T> mData) {
        this.mData = mData;
    }

    public abstract int getLayoutId(int viewType);

    public abstract void onBind(@NonNull ViewHolder holder, T t, int position);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        Log.d(TAG, "onCreateViewHolder: called");
        return ViewHolder.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        onBind(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = ViewHolder.class.getName();
        private final View mConvertView;
        private final SparseArray<View> mViews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mConvertView = itemView;
            mViews = new SparseArray<>();
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

        public void setText(int id, String text){
            TextView t = getView(id);
            t.setText(text);
        }

    }

}
