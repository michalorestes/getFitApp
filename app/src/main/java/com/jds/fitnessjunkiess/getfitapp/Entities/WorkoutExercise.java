package com.jds.fitnessjunkiess.getfitapp.Entities;

public class WorkoutExercise extends Exercise {
    private int sets;
    private int reps;
    private int exerciseAssignmentId;

    public int getExerciseAssignmentId() {
        return exerciseAssignmentId;
    }

    public void setExerciseAssignmentId(int exerciseAssignmentId) {
        this.exerciseAssignmentId = exerciseAssignmentId;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
