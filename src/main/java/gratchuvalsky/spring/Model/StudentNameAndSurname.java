package gratchuvalsky.spring.Model;

public class StudentNameAndSurname {

    private int id;
    private int form_id;
    private String name;
    private String surname;
    public StudentNameAndSurname(){
        id = 0;
        form_id = 0;
        name = null;
        surname = null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
