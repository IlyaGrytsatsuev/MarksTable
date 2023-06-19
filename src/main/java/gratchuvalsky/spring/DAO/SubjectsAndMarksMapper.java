package gratchuvalsky.spring.DAO;

import gratchuvalsky.spring.Model.StudentSubjectsList;
import gratchuvalsky.spring.Model.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectsAndMarksMapper implements RowMapper<StudentSubjectsList> {
    StudentSubjectsList res = new StudentSubjectsList();
    Subject subject = new Subject();

    @Override
    public StudentSubjectsList mapRow(ResultSet rs, int rowNum) throws SQLException {
        int subject_id = rs.getInt("subject_id");
        if (subject.isEmpty()) {
            res.addSubject(subject);
            subject.setId(subject_id);
            subject.setName(rs.getString("subject_name"));
        }
        if (subject.getId() == subject_id)
            subject.addMark(rs.getInt("id"), rs.getDate("date"), rs.getInt("mark_value"));
        else{
            subject = new Subject();
            res.addSubject(subject);
            subject.setId(subject_id);
            subject.setName(rs.getString("subject_name"));
            subject.addMark(rs.getInt("id"), rs.getDate("date"), rs.getInt("mark_value"));
        }

        return res;
    }
}
