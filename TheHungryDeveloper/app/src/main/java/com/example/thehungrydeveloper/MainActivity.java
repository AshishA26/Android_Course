package com.example.thehungrydeveloper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CardView startersCard;
    CardView mainsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startersCard = findViewById(R.id.card_view_starters);
        mainsCard = findViewById(R.id.card_view_mains);

        startersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Explicit Intent to switch to new screen when needed
                Intent startersActivityIntent = new Intent(MainActivity.this, StartersActivity.class);
                startActivity(startersActivityIntent);
            }
        });
        mainsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainCoursesActivityIntent = new Intent(MainActivity.this, MainCoursesActivity.class);
                startActivity(mainCoursesActivityIntent);
            }
        });

        TextView emailTextView = findViewById(R.id.text_view_email_address);
        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implicit intent with action and data within the intent. Will open any app that can handle "mailto" action.
                Intent launchEmailAppIntent = new Intent(Intent.ACTION_SENDTO);
                launchEmailAppIntent.setData(Uri.parse("mailto:hungrydev@gmail.com")); //Only email apps should handle this
                startActivity(launchEmailAppIntent);
            }
        });

    }
}