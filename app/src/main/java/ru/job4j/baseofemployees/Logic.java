package ru.job4j.baseofemployees;

import android.annotation.TargetApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Logic {
    public static Set<Profession> getProfessions(List<Employee> employees) {
        final HashSet<Profession> professions = new LinkedHashSet<>();
//        employees.stream().map(e -> e.getProfession()).forEach(p -> professions.add((Profession) p));
        for (Employee employee : employees) {
            professions.add(employee.getProfession());
        }
        return professions;
    }

    public static List<Employee> findEmployeesByProfession(List<Employee> employees, String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getProfession().getName().equals(name)) {
                result.add(employee);
            }
        }
        return result;
    }

    public static List<Profession> createProfessions() {
        List<Profession> professions = new ArrayList<>();
        professions.add(new Profession("Engineer", 001));
        professions.add(new Profession("Doctor", 002));
        professions.add(new Profession("Teacher", 003));
        return professions;
    }

    public static List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        List<Profession> professions = createProfessions();
        employees.add(new Employee("Niyaz", "Fazlyev", "21.09.1993", R.drawable.niyaz, professions.get(0)));
        employees.add(new Employee("Damir", "Muhametov", "01.03.1994", R.drawable.damir, professions.get(0)));
        return employees;
    }

    public static Employee findEmployeesByFullName(List<Employee> employees, String firstName, String secondName) {
        Employee result = null;
        for (Employee employee : employees){
            if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)) {
                result = employee;
                break;
            }
        }
        return result;
    }
}
