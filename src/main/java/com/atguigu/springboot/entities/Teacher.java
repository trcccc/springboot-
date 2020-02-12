package com.atguigu.springboot.entities;


public class Teacher {

  private String id;
  private String name;
  private String idNum;
  private String college;
  private String psw;

  public Teacher() {
  }

  public Teacher(String id, String name, String idNum, String college) {
    this.id = id;
    this.name = name;
    this.idNum = idNum;
    this.college = college;
  }
  public Teacher(String id, String name, String idNum, String college,String psw) {
    this.id = id;
    this.name = name;
    this.idNum = idNum;
    this.college = college;
    this.psw=psw;
  }



  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


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

  public String getPsw(){return psw;};

  public void setPsw(String psw){this.psw=psw;};

  @Override
  public String toString() {
    return "Teacher{" +
            "id=" + id +
            ", Name='" + name + '\'' +
            ", college='" + college + '\'' +
            ", idNum=" + idNum +
            '}';
  }

}
