package com.atguigu.springboot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.atguigu.springboot.entities.Department;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "计科院"));
		departments.put(102, new Department(102, "土建院"));
		departments.put(103, new Department(103, "材料院"));
		departments.put(104, new Department(104, "石工院"));
	}
	
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
	
}
