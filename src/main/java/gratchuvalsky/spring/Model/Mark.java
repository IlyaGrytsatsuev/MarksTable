package gratchuvalsky.spring.Model;

import java.sql.Date;

public class Mark {
    private int mark_value;
    private Date date;
    private int student_id;
    private int subject_id;

    private int form_id;

    public Mark(){

    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Date getDate() {
        return date;
    }

    public int getMark_value() {
        return mark_value;
    }

    public void setMark_value(int mark_value) {
        this.mark_value = mark_value;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
