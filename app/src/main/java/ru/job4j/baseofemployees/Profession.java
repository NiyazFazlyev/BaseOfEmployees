package ru.job4j.baseofemployees;

import android.os.Parcel;
import android.os.Parcelable;

public class Profession implements Comparable, Parcelable {
    private  String name;
    private int id;

    public Profession(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profession)) return false;

        Profession that = (Profession) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public int compareTo(Object o){
        Profession profession = (Profession) o;
        return this.name.compareTo(profession.getName());
    }

    public static final Creator<Profession> CREATOR = new Creator<Profession>() {
        @Override
        public Profession createFromParcel(Parcel source) {
            String name = source.readString();
            int id = source.readInt();
            return new Profession(name, id);
        }

        @Override
        public Profession[] newArray(int size) {
            return new Profession[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.name);
        parcel.writeInt(this.id);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
