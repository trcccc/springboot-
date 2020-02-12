package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.*;
import com.atguigu.springboot.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class EmplyeeController {
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    FamilyDao familyDao;
    @Autowired
    TemporaryDao temporaryDao;
    @Autowired
    RequestDao requestDao;
    @Autowired
    NumDao numDao;

    //假装退出
    @GetMapping("/index")
    public String getout(Model model){

        return "login";
    }
    //查询所有校园卡信息返回列表页面
    @GetMapping("/emps1")
    public String studentlist(Model model){
        List<Map<String, Object>> list=studentDao.getAllStudents();
        model.addAttribute("emps1",list);
        System.out.println(list);
        return "emp/list";
    }

    @GetMapping("/emps2")
    public String teacherlist(Model model){
        List<Map<String, Object>> list=teacherDao.getAllTeachers();
        model.addAttribute("emps2",list);
        return "emp/teacherlist";
    }
    @GetMapping("/emps3")
    public String familylist(Model model){
        List<Map<String, Object>> list=familyDao.getAll();
        model.addAttribute("emps3",list);
        return "emp/familylist";
    }
    @GetMapping("/emps4")
    public String temporarylist(Model model){
        List<Map<String, Object>> list=temporaryDao.getAll();
        model.addAttribute("emps4",list);
        return "emp/temporarylist";
    }
    @GetMapping("/emps5")
    public String requestlist(Model model){
        List<Map<String, Object>> list=requestDao.getAll();
        String num=numDao.findById().getNum();
        model.addAttribute("emps5",list);
        model.addAttribute("num",num);
        System.out.println(list);
        return "emp/requestlist";
    }
    //查询单个
    @PostMapping("/select/stu")
    public String selectStu(@RequestParam("id") String id,
                            @RequestParam("department") String depa,Model model){
        try {
            Student list=studentDao.findById(id);
            model.addAttribute("emps1",list);
            System.out.println(depa);
        }catch (Exception e){

            List<Map<String, Object>> list=studentDao.findByDepartment(depa);
            model.addAttribute("emps1",list);

        }

        return "emp/list";
    }
    @PostMapping("/select/tea")
    public String selectTea(@RequestParam("id") String id,@RequestParam("department") String depa,Model model){
        try {
            Teacher list=teacherDao.findById(id);
            model.addAttribute("emps2",list);
            System.out.println(list);
        }catch (Exception e){
            List<Map<String, Object>> list=teacherDao.findByDepartment(depa);
            model.addAttribute("emps2",list);

        }

        return "emp/teacherlist";
    }
    @PostMapping("/select/fam")
    public String selectFam(@RequestParam("id") String id,@RequestParam("department") String depa,Model model){
        try {
            Family list=familyDao.findById(id);
            model.addAttribute("emps3",list);
            System.out.println(list);
        }catch (Exception e){
            List<Map<String, Object>> list=familyDao.findByDepartment(depa);
            model.addAttribute("emps3",list);
        }

        return "emp/familylist";
    }
    @PostMapping("/select/tem")
    public String selectTem(@RequestParam("id") String id,@RequestParam("department") String depa,Model model){
        try {
            Temporary list=temporaryDao.findById(id);
            model.addAttribute("emps4",list);
            System.out.println(list);
        }catch (Exception e){
            List<Map<String, Object>> list=temporaryDao.findByDepartment(depa);
            model.addAttribute("emps4",list);
        }

        return "emp/temporarylist";
    }


    @GetMapping("/emp1")
    public String toAddPage(Model model){

        return "emp/add";
    }

    @GetMapping("/emp2")
    public String toteacherAddPage(Model model){

        return "emp/teacheradd";
    }

    @GetMapping("/emp3")
    public String tofamilyAddPage(Model model){

        return "emp/familyadd";
    }

    @GetMapping("/emp4")
    public String totemporaryAddPage(Model model){

        return "emp/temporaryadd";
    }
    @GetMapping("/emp5")
    public String tonumAddPage(Model model){

        return "emp/numadd";
    }


    //学生卡添加
    @PostMapping("/emp1")
    public String addStu(Student student){
        //来到学生卡列表

        System.out.println("保存的信息:"+student);
        //保存
        System.out.println(studentDao.save(student));
        return "redirect:/emps1";
    }
//    @PostMapping("/select")
//    public String selectStu(@RequestParam("id") String id,Model model){
//        Student student =studentDao.findById(id);
//        model.addAttribute("emp",student);
//
//        System.out.println("查询"+id);
//        return "redirect:/emps1";
//    }
//@GetMapping("/select1")
//public String getStudentFormById(@PathVariable("id") String id,Model model){
//    Student student=studentDao.findById(id);
//    System.out.println("查询"+id);
//    model.addAttribute("emp",student);
//    return "emp/list";
//}



    @PostMapping("/emp2")
    public String addTea(Teacher teacher){
        //来到职工列表
        //保存教工
        teacherDao.save(teacher);
        return "redirect:/emps2";
    }

    @PostMapping("/emp3")
    public String addFam(Family family){
       //家属卡列表
        familyDao.save(family);
        return "redirect:/emps3";
    }

    @PostMapping("/emp4")
    public String addTem(Temporary temporary){
        //来到员工列表

        //保存员工
        temporaryDao.save(temporary);
        return "redirect:/emps4";
    }

    //来到修改页面，查出当前员工
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") String id,Model model){
        Student student=studentDao.findById(id);
        model.addAttribute("emp",student);
        //回到修改页面
        return "emp/add";
    }
//    @GetMapping("/stu/{id}")
//    public String toPage(@PathVariable("id") String id,Model model){
//        Student student=studentDao.findById(id);
//        model.addAttribute("emp",student);
//        //回到修改页面
//        return "emp/add";
//    }

    @GetMapping("/emp2/{id}")
    public String toTeaEditPage(@PathVariable("id") String id,Model model){
        Teacher teacher=teacherDao.findById(id);
        model.addAttribute("emp",teacher);
        //回到修改页面
        return "emp/teacheradd";
    }

    @GetMapping("/emp3/{id}")
    public String toFamEditPage(@PathVariable("id") String id,Model model){
        Family family=familyDao.findById(id);
        model.addAttribute("emp",family);
        //回到修改页面
        return "emp/familyadd";
    }
    @GetMapping("/emp4/{id}")
    public String toTemEditPage(@PathVariable("id") String id,Model model){
        Temporary temporary=temporaryDao.findById(id);
        model.addAttribute("emp",temporary);
        //回到修改页面
        return "emp/temporaryadd";
    }

    //员工修改
    @PutMapping("/emp1")
    public String updateStudent(Student student){
        System.out.println("信息为"+student);
        studentDao.update(student);
        return "redirect:/emps1";
    }
    @PutMapping("/emp2")
    public String updateTeacher(Teacher teacher){
        System.out.println("信息为"+teacher);
        teacherDao.update(teacher);
        return "redirect:/emps2";
    }
    @PutMapping("/emp3")
    public String updateFamily(Family family){

        familyDao.update(family);
        return "redirect:/emps3";
    }
    @PutMapping("/emp4")
    public String updateTemporary(Temporary temporary){

        temporaryDao.update(temporary);
        return "redirect:/emps4";
    }
    @PutMapping("/emp5")
    public String updateNum(Num num){
        System.out.println("入库数量"+num);
        try{
            numDao.inCrease(Integer.parseInt(num.getNum()));
        }catch (Exception e){

        }

        return "redirect:/emps5";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteStudent(@PathVariable("id") String id){
        Student student=studentDao.findById(id);
        studentDao.delete(student);
        return "redirect:/emps1";
    }
//    @DeleteMapping("/emp/many/{checkItem}")
//    public String deleteStudents(@PathVariable("checkItem") String id){
//        Student student=studentDao.findById(id);
//        System.out.println("批量"+id);
//        studentDao.delete(student);
//        return "redirect:/emps1";
//    }
//    @DeleteMapping("/memp/{id}")
//    public String deleteStudents(@PathVariable("id") String id){
//        Student student=studentDao.findById(id);
//        studentDao.batchdelete(id);
//        System.out.println(id);
//        return "redirect:/emps1";
//    }
//    @PostMapping("/emp")
//    public ModelAndView batchdelete(Student student){
//        studentDao.batchdelete(student.getId().toString());
//        System.out.println("批量删除"+student.getId());
//        return new ModelAndView("list");
//    }

    @DeleteMapping("/emp2/{id}")
    public String deleteTeacher(@PathVariable("id") String id){
        Teacher teacher=teacherDao.findById(id);
        teacherDao.delete(teacher);
        return "redirect:/emps2";
    }
    @DeleteMapping("/emp3/{id}")
    public String deleteFamily(@PathVariable("id") String id){
        Family family=familyDao.findById(id);
        familyDao.delete(family);
        return "redirect:/emps3";
    }
    @DeleteMapping("/emp4/{id}")
    public String deleteTemporary(@PathVariable("id") String id){
        Temporary temporary=temporaryDao.findById(id);
        temporaryDao.delete(temporary);
        return "redirect:/emps4";
    }
//审批通过
    @DeleteMapping("/emp5/{id}")
    public String deleteRequest(@PathVariable("id") String id,Model model){
        Request request=requestDao.findById(id);
        if(Integer.parseInt(numDao.findById().getNum())>0)
        {
            requestDao.delete(request);
            numDao.deCrease();
        }else {
            System.out.println("卡片库存为0");
            model.addAttribute("tishi","卡片库存为0，请及时入库");
        }

        return "redirect:/emps5";
    }
    @DeleteMapping("/emp6/{id}")
    public String deleteRequest2(@PathVariable("id") String id,Model model){
        Request request=requestDao.findById(id);
        requestDao.delete(request);


        return "redirect:/emps5";
    }



//////查询学院
//    @PostMapping("/emp")
//    public String selectdepartmentfromstudent(@RequestParam("department") String id,Model model){
//        System.out.println("部门为"+id);
//        return "emp/list";
//
//    }
}
