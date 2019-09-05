package ru.job4j.baseofemployees;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmployeesFragment extends Fragment {
    private static OnEmployeeClickListener callback;
    private List<Employee> employees;
    private RecyclerView.Adapter adapter;

    public interface OnEmployeeClickListener {
        void onEmployeeClicked(String firstName, String secondName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employees = getArguments().getParcelableArrayList("employees");
        adapter = new EmployeeAdapter(this.employees);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees, container, false);
//        System.out.println(adapter.getItemCount());
        RecyclerView recycler = view.findViewById(R.id.employees);
//        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        return view;
    }

    private static final class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Employee> employees;

        public EmployeeAdapter(List<Employee> employees) {
            this.employees = employees;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.employee_info, parent, false)
            ) {
            };
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
            final Employee employee = this.employees.get(i);
            TextView firstName = holder.itemView.findViewById(R.id.firstName);
            firstName.setText(employee.getFirstName());
            TextView secondName = holder.itemView.findViewById(R.id.secondName);
            secondName.setText(employee.getSecondName());
            firstName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onEmployeeClicked(firstName.getText().toString(), secondName.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.employees.size();
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (EmployeesFragment.OnEmployeeClickListener) context; // назначаем активити при присоединении фрагмента к активити
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // обнуляем ссылку при отсоединении фрагмента от активити
    }


}

