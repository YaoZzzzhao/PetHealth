package com.ascending.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pets")

public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(View.Pet.class)
    private long id;

    @JsonView(View.Pet.class)
    @Column(name = "name")
    private String name;

    @JsonView(View.Pet.class)
    @Column(name = "type")
    private String type;

    @JsonView(View.Pet.class)
    @Column(name = "color")
    private String color;

    @JsonView(View.Pet.class)
    @Column(name = "breed")
    private String breed;

    @JsonView(View.Pet.class)
    @Column(name = "age")
    private int age;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User user;

    public User getUser(){
        try{
            String name = user.getFullName();
        }catch(Exception e){
            return null;
        }
        return user;}
    public void setUser(User u){
        this.user = u;
    }


//    @JsonIgnore
    @JsonView(View.CatNDog.class)
    @OneToMany(mappedBy = "pet", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Cat> cats;


    public Set<Cat> getCats(){
        try{
            int size = cats.size();
        }catch(Exception e){
            return null;
        }
        return cats;
    }
    public void setCats(Set<Cat> c){this.cats = c;}

//    @JsonIgnore
    @JsonView(View.CatNDog.class)
    @OneToMany(mappedBy = "pet", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Dog> dogs;

    public Set<Dog> getDogs(){
        try{
            int size = dogs.size();
        }catch(Exception e){
            return null;
        }
        return dogs;
    }
    public void setDogs(Set<Dog> d){
        this.dogs = d;
    }




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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
