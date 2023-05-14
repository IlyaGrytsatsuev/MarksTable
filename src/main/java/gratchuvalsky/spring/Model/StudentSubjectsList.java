package gratchuvalsky.spring.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectsList {

    private String name;

    private String surname;

    private int student_id;

    private int form_id;


    private List<Integer> subjects_ids;
    private List<String> subjects_names;

    public StudentSubjectsList(){
        name = null;
        surname = null;
        student_id = 0;
        form_id = 0;
        subjects_ids = new ArrayList<>();
        subjects_names = new ArrayList<>();
    }

    public int getForm_id() {
        return form_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public List<Integer> getSubjects_ids() {
        return subjects_ids;
    }

    public List<String> getSubjects_names() {
        return subjects_names;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public String getStudentName() {
        return name;
    }

    public String getStudentSurname() {
        return surname;
    }

    public void setStudentName(String student_name) {
        this.name = student_name;
    }

    public void setStudentSurname(String surname) {
        this.surname = surname;
    }

    public void addSubjectId(int id){
        subjects_ids.add(id);
    }

    public void addSubjectName(String name){
        subjects_names.add(name);
    }

    public void setSubjects_ids(List<Integer> subjects_ids) {
        this.subjects_ids = subjects_ids;
    }

    public void setSubjects_names(List<String> subjects_names) {
        this.subjects_names = subjects_names;
    }
}
