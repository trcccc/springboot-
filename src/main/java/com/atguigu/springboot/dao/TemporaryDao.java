package com.atguigu.springboot.dao;

import com.atguigu.springboot.entities.Family;
import com.atguigu.springboot.entities.Student;
import com.atguigu.springboot.entities.Temporary;
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
public class TemporaryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Temporary temporary){
        String sql = "INSERT INTO temporary (ID,name,idNum,start) VALUES (?,?,?,?)";
        Object args[] = {temporary.getId(),temporary.getName(),temporary.getIdNum(),temporary.getStart()};

        return jdbcTemplate.update(sql, args);
    }
    public List<Map<String, Object>>  findByDepartment(String department){
        String sql = "select * from temporary where college = ?";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,department);
        return list;
    }

    public List<Map<String, Object>> getAll(){
        String sql = "select * from temporary";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int delete(Temporary temporary) {
        String sql = "delete from temporary where ID = ?";

        return jdbcTemplate.update(sql,temporary.getId());
    }

    public int update(Temporary temporary) {
        String sql = "UPDATE temporary SET ID= ?,name = ?,idNum = ?,start=? WHERE ID = ?";
        int result = jdbcTemplate.update(sql, temporary.getId(),temporary.getName(),temporary.getIdNum(),temporary.getStart(),temporary.getId());
        return result;
    }

    public Temporary findById(String id) {
        String sql = "select * from temporary where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Temporary>() {
            @Override
            public Temporary mapRow(ResultSet resultSet, int i) throws SQLException {
                Temporary temporary = new Temporary();
                temporary.setId(resultSet.getString("id"));
                temporary.setName(resultSet.getString("name"));
                temporary.setIdNum(resultSet.getString("idNum"));
                System.out.println(temporary);
                return temporary;
            }
        });
    }


}
