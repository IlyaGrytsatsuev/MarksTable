package gratchuvalsky.spring.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectsAndIds {
    private int form_id;
    private int student_id;
    private String name;
    private String surname;
    private List<Integer> subject_ids;
    private List<String> subject_names;

    public StudentSubjectsAndIds(){
        subject_ids = new ArrayList<>();
        subject_names = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public List<Integer> getSubject_ids() {
        return subject_ids;
    }

    public void addSubject(int id, String name){
        subject_names.add(name);
        subject_ids.add(id);
    }
    public void setSubject_ids(List<Integer> subject_ids) {
        this.subject_ids = subject_ids;
    }

    public List<String> getSubject_names() {
        return subject_names;
    }

    public void setSubject_names(List<String> subject_names) {
        this.subject_names = subject_names;
    }
}
