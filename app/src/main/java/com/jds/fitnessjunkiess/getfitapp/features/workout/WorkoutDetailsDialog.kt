package com.jds.fitnessjunkiess.getfitapp.features.workout

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutsViewModel
import kotlinx.android.synthetic.main.fragment_dialog_add_workout.*

class WorkoutDetailsDialog : DialogFragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rootView = inflater.inflate(
            R.layout.fragment_dialog_add_workout, container, false
        )
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Create Workout"
        toolbar.setNavigationIcon(R.drawable.baseline_close_white_24dp)
        toolbar.inflateMenu(R.menu.dialog_workout_details_menu)
        toolbar.setNavigationOnClickListener { _ -> dismiss() }
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.save) {
                saveWorkout()
            }
            true
        }
        setHasOptionsMenu(true)

        return rootView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context!!, R.style.AppTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }

    private fun saveWorkout() {
        val workoutsViewModel =
            ViewModelProviders.of(activity!!).get(WorkoutsViewModel::class.java)
        workoutsViewModel.insert(this.getWorkoutData())
        this.dismiss()
    }

    private fun getWorkoutData(): Workout {
        val workout = Workout()
        workout.name = workout_name.text.toString()
        workout.type = this.selectorGroup.selectedValue
        val schedule = ArrayList<String>()

        when(true) {
            this.checkbox_mon.isChecked -> schedule.add("Monday")
            this.checkbox_tue.isChecked -> schedule.add("Tuesday")
            this.checkbox_wed.isChecked -> schedule.add("Wednesday")
            this.checkbox_thu.isChecked -> schedule.add("Thursday")
            this.checkbox_fri.isChecked -> schedule.add("Friday")
            this.checkbox_sat.isChecked -> schedule.add("Saturday")
            this.checkbox_sun.isChecked -> schedule.add("Sunday")
        }

        workout.schedule = schedule.joinToString { "," }

        return workout
    }
}
