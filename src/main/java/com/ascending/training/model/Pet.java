package com.ascending.training.model;

import javax.persistence.*;

@Entity
@Table(name = "pets")

public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "owner_id")
    private int owner_id;

    @Column(name = "pet_name")
    private String pet_name;

    @Column(name = "type")
    private String type;

    @Column(name = "color")
    private String color;

    @Column(name = "breed")
    private String breed;

    @Column(name = "age")
    private long age;




    public int getId(){
        return id;
    }
    public int getOwnerid(){
        return owner_id;
    }
    public String getName(){

        return pet_name;
    }
    public String getColor(){

        return color;
    }
    public String getBreed(){
        return breed;
    }
    public long getAge(){
        return age;
    }
    public void setId(int id){

        this.id = id;
    }
    public void setOwnerid(int id){

        this.owner_id = id;
    }
    public void setName(String name){

        this.pet_name = name;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setBreed(String breed){
        this.breed = breed;
    }
    public void setAge(long age){
        this.age = age;
    }
}
