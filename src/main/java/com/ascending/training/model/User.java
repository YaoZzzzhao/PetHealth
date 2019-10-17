package com.ascending.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //按顺序自动产生id（只能自动产生integer）
    @JsonView(View.User.class)
    private long id;

    @Column(name = "full_name")
    @JsonView(View.User.class)
    private String fullName;

    @Column(name = "password")
    @JsonView(View.User.class)
    private String password;

    @Column(name = "email")
    @JsonView(View.User.class)
    private String email;

    @Column(name = "regis_date")
    @JsonView(View.User.class)
    private Date regisDate;

    @Column(name = "pet_type")
    @JsonView(View.User.class)
    private String petType;

    @Column(name = "pet_num")
    @JsonView(View.User.class)
    private int petNum;


//    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView(View.Pet.class)
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

    //@ManyToMany(mappedBy = "users", cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    @JsonIgnore
    private List<Role> roles;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return id + "," + fullName + "," + email + "," + petNum;
    }
//    Set<User> us = new HashSet<>();
}
