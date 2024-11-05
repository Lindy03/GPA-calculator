package com.example.gpacalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Double> grades = new ArrayList<>();
    private ArrayList<String> moduleNames = new ArrayList<>();
    private EditText etModuleName, etGrade;
    private TextView tvResult, tvGradesList;
    private Button btnAddModule, btnCalculateGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etModuleName = findViewById(R.id.etModuleName);
        etGrade = findViewById(R.id.etGrade);
        tvResult = findViewById(R.id.tvResult);
        tvGradesList = findViewById(R.id.tvGradesList);
        btnAddModule = findViewById(R.id.btnAddModule);
        btnCalculateGPA = findViewById(R.id.btnCalculateGPA);

        //  module button logic
        btnAddModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moduleName = etModuleName.getText().toString().trim();
                String gradeStr = etGrade.getText().toString().trim();

                if (!gradeStr.isEmpty() && !moduleName.isEmpty()) {
                    double grade = Double.parseDouble(gradeStr);
                    grades.add(grade);
                    moduleNames.add(moduleName);

                    etModuleName.setText("");
                    etGrade.setText("");

                    updateGradesList();
                    tvResult.setText("Module added: " + moduleName + " with Grade: " + grade);
                } else {
                    tvResult.setText("Please enter a valid module name and grade.");
                }
            }
        });


        btnCalculateGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grades.size() > 0) {
                    double totalPoints = 0;
                    for (double grade : grades) {
                        totalPoints += getGradePoint(grade); // Convert grade to grade point
                    }

                    double gpa = totalPoints / grades.size(); // Calculate GPA
                    tvResult.setText("GPA: " + String.format("%.2f", gpa)); // Display GPA
                } else {
                    tvResult.setText("No grades added yet.");
                }
            }
        });
    }

    // Function to convert a numeric grade to grade points
    private double getGradePoint(double grade) {
        if (grade >= 75) {
            return 4.0; // A
        } else if (grade >= 70) {
            return 3.5; // B
        } else if (grade >= 60) {
            return 3.0; // C
        } else if (grade >= 50) {
            return 2.0; // D
        } else if (grade >= 40) {
            return 1.0; // E
        } else {
            return 0.0; // F
        }
    }

    // Updates the grades list display
    private void updateGradesList() {
        StringBuilder gradesList = new StringBuilder("Grades List:\n");
        for (int i = 0; i < grades.size(); i++) {
            gradesList.append(moduleNames.get(i)).append(": ").append(grades.get(i)).append("\n");
        }
        tvGradesList.setText(gradesList.toString());
    }
}
