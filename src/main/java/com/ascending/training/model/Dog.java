package com.ascending.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "dogs")

public class Dog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

//    @Column(name = "owner_id")
//    private long owner_id;

    @Column(name = "name")
    private String name;

    @Column(name = "Spay_neuter")
    private char spayNeuter;

    @Column(name = "Rabies")
    private char rabies;

    @Column(name = "Distemper")
    private char distemper;

    @Column(name = "Parvo")
    private char parvo;

    @Column(name= "Adenovirus")
    private char adenovirus;

    @Column(name = "Bordetella")
    private char bordetella;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "dog_id",referencedColumnName = "id")
    private Pet pet;

    public Pet getPet(){return pet;}
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
