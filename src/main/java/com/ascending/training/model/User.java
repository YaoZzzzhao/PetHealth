package com.ascending.training.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //按顺序自动产生id（只能自动产生integer）
    private long id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "regis_date")
    private Date regis_date;

    @Column(name = "pet_type")
    private String pet_type;

    @Column(name = "pet_num")
    private int pet_num;



    public long getId(){
        return id;
    }
    public String getName(){
        return full_name;
    }
    public String getPwd(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public Date getDate(){
        return regis_date;
    }
    public String getType(){
        return pet_type;
    }
    public int getNum(){
        return pet_num;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return id == users.id &&
                full_name.equals(users.full_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, full_name);
    }





    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.full_name = name;
    }
    public void setPwd(String i){
        this.password = i;
    }
    public void setEmail(String i){
        this.email = i;
    }
    public void setDate(Date i){
        this.regis_date = i;
    }
    public void setType(String i){
        this.pet_type = i;
    }
    public void setNum(int i){
        this.pet_num = i;
    }
}
