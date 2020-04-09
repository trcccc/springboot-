package com.atguigu.springboot.controller;


import com.atguigu.springboot.dao.*;
import com.atguigu.springboot.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    AdminDao adminDao;

    //查询信息
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


    //申请卡片
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
    public String addTea(@PathParam("id") String id,Request request, Map<String,Object>map, Model model){
        //来到学生卡列表

        System.out.println("保存的信息:"+request);
        //保存
        try {
            requestDao.save(request);
            map.put("msg", "提交成功，等待审批");
            Teacher teacher=teacherDao.findById(id);
            model.addAttribute("emp",teacher);

        }catch (Exception e){
            map.put("msg", "已提交，请耐心等待");
            Teacher teacher=teacherDao.findById(id);
            model.addAttribute("emp",teacher);
        }

        return "teacher";
    }
    @PostMapping("/fam2")
    public String addFam(@PathParam("id") String id,Request request, Map<String,Object>map, Model model){
        //来到学生卡列表

        System.out.println("保存的信息:"+request);
        //保存
        try {
            requestDao.save(request);
            map.put("msg", "提交成功，等待审批");
            Family family=familyDao.findById(id);
            model.addAttribute("emp",family);
        }catch (Exception e){
            map.put("msg", "已提交，请耐心等待");
            Family family=familyDao.findById(id);
            model.addAttribute("emp",family);
        }

        return "family";
    }

    @GetMapping("/changepsw")
    public String stuPsw(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();
//        student.setPsw();


        return "change";
    }

    @GetMapping("/changepswT")
    public String teaPsw(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();
//        student.setPsw();


        return "changeTea";
    }

    @GetMapping("/changepswF")
    public String famPsw(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();
//        student.setPsw();


        return "changeFam";
    }
    @GetMapping("/changepswA")
    public String admPsw(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();
//        student.setPsw();


        return "emp/changeAdm";
    }

//修改密码
    @PostMapping("/change")
    public String changePsw(@RequestParam("newpsw") String psw,@RequestParam("renewpsw") String renewpsw,Model model,Map<String,Object>map){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String id=request.getSession().getAttribute("loginUser").toString();

        try {
            Student list=studentDao.findById(id);
            list.setPsw(renewpsw);
            if (psw.equals(renewpsw)){
                studentDao.changepsw(list,renewpsw);
                map.put("msg", "修改成功！");
            }
            else
            {
                map.put("msg", "先后输入密码不相同，请重新输入");
            }
            System.out.println(list);
            return "change";
        }
        catch (Exception e){
            try{
                Teacher list=teacherDao.findById(id);
                list.setPsw(renewpsw);
                if (psw.equals(renewpsw)){
                    teacherDao.changepsw(list,renewpsw);
                    map.put("msg", "修改成功！");
                }
                else
                {
                    map.put("msg", "先后输入密码不相同，请重新输入");
                }
                System.out.println(list);
                return "changeTea";
            }
            catch (Exception e1){
                try{
                    Family list=familyDao.findById(id);
                    list.setPsw(renewpsw);
                    if (psw.equals(renewpsw)){
                        familyDao.changepsw(list,renewpsw);
                        map.put("msg", "修改成功！");
                    }
                    else
                    {
                        map.put("msg", "先后输入密码不相同，请重新输入");
                    }
                    System.out.println(list);
                    return "changeFam";
                }
                catch (Exception e2)
                {
                    Admin list=adminDao.findById(id);
                    list.setPsw(renewpsw);
                    if (psw.equals(renewpsw)){
                        adminDao.changepsw(list,renewpsw);
                        map.put("msg", "修改成功！");
                    }
                    else
                    {
                        map.put("msg", "先后输入密码不相同，请重新输入");
                    }
                    System.out.println(list);
                    return "emp/changeAdm";
                }


            }

        }








    }


}
