package com.ascending.training.model;

public class Users {
    private int id;
    private String fullname;
    private String password;
    private String email;
    private String regis_date;
    private String pet_type;
    private int pet_num;

    public int getId(){
        return id;
    }
    public String getName(){
        return fullname;
    }
    public String getPwd(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public String getDate(){
        return regis_date;
    }
    public String getType(){
        return pet_type;
    }
    public int getNum(){
        return pet_num;
    }


    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.fullname = name;
    }
    public void setPwd(String i){
        this.password = i;
    }
    public void setEmail(String i){
        this.email = i;
    }
    public void setDate(String i){
        this.regis_date = i;
    }
    public void setType(String i){
        this.pet_type = i;
    }
    public void setNum(int i){
        this.pet_num = i;
    }
}
