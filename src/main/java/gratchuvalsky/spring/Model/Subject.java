package gratchuvalsky.spring.Model;

import java.sql.Date;
import java.util.*;

public class Subject {

    private int id;
    private String name;

    private Map<Integer, Integer> marks;
    private List<Date> dates;

    private int student_id;


    public Subject(){
        name = null;
        marks = new LinkedHashMap<>();
        dates = new ArrayList<>();
    }

   /* public Subject(int id, String name, List<Integer> marks, List<Date> dates){
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.dates = dates;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addMark(int id, Date date, int mark){
        marks.put(id, mark);
        dates.add(date);
    }

    public void removeMark(Date date, int mark){
        marks.remove(date);
    }


    public Map<Integer, Integer> getMarks() {
        return marks;
    }
    public List<Date> getDates(){
        return dates;
    }
}
