package com.company.inchestometers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Class variables for views
    EditText editTextInches;
    Button convertButton;
    TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        // Find views to get info from or to change in the xml file
        editTextInches = findViewById(R.id.edit_text_inches);
        convertButton = findViewById(R.id.convert_button);
        textViewResult = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get inches from input box
                String inches = editTextInches.getText().toString();

                // Check if input box was empty
                if (inches.isEmpty()) {
                    // Alert user if it's empty
                    Toast.makeText(MainActivity.this, "Empty Input!", Toast.LENGTH_SHORT).show();
                } else {
                    // Else convert to meters and display it
                    double meters = convertToMeters(inches);
                    displayResult(meters);
                }
            }
        });
    }

    private double convertToMeters(String inches) {
        // Convert string to inches
        int inchesInt = Integer.parseInt(inches);
        // Return meters
        return inchesInt / 39.37;
    }

    private void displayResult(double meters) {

        // Format meters into a proper decimal number
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String metersResult = myDecimalFormatter.format(meters);

        // set result text
        String fullStr = metersResult + " meters";
        textViewResult.setText(fullStr);
    }
}