package com.example.fragmentos;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentos.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.fragmentos.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;
    private final OnTouchListener<PlaceholderItem> mListener;


    public MyItemRecyclerViewAdapter(List<PlaceholderItem> items, OnTouchListener<PlaceholderItem> listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FragmentItemBinding binding = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, mListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(holder.mItem.content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mContentView;
        public PlaceholderItem mItem;
        private OnTouchListener<PlaceholderItem> mListener;

        public ViewHolder(FragmentItemBinding binding, OnTouchListener<PlaceholderItem> listener) {
            super(binding.getRoot());
            mContentView = binding.content;
            mListener = listener;

            binding.getRoot().setOnClickListener(this);

//            para el textview que esta en ese fragment item seria:
//            mContentView.setOnClickListener();
        }

        //manda a ejecutar lo que se realice en ItemFragment
        @Override
        public void onClick(View v) {
            mListener.onClick(mItem);
        }
    }
}