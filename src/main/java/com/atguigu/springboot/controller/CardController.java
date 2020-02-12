package com.atguigu.springboot.controller;


import com.atguigu.springboot.dao.FamilyDao;
import com.atguigu.springboot.dao.RequestDao;
import com.atguigu.springboot.dao.StudentDao;
import com.atguigu.springboot.dao.TeacherDao;
import com.atguigu.springboot.entities.Family;
import com.atguigu.springboot.entities.Request;
import com.atguigu.springboot.entities.Student;
import com.atguigu.springboot.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class CardController {
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    FamilyDao familyDao;
    @Autowired
    RequestDao requestDao;


    @GetMapping("/stu1")
    public String student(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();
        Student student =studentDao.findById(id);
        model.addAttribute("emp",student);
        System.out.println("调用成功"+student);
        return "student";
    }

    @GetMapping("/tea1")
    public String teacher(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();
        Teacher teacher =teacherDao.findById(id);
        model.addAttribute("emp",teacher);
        System.out.println("调用成功"+teacher);
        return "teacher";
    }
    @GetMapping("/fam1")
    public String family(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();
        Family family =familyDao.findById(id);
        model.addAttribute("emp",family);
        System.out.println("调用成功"+family);
        return "family";
    }



    @PostMapping("/stu2")
    public String addStu(@PathParam("id") String id, Request request, Map<String,Object>map, Model model){
        //来到学生卡列表


        //保存
        try {
            requestDao.save(request);
            map.put("msg", "提交成功，等待审批");
            Student student =studentDao.findById(id);
            System.out.println("传值"+id);
            model.addAttribute("emp",student);
        }catch (Exception e){
            map.put("msg", "已提交，请耐心等待");
            Student student =studentDao.findById(id);
            System.out.println("传值"+id);
            model.addAttribute("emp",student);
        }

        return "student";
    }

    @PostMapping("/tea2")
    public String addTea(Request request, Map<String,Object>map){
        //来到学生卡列表

        System.out.println("保存的信息:"+request);
        //保存
        try {
            requestDao.save(request);
            map.put("msg", "提交成功，等待审批");
        }catch (Exception e){
            map.put("msg", "已提交，请耐心等待");
        }

        return "teacher";
    }
    @PostMapping("/fam2")
    public String addFam(Request request, Map<String,Object>map){
        //来到学生卡列表

        System.out.println("保存的信息:"+request);
        //保存
        try {
            requestDao.save(request);
            map.put("msg", "提交成功，等待审批");
        }catch (Exception e){
            map.put("msg", "已提交，请耐心等待");
        }

        return "family";
    }

}
