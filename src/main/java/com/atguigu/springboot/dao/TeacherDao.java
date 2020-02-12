package com.atguigu.springboot.dao;

import com.atguigu.springboot.entities.Student;
import com.atguigu.springboot.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class TeacherDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Teacher teacher){
        String sql = "INSERT INTO teacher (ID,name,idNum,college,psw) VALUES (?,?,?,?,?)";
        Object args[] = {teacher.getId(),teacher.getName(),teacher.getIdNum(),teacher.getCollege(),123456};

        return jdbcTemplate.update(sql, args);
    }


    public List<Map<String, Object>> getAllTeachers(){
        String sql = "select * from teacher";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int delete(Teacher teacher) {
        String sql = "delete from teacher where ID = ?";

        return jdbcTemplate.update(sql,teacher.getId());
    }

    public int update(Teacher teacher) {
        String sql = "UPDATE teacher SET ID= ?,name = ?,idNum = ?,college= ? WHERE ID = ?";
        int result = jdbcTemplate.update(sql, teacher.getId(),teacher.getName(),teacher.getIdNum(),teacher.getCollege(),teacher.getId());
        return result;
    }
    public List<Map<String, Object>>  findByDepartment(String department){
        String sql = "select * from teacher where college = ?";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,department);
        return list;
    }

    public Teacher findById(String id) {
        String sql = "select * from teacher where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
                Teacher student = new Teacher();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setCollege(resultSet.getString("college"));
                student.setIdNum(resultSet.getString("idNum"));
                student.setPsw(resultSet.getString("psw"));
                System.out.println(student);
                return student;
            }
        });
    }

//    public Student getById(int id) {
//        String sql = "select * from student where id = ? ";
//        List<Student> stu = jdbcTemplate.query(sql,new Object[]{id}, new StudentRowMapper());
//        Student student = null;
//        if(!stu.isEmpty()){
//            student = stu.get(0);
//        }
//        return student;
//    }
//
//    class StudentRowMapper implements RowMapper<Student> {
//
//
//        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
//            Student stu = new Student();
//            stu.setId(resultSet.get"id"));
//
//            stu.setName(resultSet.getString("name"));
//            return stu;
//        }
//    }




}
