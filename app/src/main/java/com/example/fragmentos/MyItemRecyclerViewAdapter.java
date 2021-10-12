package com.example.fragmentos;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentos.placeholder.PlaceholderContent;
import com.example.fragmentos.databinding.FragmentItemBinding;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderContent.PlaceholderVersion> mValues;
    private final OnTouchListener<PlaceholderContent.PlaceholderVersion> mListener;


    public MyItemRecyclerViewAdapter(List<PlaceholderContent.PlaceholderVersion> items, OnTouchListener<PlaceholderContent.PlaceholderVersion> listener) {
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
        holder.mContentView.setText(holder.mItem.getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mContentView;
        public PlaceholderContent.PlaceholderVersion mItem;
        private OnTouchListener<PlaceholderContent.PlaceholderVersion> mListener;

        public ViewHolder(FragmentItemBinding binding, OnTouchListener<PlaceholderContent.PlaceholderVersion> listener) {
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