package com.atguigu.springboot.dao;


import com.atguigu.springboot.entities.Family;
import com.atguigu.springboot.entities.Request;
import com.atguigu.springboot.entities.Student;
import com.atguigu.springboot.entities.Temporary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class RequestDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Request request) {
        String sql = "INSERT INTO request (ID,name,idNum) VALUES (?,?,?)";
        Object args[] = {request.getId(), request.getName(), request.getIdNum()};

        return jdbcTemplate.update(sql, args);
    }

    public int delete(Request request) {
        String sql = "delete from request where ID = ?";

        return jdbcTemplate.update(sql, request.getId());
    }

    public List<Map<String, Object>> getAll() {
        String sql = "select * from request";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Request findById(String id) {
        String sql = "select * from request where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Request>() {
            @Override
            public Request mapRow(ResultSet resultSet, int i) throws SQLException {
                Request request = new Request();
                request.setId(resultSet.getString("id"));
                request.setName(resultSet.getString("name"));
                request.setIdNum(resultSet.getString("idNum"));
                System.out.println("findbyid" + request);
                return request;
            }
        });
    }
}
