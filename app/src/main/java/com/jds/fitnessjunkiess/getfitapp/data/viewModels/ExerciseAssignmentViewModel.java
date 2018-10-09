package com.jds.fitnessjunkiess.getfitapp.data.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship;
import com.jds.fitnessjunkiess.getfitapp.data.repositories.ExerciseAssignmentRepository;
import java.util.List;

public class ExerciseAssignmentViewModel extends AndroidViewModel {

  private ExerciseAssignmentRepository exerciseAssignmentRepository;
  private LiveData<List<ExerciseRelationship>> exerciseRelationsData = new MediatorLiveData<>();

  public ExerciseAssignmentViewModel(Application application) {
    super(application);
    this.exerciseAssignmentRepository = new ExerciseAssignmentRepository(application);
  }

  public LiveData<List<ExerciseRelationship>> selectExerciseRelationsData(int workoutId) {
    if (this.exerciseRelationsData.getValue() == null) {
      this.exerciseRelationsData = this.exerciseAssignmentRepository.selectRelations(workoutId);
    }

    return exerciseRelationsData;
  }

  public void insert(ExerciseAssignment exerciseAssignment) {
    this.exerciseAssignmentRepository.insert(exerciseAssignment);
  }

  public void update(ExerciseAssignment exerciseAssignment) {
    this.exerciseAssignmentRepository.update(exerciseAssignment);
  }
}
