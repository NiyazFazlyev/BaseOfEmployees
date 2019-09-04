package ru.job4j.baseofemployees;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class HostFragmentsActivity extends AppCompatActivity
        implements ProfessionsFragment.OnProfessionClickListener, EmployeesFragment.OnEmployeeClickListener {
    private FragmentManager fm;
    private Fragment professionsFragment;
    private Fragment employeesFragment;
    private Fragment detailInfoFragment;

    private final List<Employee> employees = Logic.createEmployees();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_fragments);
        fm = getSupportFragmentManager(); // получить FragmentManager
        professionsFragment = fm.findFragmentById(R.id.fragment_container);
        if (professionsFragment == null) {
            professionsFragment = new ProfessionsFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, professionsFragment) // добавить фрагмент в контейнер
                    .commit();
        }
    }

    @Override
    public void onProfessionClicked(String message) {
        ArrayList<Employee> result = (ArrayList) Logic.findEmployeesByProfession(this.employees, message);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("employees", result);
        if (employeesFragment == null) {
            employeesFragment = new EmployeesFragment();
        }
        employeesFragment.setArguments(bundle);
        fm.beginTransaction()
                .replace(R.id.fragment_container, employeesFragment) // добавить фрагмент в контейнер
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onEmployeeClicked(String firstName, String secondName){
        Employee employee = Logic.findEmployeesByFullName(this.employees,firstName, secondName);
        Bundle bundle = new Bundle();
        bundle.putParcelable("employee", employee);
        if (detailInfoFragment == null) {
            detailInfoFragment = new DetailInfoFragment();
        }
        detailInfoFragment.setArguments(bundle);
        fm.beginTransaction()
                .replace(R.id.fragment_container, detailInfoFragment) // добавить фрагмент в контейнер
                .addToBackStack(null)
                .commit();
    }
}
