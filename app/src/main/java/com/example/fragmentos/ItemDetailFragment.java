package com.example.fragmentos;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.fragmentos.placeholder.PlaceholderContent;

import java.time.temporal.ChronoField;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ItemDetailFragment extends Fragment {

    private PlaceholderContent.PlaceholderVersion element;

    public static ItemDetailFragment newInstance(PlaceholderContent.PlaceholderVersion element) {
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
            element = (PlaceholderContent.PlaceholderVersion) getArguments().getSerializable("element");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        TextView details = view.findViewById(R.id.textviewDetails);
        TextView internalcode =  view.findViewById(R.id.textviewInternalCode);
        TextView version =  view.findViewById(R.id.textviewVersion);
        DatePicker datePicker =  view.findViewById(R.id.datePickerReleaseDate);
        CheckBox supported =  view.findViewById(R.id.checkboxSupp);
        TextView lvl =  view.findViewById(R.id.textFieldLvl);

        details.setText(element.getDetails());
        internalcode.setText(element.getInternalCodeName());
        version.setText(element.getVersionNumber());

        int dia = element.getReleaseDate().getDayOfMonth();
        int mes = element.getReleaseDate().getMonthValue();
        int year = element.getReleaseDate().getYear();
        datePicker.updateDate(year, mes, dia);
        datePicker.setEnabled(false);

        supported.setChecked(element.isSupported());
        supported.setEnabled(false);

        lvl.setText(String.valueOf(element.getLevel()));

        Button btnLink = view.findViewById(R.id.linkBTN);
        btnLink.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(element.getLink()));
            startActivity(browserIntent);
        });

        return view;
    }



}
