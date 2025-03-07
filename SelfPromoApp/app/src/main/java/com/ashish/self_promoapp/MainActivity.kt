package com.ashish.self_promoapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.ashish.self_promoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Kotlin extensions is deprecated, so I will use viewbindings instead
    private lateinit var binding: ActivityMainBinding //Uses name of xml file in Caps-casing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // uses camel-casing for view, but we do snake-casing in xml file
        binding.buttonPreview.setOnClickListener {
            onPreviewClicked()
        }

        // This is how you make an array in kotlin.
        // The 1st line below, we specify it's a string array
        // The 2nd line below, kotlin infers the type of array.
        // val spinnerValues: Array<String> = arrayOf("our", "array", "values")
        val spinnerValues = arrayOf("Android Dev", "Android Engineer")

        // Adapter is needed for the spinner/dropdown menu
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerValues)
        binding.spinnerJobTitle.adapter = spinnerAdapter

    }

    private fun onPreviewClicked() {
//        Log.d("This is my TAG", "currently contactNameEditText is " + contactNameEditText.toString())

//        // Note, Kotlin is inferring the type of variable b/c of the method called on it
//        val contactName = binding.editTextContactName.text.toString()
//        val contactNumber = binding.editTextContactNumber.text.toString()
//        val myDisplayName = binding.editTextMyDisplayName.text.toString()
//        val includeJunior = binding.checkBoxJunior.isChecked
//        val jobDescription = binding.spinnerJobTitle.selectedItem?.toString()
//        //Safe call above because spinner can return null if no items in the spinner, and thus can't do toString
//        val immediateStart = binding.checkBoxImmediateStart.isChecked
//        val startDate = binding.editTextStartDate.text.toString()

        // Intent to move to preview message activity
        val previewActivityIntent = Intent(this, PreviewActivity::class.java)

//        // This is how you send data from one activity to another, by using intents
//        // You do a putExtra for every piece of data you want to send, and give it some name and your data
//        previewActivityIntent.putExtra("Contact Name", contactName)
//        previewActivityIntent.putExtra("Contact Number", contactNumber)
//        previewActivityIntent.putExtra("My Display Name", myDisplayName)
//        previewActivityIntent.putExtra("Include Junior", includeJunior)
//        previewActivityIntent.putExtra("Job Title", jobDescription)
//        previewActivityIntent.putExtra("Immediate Start", immediateStart)
//        previewActivityIntent.putExtra("Start Date", startDate)

        // Alternatively, make a data class to hold all your info, and then do the above stuff
        val message = Message(
            binding.editTextContactName.text.toString(),
            binding.editTextContactNumber.text.toString(),
            binding.editTextMyDisplayName.text.toString(),
            binding.checkBoxJunior.isChecked,
            binding.spinnerJobTitle.selectedItem?.toString(),
            binding.checkBoxImmediateStart.isChecked,
            binding.editTextStartDate.text.toString()
        )

        // Send object of class through intent by simply making class Serializable
        previewActivityIntent.putExtra("Message", message)

        // Then you start the other activity
        startActivity(previewActivityIntent)
    }
}
