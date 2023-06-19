package gratchuvalsky.spring.Controller;

import gratchuvalsky.spring.DAO.MarksTablesDao;
import gratchuvalsky.spring.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RequestMapping("/Forms")
@Controller
public class editDBController {
    MarksTablesDao dao;

    @Autowired
    public editDBController(MarksTablesDao dao){
        this.dao = dao;
    }


    @GetMapping("/addMark:form={form_id}:subject={subject_id}:student={student_id}")
    public String addMarkForm(@PathVariable("form_id") int form_id, @PathVariable("subject_id") int subject_id,
                              @PathVariable("student_id") int student_id, Model model){
        Mark mark = new Mark();
        mark.setForm_id(form_id);
        mark.setSubject_id(subject_id);
        mark.setStudent_id(student_id);
        model.addAttribute("mark", mark);
        return "addMark";
    }

    @PostMapping("/addMark:form={form_id}:subject={subject_id}:student={student_id}")
    public String addMark(@ModelAttribute("mark") Mark mark, @PathVariable("form_id") int form_id) throws SQLException {
        dao.add_mark(mark);
        return "redirect:/Forms/" + "Students:form=" + form_id +
                "/Subjects:student="+ mark.getStudent_id() + "/Marks:subject=" + mark.getSubject_id();
    }

    @GetMapping("")
    public String getFormsPage(Model model){
        List<FormAndId> forms_list= dao.getSchoolFormsList();
        model.addAttribute("list", forms_list);
        return "FormsList";
    }

    @GetMapping("/Students:form={form_id}")
    public String getStudentsList(@PathVariable("form_id") int form_id, Model model){
        model.addAttribute("students", dao.getStudentsList(form_id));
        model.addAttribute("form_id", form_id);
        return "StudentsList";
    }

    @GetMapping("/Students:form={form_id}/Subjects:student={student_id}")
    public String getSubjectsAndMarks(@PathVariable("form_id") int form_id,
                                      @PathVariable("student_id") int student_id,
                                      Model model){
        StudentSubjectsList subjectsAndMarks =
                dao.getStudentSubjectsAndMarks(form_id, student_id);

        model.addAttribute("subjectsAndMarks", subjectsAndMarks);
        model.addAttribute("form_id", form_id);
        return "SubjectsList";
    }
    /*public String getSubjectsList(@PathVariable("form_id") int form_id, @PathVariable("student_id") int student_id, Model model ){
        StudentSubjectsAndIds subjectsList = dao.getStudentSubjects(student_id, form_id);

        //System.out.println(subjectsList.getName() + " " + subjectsList.getSurname());
        model.addAttribute("form_id", form_id);
        model.addAttribute("subjectsList", subjectsList);
        model.addAttribute("subjects_names", subjectsList.getSubject_names());
        model.addAttribute("subjects_ids", subjectsList.getSubject_ids());

        return "SubjectsList";
    }

    @GetMapping("/Students:form={form_id}" +
            "/Subjects:student={student_id}/Marks:subject={subject_id}")
    public String getSubjectMarks(@PathVariable("form_id") int form_id, @PathVariable("student_id") int student_id, @PathVariable("subject_id") int subject_id, Model model){

        Subject subject = dao.getSubjectMarks(student_id, subject_id);
        if(subject!= null) {
            Map<Integer, Integer> marks = subject.getMarks();
            model.addAttribute("form_id", form_id);
            model.addAttribute("marks", subject.getMarks());
            model.addAttribute("dates", subject.getDates());
        }
        model.addAttribute("subject", subject);

        return "SubjectMarks";
    }*/


    @GetMapping("/editMark:form={form_id}:" +
            "subject={subject_id}:student={student_id}:mark={mark_id}")
    public String getEditMarkForm(@PathVariable("form_id") int form_id, @PathVariable("subject_id") int subject_id,
                                  @PathVariable("student_id") int student_id,
                                  @PathVariable("mark_id") int mark_id, Model model){
        model.addAttribute("form_id", form_id);
        model.addAttribute("subject_id", subject_id);
        model.addAttribute("student_id", student_id);
        model.addAttribute("mark_id", mark_id);
        return "editMark";
    }

    @PatchMapping("/editMark:form={form_id}:" +
            "subject={subject_id}:student={student_id}:mark={mark_id}")
    public String editMark(@PathVariable("mark_id") int mark_id, @RequestParam("mark_value") int mark_value,
                           @PathVariable("form_id") String form_id, @PathVariable("student_id") String student_id,
                           @PathVariable("subject_id") String subject_id){
        dao.editMark(mark_id, mark_value);
        return "redirect:/Forms" + "/Students:form=" + form_id
                +"/Subjects:student=" + student_id + "/Marks:subject=" + subject_id;
    }

    @DeleteMapping("/deleteMark:form={form_id}:" +
            "subject={subject_id}:student={student_id}:mark={mark_id}")
    public String deleteMark(@PathVariable("mark_id") int mark_id,
                             @PathVariable("form_id") String form_id,
                             @PathVariable("student_id") String student_id,
                             @PathVariable("subject_id") String subject_id){

        dao.deleteMark(mark_id);

        return "redirect:/Forms" + "/Students:form=" + form_id
                +"/Subjects:student=" + student_id + "/Marks:subject=" + subject_id;
    }

    @DeleteMapping("/deleteForm:form={form_id}")
    public String DeleteForm(@PathVariable("form_id") int form_id){
        dao.deleteForm(form_id);
        return "redirect:/Forms";
    }

    @GetMapping("/editForm:form={form_id}")
    public  String getEditFormName(@PathVariable("form_id") int form_id, Model model){
        model.addAttribute("form_id", form_id);
        return "editForm";
    }

    @PatchMapping("/editForm:form={form_id}")
    public String editFormName(@PathVariable("form_id") int form_id,
                               @RequestParam("form_name") String form_name){
        dao.editForm(form_id, form_name);
        return "redirect:/Forms";
    }

    @GetMapping("/addForm")
    public String getAddForm(){
        return "addForm";
    }

    @PostMapping("/addForm")
    public String addForm(@RequestParam("form_name") String form_name){
        dao.addForm(form_name);
        return "redirect:/Forms";
    }

    @GetMapping("/addStudent:form={form_id}")
    public String getAddStudentForm(@PathVariable("form_id") int form_id, Model model){
        model.addAttribute("form_id", form_id);
        return "addStudent";
    }

    @PostMapping("/addStudent:form={form_id}")
    public String addStudent(@PathVariable("form_id") int form_id,
                             @RequestParam("name") String name,
                             @RequestParam("surname") String surname){
        dao.addStudent(name, surname, form_id);
        return "redirect:/Forms/Students:form=" + form_id;
    }

    @DeleteMapping("/deleteStudent:form={form_id}:student={student_id}")
    public String deleteStudent(@PathVariable("form_id") int form_id, @PathVariable("student_id") int student_id ){
        dao.deleteStudent(student_id);
        return "redirect:/Forms/Students:form=" + form_id ;
    }
    @GetMapping("/editStudent:form={form_id}:student={student_id}")
    public String getEditStudent(@PathVariable("form_id") int form_id, @PathVariable("student_id") int student_id, Model model ){
        StudentNameAndSurname student  = dao.getStudent(student_id);
        model.addAttribute("form_id", form_id);
        model.addAttribute("student_id", student_id);
        model.addAttribute("student", student);

        return "editStudent" ;
    }
    @PatchMapping("editStudent:form={form_id}:student={student_id}")
    public String editStudent(@PathVariable("form_id") int form_id, @PathVariable("student_id") int student_id, @RequestParam("name") String name,
                              @RequestParam("surname") String surname){
        dao.editStudentNameSurname(name, surname, student_id);

        return "redirect:/Forms/Students:form=" + form_id ;
    }

    @GetMapping("/addSubject:form={form_id}")
    public String getAddSubject(@PathVariable("form_id") int form_id, Model model){
        model.addAttribute("form_id", form_id);
        return "addSubject";
    }

    @GetMapping("/form={form_id}/Subjects")
    public String FormSubjects(@PathVariable("form_id") int form_id, Model model){
        StudentSubjectsAndIds res = dao.getFormSubjects(form_id);
        model.addAttribute("subjects_names", res.getSubject_names());
        model.addAttribute("subjects_ids", res.getSubject_ids());
        model.addAttribute("res", res);
        return "FormSubjects";
    }

    @PostMapping("/addSubject:form={form_id}")
    public String addSubject(@PathVariable("form_id") int form_id, @RequestParam("subject_name") String subject_name, Model model) {
        dao.addSubject(subject_name, form_id);
        return "redirect:/Forms/form=" + form_id + "/Subjects";
    }

    @DeleteMapping("/deleteSubject:form={form_id}:subject={subject_id}")
    public String deleteSubject(@PathVariable("subject_id") int subject_id,
                                @PathVariable("form_id") int form_id){
        dao.deleteSubject(subject_id);
        return "redirect:/Forms/form=" + form_id + "/Subjects";
    }

    @GetMapping("/editSubject:form={form_id}:subject={subject_id}")
    public String getEditSubject(@PathVariable("subject_id") int subject_id,
                                 @PathVariable("form_id") int form_id, Model model){

        String subject_name = dao.getSubjectName(subject_id);
        model.addAttribute("subject_id", subject_id);
        model.addAttribute("form_id", form_id);
        model.addAttribute("subject_name", subject_name);
        return "editSubject";
    }

    @PatchMapping("/editSubject:form={form_id}:subject={subject_id}")
    public String editSubject(@PathVariable("subject_id") int subject_id,
                              @PathVariable("form_id") int form_id,
                              @RequestParam("subject_name") String subject_name){

        dao.editSubject(subject_id, subject_name);

        return "redirect:/Forms/form=" + form_id + "/Subjects";
    }




    }
