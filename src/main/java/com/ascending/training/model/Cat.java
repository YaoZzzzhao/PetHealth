package com.ascending.training.model;

public class Cat {
    private int id;
    private int owner_id;
    private String cat_name;
    private String spay_neuter;
    private String deworm;
    private String panleukopenia;
    private String rhinotracheitis;
    private String calici;
    private String rabies;

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public String getName() {
        return cat_name;
    }

    public String getSpayNeuter() {
        return spay_neuter;
    }

    public String getRabies() {
        return rabies;
    }

    public String getDeworm() {

        return deworm;
    }

    public String getPanleukopenia() {

        return panleukopenia;
    }

    public String getRhinotracheitis() {
        return rhinotracheitis;
    }

    public String getCalici() {

        return calici;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setOwnerId(int i) {

        this.owner_id = i;
    }

    public void setName(String i) {

        this.cat_name = i;
    }

    public void setSpayNeuter(String i) {

        this.spay_neuter = i;
    }

    public void setRabies(String i) {
        this.rabies = i;
    }

    public void setDeworm(String i) {

        this.deworm = i;
    }

    public void setPanleukopenia(String i) {

        this.panleukopenia = i;
    }

    public void setRhinotracheitis(String i) {

        this.rhinotracheitis = i;
    }

    public void setCalici(String i) {

        this.calici = i;
    }

    public String toString(){
        return cat_name+calici+panleukopenia;
    }
}
