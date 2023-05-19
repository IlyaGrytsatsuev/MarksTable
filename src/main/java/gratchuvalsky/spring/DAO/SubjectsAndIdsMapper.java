package gratchuvalsky.spring.DAO;

import gratchuvalsky.spring.Model.StudentSubjectsAndIds;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectsAndIdsMapper implements RowMapper<StudentSubjectsAndIds> {
    StudentSubjectsAndIds res = new StudentSubjectsAndIds();
    @Override
    public StudentSubjectsAndIds mapRow(ResultSet rs, int rowNum) throws SQLException {
        res.addSubject(rs.getInt("id"), rs.getString("subject_name"));
        return res;
    }
}
