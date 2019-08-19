package com.ascending.training.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pets")

public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @Column(name = "name")
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "type")
    private String type;

    @Column(name = "color")
    private String color;

    @Column(name = "breed")
    private String breed;

    @Column(name = "age")
    private int age;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User user;

    public User getUser(){return user;}
    public void setUser(User u){
        this.user = u;
    }


    @OneToMany(mappedBy = "pet", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Cat> cats;

    public List<Cat> getCat(){return cats;}
    public void setCat(List<Cat> c){this.cats = c;}

    @OneToMany(mappedBy = "pet", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Dog> dogs;

    public List<Dog> getDog(){return dogs;}
    public void setDog(List<Dog> d){this.dogs = d;}




//    @OneToMany(mappedBy = "pet", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY )
//
//    private List<Dog> dogs;
//
//    public List<Dog> getDog(){return dogs;}
//    public void setDog(List<Dog> d){this.dogs = d;}
//
//    @OneToMany(mappedBy = "pet", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY )
//    private List<Cat> cats;
//
//    public List<Cat> getCat(){return cats;}
//    public void setCat(List<Cat> c){this.cats = c;}





    public long getId(){
        return id;
    }

    public String getName(){

        return name;
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
//    public void setOwnerid(long id){
//
//        this.owner_id = id;
//    }
    public void setName(String name){

        this.name = name;
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


//    public static void main(String[] args) {
//        Pet a = new Pet();
//        a.getUser().getId();
//    }
}
