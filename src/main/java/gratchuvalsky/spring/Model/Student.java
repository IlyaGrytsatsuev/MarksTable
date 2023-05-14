package gratchuvalsky.spring.Model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String surname;

    private int form_id;

    private List<Subject> subjects;

    public Student(){
        this.name = null;
        this.surname = null;
        this.subjects = new ArrayList<>();
    }

    public Student(int id, String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.subjects = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void addSubject(Subject subject){subjects.add(subject);}

    public void deleteSubject(String subject){
        subjects.removeIf(p->p.getName().equals(subject));
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
