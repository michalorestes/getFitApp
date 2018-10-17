package com.jds.fitnessjunkiess.getfitapp.features

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import androidx.navigation.NavController
import com.jds.fitnessjunkiess.getfitapp.R
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseViewModel
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnFragmentActionBarInteractionInterface {
    override fun setToolbarTitle(text: String) {
        supportActionBar!!.title = text
    }

    var exerciseViewModel: ExerciseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myToolbar = findViewById<Toolbar>(R.id.my_toolbar)

        this.exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)

        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        val navController: NavController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
}
