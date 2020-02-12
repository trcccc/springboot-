package com.atguigu.springboot.entities;


public class Temporary {

  private String id;
  private String name;

  public Temporary() {
  }

  public Temporary(String id, String name, String idNum, String start) {
    this.id = id;
    this.name = name;
    this.idNum = idNum;
    this.start = start;
  }

  private String idNum;
  private String start;


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


  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

}
