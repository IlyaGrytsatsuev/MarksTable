package gratchuvalsky.spring.Model;

import java.util.ArrayList;
import java.util.List;

public class SchoolForm {
    private int form_id;
    private String form_name;
    private List<Student> students;


    public SchoolForm(){
        form_name = null;
        students = null;
    }

    public SchoolForm(String form_name){
        this.form_name = form_name;
        students = new ArrayList<>();
    }

    public SchoolForm(int form_id, String form_name){
        this.form_id = form_id;
        this.form_name = form_name;
    }

    public void setId(int id) {
        this.form_id = id;
    }

    public int getId() {
        return form_id;
    }

    public void setForm(String form_name) {
        this.form_name = form_name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getFormName() {
        return form_name;
    }

    public List<Student> getStudents() {
        return students;
    }

    /*public void addStudent(String name, String surname){
        students.add(new Student(name, surname));
    }*/

    public void removeStudent(String name, String surname){
        students.removeIf(p->p.getName().equals(name)&&p.getSurname().equals(surname));

    }
}
