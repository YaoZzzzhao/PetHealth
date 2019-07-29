package com.ascending.training.model;

public class Dogs {
    private int dog_id;
    private int owner_id;
    private String dog_name;
    private String spay_neuter;
    private String rabies;
    private String distemper;
    private String parvo;
    private String adenovirus;
    private String bordetella;

    public int getId() {
        return dog_id;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public String getName() {
        return dog_name;
    }

    public String getSpayNeuter() {
        return spay_neuter;
    }

    public String getRabies() {
        return rabies;
    }

    public String getDistemper() {
        return distemper;
    }

    public String getParvo() {
        return parvo;
    }

    public String getAdenovirus() {
        return adenovirus;
    }

    public String getBordetella() {
        return bordetella;
    }

    public void setId(int i) {
        this.dog_id = i;
    }

    public void setOwnerId(int i) {
        this.owner_id = i;
    }

    public void setName(String i) {
        this.dog_name = i;
    }

    public void setSpayNeuter(String i) {
        this.spay_neuter = i;
    }

    public void setRabies(String i) {
        this.rabies = i;
    }

    public void setDistemper(String i) {
        this.distemper = i;
    }

    public void setParvo(String i) {
        this.parvo = i;
    }

    public void setAdenovirus(String i) {
        this.adenovirus = i;
    }

    public void setBordetella(String i) {
        this.bordetella = i;
    }
}
