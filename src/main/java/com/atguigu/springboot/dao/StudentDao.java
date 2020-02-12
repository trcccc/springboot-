package com.atguigu.springboot.dao;

import ch.qos.logback.classic.spi.STEUtil;
import com.atguigu.springboot.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Student student){
        String sql = "INSERT INTO student (ID,name,idNum,college,specialty) VALUES (?,?,?,?,?)";
        Object args[] = {student.getId(),student.getName(),student.getIdNum(),student.getCollege(),student.getSpecialty()};

        return jdbcTemplate.update(sql, args);
    }


    public List<Map<String, Object>> getAllStudents(){
        String sql = "select * from student";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int delete(Student student) {
        String sql = "delete from student where ID = ?";

        return jdbcTemplate.update(sql,student.getId());
    }

    public int update(Student student) {
        String sql = "UPDATE student SET ID= ?,name = ?,idNum = ?,college= ?,specialty= ? WHERE ID = ?";
        int result = jdbcTemplate.update(sql, student.getId(),student.getName(),student.getIdNum(),student.getCollege(),student.getSpecialty(),student.getId());
        return result;
    }

    public List<Map<String, Object>>  findByDepartment(String department){
        String sql = "select * from student where college = ?";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,department);
        return list;
    }

    public Student findById(String id) {
        String sql = "select * from student where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setCollege(resultSet.getString("college"));
                student.setSpecialty(resultSet.getString("specialty"));
                student.setIdNum(resultSet.getString("idNum"));
                student.setPsw(resultSet.getString("psw"));
                System.out.println("findbyid"+student);
                return student;
            }
        });
    }
    public void batchdelete(String id) {
        String sql = "DELETE FROM student WHERE Id =?";
        final String[] noticeid = id.split(",");
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, noticeid[i]);
                    }

            public int getBatchSize() {
                        return noticeid.length;
                    }
                }
        );
    }









}
