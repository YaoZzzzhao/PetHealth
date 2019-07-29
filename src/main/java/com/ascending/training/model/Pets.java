package com.ascending.training.model;

public class Pets {
    private int id;
    private int owner_id;
    private String pet_name;
    private String type;
    private String color;
    private String breed;
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
    public long age(){
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
