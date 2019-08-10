package com.ascending.training.model;

import javax.persistence.*;

@Entity
@Table(name = "pets")

public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "owner_id")
    private long owner_id;

    @Column(name = "pet_name")
    private String pet_name;

    @Column(name = "type")
    private String type;

    @Column(name = "color")
    private String color;

    @Column(name = "breed")
    private String breed;

    @Column(name = "age")
    private int age;




    public long getId(){
        return id;
    }
    public long getOwnerid(){
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
    public int getAge(){
        return age;
    }



    public void setId(long id){

        this.id = id;
    }
    public void setOwnerid(long id){

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
    public void setAge(int age){
        this.age = age;
    }
}
