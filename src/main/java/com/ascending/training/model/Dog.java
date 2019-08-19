package com.ascending.training.model;

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
    private char spay_neuter;

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




    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "pet_id",referencedColumnName = "id")
    private Pet pet;

    public Pet getPet(){return pet;}
    public void setPet(Pet u){
        this.pet = u;
    }



    public long getId() {
        return id;
    }

//    public long getOwnerId() {
//        return owner_id;
//    }

    public String getName() {
        return name;
    }

    public char getSpayNeuter() {
        return spay_neuter;
    }

    public char getRabies() {
        return rabies;
    }

    public char getDistemper() {
        return distemper;
    }

    public char getParvo() {
        return parvo;
    }

    public char getAdenovirus() {
        return adenovirus;
    }

    public char getBordetella() {
        return bordetella;
    }




//    public void setId(long i) {
//        this.id = i;
//    }

//    public void setOwnerId(long i) {
//        this.owner_id = i;
//    }

    public void setName(String i) {
        this.name = i;
    }

    public void setSpayNeuter(char i) {
        this.spay_neuter = i;
    }

    public void setRabies(char i) {
        this.rabies = i;
    }

    public void setDistemper(char i) {
        this.distemper = i;
    }

    public void setParvo(char i) {
        this.parvo = i;
    }

    public void setAdenovirus(char i) {
        this.adenovirus = i;
    }

    public void setBordetella(char i) {
        this.bordetella = i;
    }

//    public int getDog_id() {
//        return dog_id;
//    }

//    public void setDog_id(int dog_id) {
//        this.dog_id = dog_id;
//    }
}
