package gratchuvalsky.spring.DAO;

import gratchuvalsky.spring.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.sql.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class MarksTablesDao {

    private JdbcTemplate jdbcTemplate;
    private SchoolForm schoolForm;
    private static int formId = 0;
    private Student student;
    private static int studentId = 0;
    private Subject subject;
    private static int subjectId = 0;

    private List<SchoolForm> formsList;


    @Autowired
    public MarksTablesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        schoolForm = null;
        student = null;
        subject = null;
        formsList = new ArrayList<>();
    }


    public void addForm(String form_name) {
        jdbcTemplate.update("insert into forms(form_name) values(?)", form_name);
    }

    public void deleteForm(int form_id){
        jdbcTemplate.update("delete from forms where id = ?", form_id);
    }

    public void editForm(int form_id, String name){
        jdbcTemplate.update("update forms set form_name = ? where id = ?", name, form_id);
    }

    public void addStudent(String name, String surname, int form_id) {
        jdbcTemplate.update("insert into students(name, surname, form_id) VALUES(?,?,?) ", name, surname, form_id);
    }

    public void editStudentNameSurname(String name, String surname, int student_id){
        jdbcTemplate.update("update students set name = ?, surname = ? where id = ?", name, surname, student_id);
    }

    public void deleteStudent(int student_id){
        jdbcTemplate.update("delete from students where id = ?", student_id);
    }


    public void addSubject(String subject_name, int form_id) {
        int subject_id = jdbcTemplate.queryForObject("select nextval ('subjects_subject_id_seq')", Integer.class);
        jdbcTemplate.update("insert into subjects(subject_name) values(?)", subject_name);
        jdbcTemplate.update("insert  into subjectsandforms(subject_id, form_id) values(?,?)", subject_id, form_id);
    }

    public void deleteSubject(int subject_id){
        jdbcTemplate.update("delete where id = ?", new Object[]{subject_id});
    }

    public void editMark(int mark_id, int mark_value){
        jdbcTemplate.update("update marks set mark_value = ? where id = ?", mark_value, mark_id);
    }
    public void add_mark(Mark mark) throws SQLException {
        //Date date = new Date(System.currentTimeMillis());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO marks (mark_value, subject_id, date) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, mark.getMark_value());
            stmt.setInt(2, mark.getSubject_id());
            stmt.setDate(3, mark.getDate());
            return stmt;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();

        int mark_id = (Integer)keys.get("id");
        //int mark_id = jdbcTemplate.queryForObject("select currval ('marks_mark_id_seq')", Integer.class);

        jdbcTemplate.update("insert into studentsandmarks(student_id, mark_id)VALUES (?,?)", mark.getStudent_id(), mark_id);
    }


    public void getSchoolFormDataFromDB(int form_id) {
        List<Student> students = jdbcTemplate.query("select student_id, name, surname,  form_id from  students where form_id = ?", new Object[]{form_id}, new BeanPropertyRowMapper<Student>(Student.class));
        String form_name = jdbcTemplate.queryForObject("select form_name from forms where form_id = ?", String.class, form_id);
        SchoolForm form = new SchoolForm(form_id, form_name);
        form.setStudents(students);
        formsList.add(form);
    }

    public List<FormAndId> getSchoolFormsList() {
        List<FormAndId> forms_list = jdbcTemplate.query("select id, form_name from forms", new BeanPropertyRowMapper(FormAndId.class));
        return forms_list;
    }

    public List<StudentNameAndSurname> getStudentsList(int form_id) {

        List<StudentNameAndSurname> students_list = jdbcTemplate.query("select id, form_id, name, surname " +
                "from students where form_id = ?",
                new Object[]{form_id}, new BeanPropertyRowMapper(StudentNameAndSurname.class));

        return students_list;
    }

    public SubjectsAndIds getFormSubjects(int form_id){
        SubjectsAndIds res = jdbcTemplate.query("select id, subject_name from subjects s " +
                "join subjectsandforms sf on s.id = sf.subject_id  " +
                "where sf.form_id = ?", new Object[]{form_id},
                new SubjectsAndIdsMapper()).stream().findAny().orElse(new SubjectsAndIds());
        res.setForm_id(form_id);

        return res;

    }
    public StudentSubjectsList getStudentSubjects(int student_id, int form_id) {

        StudentSubjectsList subjectsList = jdbcTemplate.query("select name, surname from students s where id = ?",
                new Object[]{student_id}, new RowMapper<StudentSubjectsList>() {
            @Override
            public StudentSubjectsList mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentSubjectsList subjectsList = new StudentSubjectsList();
                subjectsList.setStudentName(rs.getString("name"));
                subjectsList.setStudentSurname(rs.getString("surname"));

                return subjectsList;
            }
        }).stream().findAny().orElse(null);

        subjectsList.setStudent_id(student_id);
        subjectsList.setForm_id(form_id);

        List<Integer> subjects_ids = jdbcTemplate.query("select id " +
                        "from subjects sub " +
                        "join subjectsandforms subf " +
                        "on sub.id = subf.subject_id " +
                        "where form_id = ? order by subject_name",
                new Object[]{form_id}, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getInt("id");
                    }
                });

        List<String> subjects_names = jdbcTemplate.query("select subject_name " +
                        "from subjects sub " +
                        "join subjectsandforms subf " +
                        "on sub.id = subf.subject_id " +
                        "where form_id = ? order by subject_name",
                new Object[]{form_id}, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("subject_name");
                    }
                });


        subjectsList.setSubjects_names(subjects_names);
        subjectsList.setSubjects_ids(subjects_ids);

        return subjectsList;
    }

    public Subject getSubjectMarks(int student_id, int subject_id){

        Subject subject = jdbcTemplate.query("select mark_value, date, id from marks m " +
                        "join studentsandmarks sm on m.id = sm.mark_id " +
                        " where student_id = ? and subject_id = ? order by m.date",
                new Object[]{student_id, subject_id}, new SubjectMapper()).stream().findAny().orElse(null);

        if (subject!=null) {
            String name = jdbcTemplate.queryForObject("select subject_name from subjects where id = ?", new Object[]{subject_id}, String.class);
            subject.setName(name);
        }
        else {
            subject = new Subject();

        }
        subject.setId(subject_id);
        subject.setStudent_id(student_id);
        return subject;
    }

    public void deleteMark(int mark_id){
        jdbcTemplate.update("delete from marks where id = ?", mark_id);

    }



}

