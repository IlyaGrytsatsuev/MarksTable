package gratchuvalsky.spring.DAO;

import gratchuvalsky.spring.Model.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<Subject>{
    Subject subject = new Subject();

    @Override
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        subject.addMark(rs.getInt("id"), rs.getDate("date"), rs.getInt("mark_value"));
        subject.setId(rs.getInt("id"));

        return subject;
    }
}
