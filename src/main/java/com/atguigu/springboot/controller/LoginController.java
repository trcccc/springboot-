package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.AdminDao;
import com.atguigu.springboot.dao.FamilyDao;
import com.atguigu.springboot.dao.StudentDao;
import com.atguigu.springboot.dao.TeacherDao;
import com.atguigu.springboot.entities.Admin;
import com.atguigu.springboot.entities.Family;
import com.atguigu.springboot.entities.Student;
import com.atguigu.springboot.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    AdminDao adminDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    FamilyDao familyDao;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("user") String user,
                        Model model,
                        Map<String,Object>map, HttpSession session){
            try{
                Admin admin= adminDao.findById(username);
                if(admin!=null&&admin.getPsw().equals(password)&&"admin".equals(user)){
                    //登陆成功
                    session.setAttribute("loginUser",username);
                    List<Map<String, Object>> list=studentDao.getAllStudents();
                    model.addAttribute("emps1",list);

                    return "emp/list";

                }
                else {
                    //登陆失败
                    map.put("msg", "用户名密码错误");
                    return "login";
                }
            }catch (Exception e){
                try{
                    Student student=studentDao.findById(username);
                    if (student!=null&& student.getPsw().equals(password)&&"user".equals(user)){
                        session.setAttribute("loginUser",username);


                        model.addAttribute("emp",student);
                        System.out.println("调用成功"+student);
                        return "/student";

                    }
                    else {
                        //登陆失败
                        map.put("msg", "用户名密码错误");
                        return "login";
                    }
                }catch (Exception r){

                    try {
                        Teacher teacher=teacherDao.findById(username);
                        System.out.println("教师登陆："+teacher);
                        if(teacher!=null&& teacher.getPsw().equals(password)&&"user".equals(user)){
                            System.out.println("教师登陆："+teacher.getPsw());
                            session.setAttribute("loginUser",username);

                            model.addAttribute("emp",teacher);
                            System.out.println("调用成功"+teacher);
                            return "teacher";

                        }
                        else {
                            //登陆失败
                            map.put("msg", "用户名密码错误");
                            return "login";
                        }
                    }catch (Exception e1){
                        try {
                            Family family=familyDao.findById(username);
                            System.out.println("教师登陆："+family);
                            if(family!=null&& family.getPsw().equals(password)&&"user".equals(user)){
                                System.out.println("教师登陆："+family.getPsw());
                                session.setAttribute("loginUser",username);
                                model.addAttribute("emp",family);
                                System.out.println("调用成功"+family);
                                return "family";
                            }
                            else {
                                //登陆失败
                                map.put("msg", "用户名密码错误");
                                return "login";
                            }
                        }catch (Exception e2){
                            map.put("msg", "用户名密码错误");
                        }
                    }
                    map.put("msg", "用户名密码错误");
                    return "login";
                }

            }






    }


}
