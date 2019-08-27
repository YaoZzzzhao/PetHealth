package com.ascending.training.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //按顺序自动产生id（只能自动产生integer）
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "regis_date")
    private Date regisDate;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "pet_num")
    private int petNum;


//    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Pet> pets;


    public Set<Pet> getPets(){
        try{
            int size = pets.size();
        }catch(Exception e){
            return null;
        }
        return pets;
    }
    public void setPets(Set<Pet> p){this.pets = p;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer users = (Customer) o;
        return id == users.id &&
                fullName.equals(users.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(Date regisDate) {
        this.regisDate = regisDate;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getPetNum() {
        return petNum;
    }

    public void setPetNum(int petNum) {
        this.petNum = petNum;
    }

    @Override
    public String toString() {
        return id + "," + fullName + "," + email + "," + regisDate + "," + petNum;
    }
}
