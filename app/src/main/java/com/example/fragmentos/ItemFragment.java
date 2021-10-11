package com.example.fragmentos;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fragmentos.placeholder.PlaceholderContent;
import com.google.android.material.snackbar.Snackbar;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment implements OnTouchListener<PlaceholderContent.PlaceholderItem> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS, this));
        }
        return view;
    }


    @Override
    public void onClick(PlaceholderContent.PlaceholderItem element){
//        Snackbar.make(getView(), element.content, Snackbar.LENGTH_LONG).show();
        int fragmentoRemplazar;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            fragmentoRemplazar = R.id.fragmentDetail;
        }else {
            fragmentoRemplazar = R.id.fragmentParent;
        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(fragmentoRemplazar, ItemDetailFragment.newInstance(element))
                .addToBackStack(null)
                .commit();
    }
}