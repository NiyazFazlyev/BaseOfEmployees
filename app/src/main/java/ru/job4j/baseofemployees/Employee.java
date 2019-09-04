package ru.job4j.baseofemployees;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Employee implements Parcelable {
    private String firstName;
    private String secondName;
    private String birthday;
    private int foto;
    private Profession profession;

    public Employee(String firstName, String secondName, String birthday, int foto, Profession profession) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.foto = foto;
        this.profession = profession;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Profession getProfession() {
        return profession;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getFoto() {
        return foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (foto != employee.foto) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null)
            return false;
        if (secondName != null ? !secondName.equals(employee.secondName) : employee.secondName != null)
            return false;
        if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null)
            return false;
        return profession != null ? profession.equals(employee.profession) : employee.profession == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + foto;
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        return result;
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            String firstName = source.readString();
            String secondName = source.readString();
            String birthday = source.readString();
            int foto = source.readInt();
            Profession profession = source.readParcelable(Profession.class.getClassLoader());
            return new Employee(firstName, secondName, birthday, foto, profession);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.firstName);
        parcel.writeString(this.secondName);
        parcel.writeString(this.birthday);
        parcel.writeInt(this.foto);
        parcel.writeParcelable(this.profession, 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
