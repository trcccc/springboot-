package com.atguigu.springboot.entities;


public class Student {

  private Long id;
  private String name;
  private String idNum;
  private String college;
  private String specialty;
  private String psw;

  public Student() {
  }

  public Student(Long id, String name, String idNum, String college, String specialty) {
    this.id = id;
    this.name = name;
    this.idNum = idNum;
    this.college = college;
    this.specialty = specialty;
  }
  public Student(Long id, String name, String idNum, String college, String specialty,String psw) {
    this.id = id;
    this.name = name;
    this.idNum = idNum;
    this.college = college;
    this.specialty = specialty;
    this.psw=psw;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPsw(){return psw; };

  public void setPsw(String psw){this.psw=psw; };


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getIdNum() {
    return idNum;
  }

  public void setIdNum(String idNum) {
    this.idNum = idNum;
  }


  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }


  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", Name='" + name + '\'' +
            ", college='" + college + '\'' +
            ", specialty=" + specialty +
            ", idNum=" + idNum +
            ", psw=" + psw +
            '}';
  }

}
