package com.atguigu.springboot.dao;

import com.atguigu.springboot.entities.Family;
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
public class FamilyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Family family){
        String sql = "INSERT INTO family (ID,name,idNum,psw) VALUES (?,?,?,?)";
        Object args[] = {family.getId(),family.getName(),family.getIdNum(),123456};

        return jdbcTemplate.update(sql, args);
    }



    public List<Map<String, Object>>  findByDepartment(String department){
        String sql = "select * from family where college = ?";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,department);
        return list;
    }

    public List<Map<String, Object>> getAll(){
        String sql = "select * from family";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int delete(Family family) {
        String sql = "delete from family where ID = ?";

        return jdbcTemplate.update(sql,family.getId());
    }

    public int update(Family family) {
        String sql = "UPDATE family SET ID= ?,name = ?,idNum = ? WHERE ID = ?";
        int result = jdbcTemplate.update(sql, family.getId(),family.getName(),family.getIdNum(),family.getId());
        return result;
    }

    public Family findById(String id) {
        String sql = "select * from family where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Family>() {
            @Override
            public Family mapRow(ResultSet resultSet, int i) throws SQLException {
                Family family = new Family();
                family.setId(resultSet.getString("id"));
                family.setName(resultSet.getString("name"));
                family.setIdNum(resultSet.getString("idNum"));
                family.setPsw(resultSet.getString("psw"));
                System.out.println(family);
                return family;
            }
        });
    }





}
