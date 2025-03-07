package com.ashish.activitylifecycleplayground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ashish.activitylifecycleplayground.databinding.ActivityMainBinding
import java.io.File
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var seconds = 0
    lateinit var timer: Timer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            showDialog()
        }

        onBackPressedDispatcher.addCallback {
            showDialog()
        }
        binding.buttonSave.setOnClickListener { saveMsg() }
        binding.savedMsg.text = savedInstanceState?.
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val saveMessage = binding.savedMsg.text.toString()
        outState.putString("savedMessage",saveMessage)
    }

    private fun saveMsg() {
        val userMessage = binding.edit.text
        File(filesDir, "user message.txt").writeText(userMessage.toString())
        binding.savedMsg.text =
            "Your message has been saved! \n\n Message Preview: \n\n$userMessage"
        binding.edit.setText("")
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onResume() {
        super.onResume()
        // Make a counter for number of seconds user has looked at the app
        timer = fixedRateTimer(period = 1000L) {
            runOnUiThread {
                seconds++
                binding.refreshStatus.text = "$seconds seconds"
            }
        }
    }

    fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Warning!")
            .setMessage("You are about to leave the app. Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("More info") { dialog, _ ->
                Toast.makeText(
                    this,
                    "This is where the more info screen could be!",
                    Toast.LENGTH_LONG
                ).show()
                dialog.dismiss()
            }
            .show()
    }

}