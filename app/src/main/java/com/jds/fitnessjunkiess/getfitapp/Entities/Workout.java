package com.jds.fitnessjunkiess.getfitapp.Entities;


import java.util.ArrayList;
import java.util.List;

public class Workout {
    private Integer id;
    private String name;
    private List<WorkoutExercise> exercises;
    private Integer userId;

    public Workout(){
        this.exercises = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    public Exercise findExercise(Integer exerciseId){
        for (Exercise e : this.getExercises()){
            if (e.getId() == exerciseId){
                return e;
            }
        }

        return null;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Workout){
            return (this.getId()).equals(((Workout) o).getId());
        }

        return false;
    }

    @Override
    public String toString() {
        return "\nName: " + this.getName() + "\n ID: " + this.getId();
    }
}
