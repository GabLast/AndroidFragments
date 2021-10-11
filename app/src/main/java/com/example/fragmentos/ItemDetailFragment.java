package com.example.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fragmentos.placeholder.PlaceholderContent;

public class ItemDetailFragment extends Fragment {

    private PlaceholderContent.PlaceholderItem element;

    public static ItemDetailFragment newInstance(PlaceholderContent.PlaceholderItem element) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("element", element);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            element = (PlaceholderContent.PlaceholderItem) getArguments().getSerializable("element");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        TextView details = view.findViewById(R.id.textviewDetails);
        details.setText(element.details);
        return view;
    }

}
