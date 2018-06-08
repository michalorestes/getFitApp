package com.jds.fitnessjunkiess.getfitapp.Entities;

public class Exercise {

    private int id;
    private String name;
    private String picture;
    private String instructions;
    private String type;
    private String muscleGroups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getMuscleGroups() {
        System.out.println(this.muscleGroups);
        return this.muscleGroups;
    }
    public void setMuscleGroups(String muscleGroups) {
        this.muscleGroups = muscleGroups;
    }
    @Override
    public String toString(){
        return "\nID: " + getId() + " " +
                "Name: " + getName() + " " +
                "Picture URL: " + getPicture() + " " +
                "Instructions: " + getInstructions() + " " +
                "Type: " + getType();
    }


}
