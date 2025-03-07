package com.example.bmi_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Class variables, aka Fields
    private TextView resultText;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private EditText editTextAge;
    private EditText editTextFeet;
    private EditText editTextInches;
    private EditText editTextWeight;
    private Button calculateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        // Get views you want to be able to edit or get info from, from xml file
        resultText = findViewById(R.id.text_view_result);
        radioButtonMale = findViewById(R.id.radio_button_male);
        radioButtonFemale = findViewById(R.id.radio_button_female);
        editTextAge = findViewById(R.id.edit_text_age);
        editTextFeet = findViewById(R.id.edit_text_feet);
        editTextInches = findViewById(R.id.edit_text_inches);
        editTextWeight = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        // Listen for any button clicks, and when clicked call calculateBmi function
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmi = calculateBmi();

                String ageText = editTextAge.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmi);
                } else {
                    displayGuidance(bmi);
                }
            }
        });
    }

    private double calculateBmi() {
        // Get text from views
        String feetText = editTextFeet.getText().toString();
        String inchesText = editTextInches.getText().toString();
        String weightText = editTextWeight.getText().toString();

        // Converting the number 'strings' into 'int' variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        // Height in meters
        double heightInMeters = totalInches * 0.0254;

        // BMI formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultStr;
        if (bmi < 18.5) {
            // Display underweight
            fullResultStr = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            // Display overweight
            fullResultStr = bmiTextResult + " - You are overweight";
        } else {
            // Display healthy
            fullResultStr = bmiTextResult + " - You are a healthy weight";
        }
        resultText.setText(fullResultStr);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultStr;
        if (radioButtonMale.isChecked()) {
            // Display boy guidance
            fullResultStr = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys";
        } else if (radioButtonFemale.isChecked()) {
            // Display girl guidance
            fullResultStr = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls";
        } else {
            // Display general guidance
            fullResultStr = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(fullResultStr);
    }
}