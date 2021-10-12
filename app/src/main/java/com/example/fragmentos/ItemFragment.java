package com.example.fragmentos;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fragmentos.placeholder.PlaceholderContent;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment implements OnTouchListener<PlaceholderContent.PlaceholderVersion> {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private List<String> reqPerms = new ArrayList<>();
    private final int PERMISSION_REQUEST_CODE = 200;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(PlaceholderContent.PlaceholderVersion element){

        askPermisions();

        if(verifyPermissions()){
            initDetailFragment(element);
        }else{
            Snackbar.make(getView(), "You don't have the required permissions to see this view", Snackbar.LENGTH_LONG).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initDetailFragment(PlaceholderContent.PlaceholderVersion element){
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

    public void askPermisions() {

        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            reqPerms.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            reqPerms.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            reqPerms.add(Manifest.permission.CAMERA);
        }

        if (!reqPerms.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), reqPerms.toArray(new String[reqPerms.size()]), PERMISSION_REQUEST_CODE);
        }
    }

    public boolean verifyPermissions(){
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//            && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            return true;
        }else {
            return false;
        }
        /*TODO
           WRITE_EXTERNAL_STORAGE no funciona apropiadamente
        ** a diferencia de los demas permisos, los cuales no tienen ningun problema
        */
//        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//
//            return true;
//        }else {
//            return false;
//        }
    }

}