package com.ashish.ashishsandroidprojects;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Project gettingStartedProject = new Project("Get started app", "Default Hello World app", 5);

        RecyclerView list = findViewById(R.id.recycler_view_projects);

        Project[] projects = {
                new Project("Get started app", "Default Hello World app", R.drawable.getting_started),
                new Project("Calculator", "BMI app, Lorum ipsum Lorum ipsum Lorum ipsum Lorum ipsum Lorum ipsum Lorum ipsum Lorum ipsum Lorum ipsum", R.drawable.calculator),
                new Project("Inches convert", "Converter app", R.drawable.tape),
                new Project("Hungry dev", "Restaurant app", R.drawable.hungry_developer),
                new Project("Quote", "My quote app", R.drawable.quote)
        };

        // Make adapter with our projects list, and set adapter in view
        ProjectsAdapter adapter = new ProjectsAdapter(projects);
        list.setAdapter(adapter);

    }
}