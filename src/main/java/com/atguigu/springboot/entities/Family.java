package com.atguigu.springboot.entities;


public class Family {

  private String id;
  private String name;
  private String idNum;
  private String psw;
  public Family(){

  }

  public Family(String id, String name, String idNum) {
    this.id = id;
    this.name = name;
    this.idNum = idNum;
  }

  public String getPsw(){return psw;};

  public void setPsw(String psw){this.psw=psw;};

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

  @Override
  public String toString() {
    return "Family{" +
            "id=" + id +
            ", Name='" + name + '\'' +

            ", idNum=" + idNum +
            '}';
  }

}
