package com.ascending.training.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(View.CatNDog.class)
    private long id;

//    @Column(name = "owner_id")
//    private int owner_id;

    @JsonView(View.CatNDog.class)
    @Column(name = "name")
    private String name;

    @JsonView(View.CatNDog.class)
    @Column(name = "Spay_neuter")
    private char spayNeuter;

    @JsonView(View.CatNDog.class)
    @Column(name ="Deworm")
    private char deworm;

    @JsonView(View.CatNDog.class)
    @Column(name ="Panl")
    private char pan;

    @JsonView(View.CatNDog.class)
    @Column(name = "Rhi")
    private char rhi;

    @JsonView(View.CatNDog.class)
    @Column(name = "Calici")
    private char calici;

    @JsonView(View.CatNDog.class)
    @Column(name = "Rabies")
    private char rabies;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Pet pet;

    public Pet getPet(){
        try{
            int size = pet.getAge();
        }catch(Exception e){
            return null;
        }
        return pet;}
    public void setPet(Pet p){
        this.pet = p;
    }


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

    public char getSpayNeuter() {
        return spayNeuter;
    }

    public void setSpayNeuter(char spayNeuter) {
        this.spayNeuter = spayNeuter;
    }

    public char getDeworm() {
        return deworm;
    }

    public void setDeworm(char deworm) {
        this.deworm = deworm;
    }

    public char getPan() {
        return pan;
    }

    public void setPan(char pan) {
        this.pan = pan;
    }

    public char getRhi() {
        return rhi;
    }

    public void setRhi(char rhi) {
        this.rhi = rhi;
    }

    public char getCalici() {
        return calici;
    }

    public void setCalici(char calici) {
        this.calici = calici;
    }

    public char getRabies() {
        return rabies;
    }

    public void setRabies(char rabies) {
        this.rabies = rabies;
    }
}
