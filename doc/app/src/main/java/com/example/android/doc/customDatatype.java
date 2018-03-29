package com.example.android.doc;

public class customDatatype {

    String name_of_patient;
    String age;
    String gender_of_patient;
    String part;


    public customDatatype(String name_of_patient, String age, String gender_of_patient, String part) {
        this.name_of_patient = name_of_patient;
        this.age = age;
        this.gender_of_patient = gender_of_patient;
        this.part = part;
    }

    public String getName_of_patient() {
        return name_of_patient;
    }

    public void setName_of_patient(String name_of_patient) {
        this.name_of_patient = name_of_patient;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender_of_patient() {
        return gender_of_patient;
    }

    public void setGender_of_patient(String gender_of_patient) {
        this.gender_of_patient = gender_of_patient;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }


}
