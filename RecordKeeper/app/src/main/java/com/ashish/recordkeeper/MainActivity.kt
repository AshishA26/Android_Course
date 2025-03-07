package com.ashish.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ashish.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Anonymous class that implements the interface View.onClickListener right here, in the main activity.
//        binding.buttonCycling.setOnClickListener(object:View.OnClickListener{
//            override fun onClick(v: View?) {
//
//            }
//        })

        // Doing alt enter, you get back to where we started for button click listeners
//        binding.buttonCycling.setOnClickListener { }

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.reset_running -> {
            Toast.makeText(this, "Clicked the Reset Running menu item", Toast.LENGTH_SHORT)
                .show()
            true
        }

        R.id.reset_cycling -> {
            Toast.makeText(this, "Clicked the Reset Cycling menu item", Toast.LENGTH_SHORT)
                .show()
            true
        }

        R.id.reset_all -> {
            Toast.makeText(this, "Clicked the Reset All menu item", Toast.LENGTH_SHORT).show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, RunningFragment())
        }
        return true
    }

    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, CyclingFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_cycling -> onCyclingClicked()
        R.id.nav_running -> onRunningClicked()
        else -> false
    }
}