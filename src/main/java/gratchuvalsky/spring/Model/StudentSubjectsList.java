package gratchuvalsky.spring.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectsList {

    private String name;

    private String surname;

    private int student_id;

    private int form_id;


    private List<Subject> subjectsList;

    public StudentSubjectsList(){
        name = null;
        surname = null;
        student_id = 0;
        form_id = 0;
        subjectsList = new ArrayList<>();
    }

    public int getForm_id() {
        return form_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public List<Subject> getSubjectsList() {
        return subjectsList;
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

    public void setSubjectsList(List<Subject> subjectsList) {
        this.subjectsList = subjectsList;
    }
    public void addSubject(Subject subject){
        subjectsList.add(subject);
    }
}
