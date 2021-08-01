package com.rabbit.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

@SuppressWarnings("unused")
public abstract class RecyclerViewBindingAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerViewBindingAdapter.BindingHolder<B>> {
    private static final String TAG = "AbstractBindingAdapter";
    /**
     * 数据源
     */
    protected List<T> mData;

    public RecyclerViewBindingAdapter(@Nullable List<T> data) {
        this.mData = data;
    }

    /**
     * 获取布局
     *
     * @param viewType 视图类型
     * @return 视图布局
     */
    @LayoutRes
    public abstract int getLayoutId(int viewType);

    /**
     * 绑定
     *
     * @param holder   视图Holder
     * @param t        数据
     * @param position 位置
     */
    public abstract void onBind(@NonNull BindingHolder<B> holder, @Nullable T t, int position);

    /**
     * 更新数据
     *
     * @param list 数据源
     */
    public abstract void update(@Nullable List<T> list);

    @NonNull
    @Override
    public BindingHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(viewType),
                parent, false);
        return new BindingHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder<B> holder, int position) {
        onBind(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class BindingHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        @NonNull
        private final B binding;

        public BindingHolder(@NonNull B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @NonNull
        public B getBinding() {
            return binding;
        }
    }
}
