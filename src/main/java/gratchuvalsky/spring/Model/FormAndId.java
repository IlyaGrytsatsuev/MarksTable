package gratchuvalsky.spring.Model;

public class FormAndId {
    private int id;
    private String form_name;

    public FormAndId(){
        id = 0;
        form_name = null;
    }

    public FormAndId(int form_id, String form_name){
        this.id = form_id;
        this.form_name = form_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getForm_name() {
        return form_name;
    }

    public int getId() {
        return id;
    }
}
