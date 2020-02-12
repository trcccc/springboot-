package com.atguigu.springboot.dao;

import com.atguigu.springboot.entities.Family;
import com.atguigu.springboot.entities.Num;
import com.atguigu.springboot.entities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class NumDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int deCrease() {
        String sql = "UPDATE num SET Num =?";
        int result = jdbcTemplate.update(sql, Integer.parseInt(findById().getNum())-1);
        return result;
    }
    public int inCrease(Integer addnum) {
        String sql = "UPDATE num SET Num =?";
        int result = jdbcTemplate.update(sql, Integer.parseInt(findById().getNum())+addnum);
        return result;
    }
    public Num findById() {
        String sql = "select * from num ";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Num>() {
            @Override
            public Num mapRow(ResultSet resultSet, int i) throws SQLException {
                Num num = new Num();
                num.setNum(resultSet.getString("num"));
                System.out.println("findbyid" + num);
                return num;
            }
        });
    }



}
