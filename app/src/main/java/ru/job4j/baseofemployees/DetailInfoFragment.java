package ru.job4j.baseofemployees;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_info, container, false);
        Employee  employee = getArguments().getParcelable("employee");
        TextView firstName = view.findViewById(R.id.textView);
        firstName.setText(employee.getFirstName());
        TextView secondName = view.findViewById(R.id.textView2);
        secondName.setText(employee.getSecondName());
        TextView birthday = view.findViewById(R.id.textView3);
        birthday.setText(employee.getBirthday());
        TextView profession = view.findViewById(R.id.textView4);
        profession.setText(employee.getProfession().getName());
        ImageView foto = view.findViewById(R.id.foto);
        foto.setImageResource(employee.getFoto());
        return view;
    }
}
