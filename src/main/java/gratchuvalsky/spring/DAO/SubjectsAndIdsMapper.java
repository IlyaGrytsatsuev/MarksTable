package gratchuvalsky.spring.DAO;

import gratchuvalsky.spring.Model.SubjectsAndIds;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectsAndIdsMapper implements RowMapper<SubjectsAndIds> {
    SubjectsAndIds res = new SubjectsAndIds();
    @Override
    public SubjectsAndIds mapRow(ResultSet rs, int rowNum) throws SQLException {
        res.addSubject(rs.getInt("id"), rs.getString("subject_name"));
        return res;
    }
}
