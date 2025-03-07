package com.ashish.self_promoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ashish.self_promoapp.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {

    private lateinit var message: Message
    private lateinit var messagePreviewText: String

    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayMessage()
        setupButton()

    }

    private fun displayMessage() {
        //        // This activity is opened by an intent made in another activity.
        //        // Android still has access to the intent, thus can do the below.
        //        // This is how you get the extra data we put in. We must ensure the name matches between
        //        // putExtra in the other activity and get_____Extra here.
        //        val contactName = intent.getStringExtra("Contact Name")
        //        val contactNumber = intent.getStringExtra("Contact Number")
        //        val myDisplayName = intent.getStringExtra("My Display Name")
        //        val includeJunior = intent.getBooleanExtra("Include Junior", false)
        //        val jobTitle = intent.getStringExtra("Job Title")
        //        val immediateStart = intent.getBooleanExtra("Immediate Start", false)
        //        val startDate = intent.getStringExtra("Start Date")

        // Alternatively, now use Serialization and class
        message = intent.getSerializableExtra("Message") as Message
        // The "as Message" is Type casting (transforming object from one type to another),
        // ensuring it is of type Message class

        messagePreviewText = """
                Hi ${message.contactName},
                
                My name is ${message.myDisplayName} and I am ${message.getFullJobDescription()}.
                
                I have a portfolio of apps to show my skills on request.
                
                I am able to start a new position ${message.getAvailability()}.
                
                Please get in touch if you have any suitable roles for me.
                
                Thanks and best regards.
            """.trimIndent()

        binding.textViewMessage.text = messagePreviewText
    }

    private fun setupButton() {
        binding.buttonSendMessage.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data =
                    Uri.parse("smsto: ${message.contactNumber}")  // Only SMS apps respond to this.
                putExtra("sms_body", messagePreviewText)
            }
            startActivity(intent)

        }
    }

}