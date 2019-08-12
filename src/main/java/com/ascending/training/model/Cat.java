package com.ascending.training.model;


import javax.persistence.*;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "owner_id")
    private int owner_id;

    @Column(name = "cat_name")
    private String cat_name;

    @Column(name = "spay_neuter")
    private char spay_neuter;

    @Column(name ="deworm")
    private char deworm;

    @Column(name ="pan")
    private char pan;

    @Column(name = "rhi")
    private char rhi;

    @Column(name = "calici")
    private char calici;

    @Column(name = "rabies")
    private char rabies;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Pet pet;

    public Pet getPet(){return pet;}
    public void setPet(Pet p){
        this.pet = p;
    }




    public String getName() {
        return cat_name;
    }

    public void setName(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getRhi() {
        return rhi;
    }

    public void setRhi(char rhi) {
        this.rhi = rhi;
    }

    public char getDeworm() {
        return deworm;
    }

    public void setDeworm(char deworm) {
        this.deworm = deworm;
    }

    public char getRabies() {
        return rabies;
    }

    public void setRabies(char rabies) {
        this.rabies = rabies;
    }

    public char getCalici() {
        return calici;
    }

    public void setCalici(char calici) {
        this.calici = calici;
    }

    public char getPan() {
        return pan;
    }

    public void setPan(char pan) {
        this.pan = pan;
    }

    public char getSpayNeuter() {
        return spay_neuter;
    }

    public void setSpayNeuter(char spay_neuter) {
        this.spay_neuter = spay_neuter;
    }

    //    public String toString(){
//        return cat_name+calici+panleukopenia;
//    }
}
