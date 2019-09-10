package com.ascending.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "dogs")

public class Dog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(View.CatNDog.class)
    private long id;

//    @Column(name = "owner_id")
//    private long owner_id;
    @JsonView(View.CatNDog.class)
    @Column(name = "name")
    private String name;

    @JsonView(View.CatNDog.class)
    @Column(name = "Spay_neuter")
    private char spayNeuter;

    @JsonView(View.CatNDog.class)
    @Column(name = "Rabies")
    private char rabies;

    @JsonView(View.CatNDog.class)
    @Column(name = "Distemper")
    private char distemper;

    @JsonView(View.CatNDog.class)
    @Column(name = "Parvo")
    private char parvo;

    @JsonView(View.CatNDog.class)
    @Column(name= "Adenovirus")
    private char adenovirus;

    @JsonView(View.CatNDog.class)
    @Column(name = "Bordetella")
    private char bordetella;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id",referencedColumnName = "id")
//    @JoinColumn(name = "dog_id")
    private Pet pet;

    @Override
    public int hashCode(){
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Dog d = (Dog)obj;
        if(this.id != d.id)
            return false;
        if(!this.name.equals(d.name))
            return false;
        return true;
    }


    public Pet getPet(){
        try{
            int size = pet.getAge();
        }catch(Exception e){
            return null;
        }
        return pet;
    }
    public void setPet(Pet u){
        this.pet = u;
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

    public char getRabies() {
        return rabies;
    }

    public void setRabies(char rabies) {
        this.rabies = rabies;
    }

    public char getDistemper() {
        return distemper;
    }

    public void setDistemper(char distemper) {
        this.distemper = distemper;
    }

    public char getParvo() {
        return parvo;
    }

    public void setParvo(char parvo) {
        this.parvo = parvo;
    }

    public char getAdenovirus() {
        return adenovirus;
    }

    public void setAdenovirus(char adenovirus) {
        this.adenovirus = adenovirus;
    }

    public char getBordetella() {
        return bordetella;
    }

    public void setBordetella(char bordetella) {
        this.bordetella = bordetella;
    }
}
