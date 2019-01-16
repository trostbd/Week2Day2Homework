package com.example.lawre.week2day2homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserInput extends AppCompatActivity
{
    public static final String TAG = "tag_user_input ";
    boolean switchToMain = false;
    EditText etSsn, etName, etMajor, etMinor, etGpa, etDob, etCity, etState;
    MySQLHelper myDBHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        etSsn = findViewById(R.id.etSsn);
        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etMinor = findViewById(R.id.etMinor);
        etGpa = findViewById(R.id.etGPA);
        etDob = findViewById(R.id.etDOB);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        myDBHelp = new MySQLHelper(this);
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btSelect:
                if(etSsn.getText() != null && !etSsn.getText().toString().isEmpty())
                {
                    Student retrievedStudent = myDBHelp.getStudent(etSsn.getText().toString());
                    etSsn.setText(retrievedStudent.getSsn());
                    etName.setText(retrievedStudent.getName());
                    etMajor.setText(retrievedStudent.getMajor());
                    etMinor.setText(retrievedStudent.getMinor());
                    etGpa.setText(retrievedStudent.getGpa());
                    etDob.setText(retrievedStudent.getDob());
                    etCity.setText(retrievedStudent.getHomeCity());
                    etState.setText(retrievedStudent.getHomeState());
                }
                else
                {
                    Toast.makeText(this,"Invalid SSN",Toast.LENGTH_SHORT);
                }
                break;
            case R.id.btInsert:
                if(etSsn.getText() != null && !etSsn.getText().toString().isEmpty())
                {
                    Student insertStu = new Student(etName.getText().toString(),etMajor.getText().toString(),etMinor.getText().toString(),etGpa.getText().toString(),etDob.getText().toString(),etCity.getText().toString(),etState.getText().toString(),etSsn.getText().toString());
                    myDBHelp.insertStudent(insertStu);
                    switchToMain = true;
                }
                else
                {
                    Toast.makeText(this,"Student must have a SSN",Toast.LENGTH_SHORT);
                }
                break;
            case R.id.btUpdate:
                if(etSsn.getText() != null && !etSsn.getText().toString().isEmpty())
                {
                    Student updateStu = myDBHelp.getStudent(etSsn.getText().toString());
                    String ssn = etSsn.getText().toString();
                    String name, major, minor, gpa, dob, city, state;

                    //checks to see if field is empty
                    //if yes, use existing value
                    //if no, use new value

                    if(etName.getText() != null && !etName.getText().toString().isEmpty())
                    {
                        name = etName.getText().toString();
                    }
                    else
                    {
                        name = updateStu.getName();
                    }
                    if(etMajor.getText() != null && !etMajor.getText().toString().isEmpty())
                    {
                        major = etMajor.getText().toString();
                    }
                    else
                    {
                        major = updateStu.getMajor();
                    }
                    if(etMinor.getText() != null && !etMinor.getText().toString().isEmpty())
                    {
                        minor = etMinor.getText().toString();
                    }
                    else
                    {
                        minor = updateStu.getMinor();
                    }
                    if(etGpa.getText() != null && !etGpa.getText().toString().isEmpty())
                    {
                        gpa = etGpa.getText().toString();
                    }
                    else
                    {
                        gpa = updateStu.getGpa();
                    }
                    if(etDob.getText() != null && !etDob.getText().toString().isEmpty())
                    {
                        dob = etDob.getText().toString();
                    }
                    else
                    {
                        dob = updateStu.getDob();
                    }
                    if(etCity.getText() != null && !etCity.getText().toString().isEmpty())
                    {
                        city = etCity.getText().toString();
                    }
                    else
                    {
                        city = updateStu.getHomeCity();
                    }
                    if(etState.getText() != null && !etState.getText().toString().isEmpty())
                    {
                        state = etState.getText().toString();
                    }
                    else
                    {
                        state = updateStu.getHomeState();
                    }
                    updateStu = new Student(name,major,minor,gpa,dob,city,state,ssn);
                    myDBHelp.updatePerson(updateStu);
                    switchToMain = true;
                }
                else
                {
                    Toast.makeText(this,"Invalid SSN",Toast.LENGTH_SHORT);
                }
                break;
            case R.id.btDelete:
                if(etSsn.getText() != null && !etSsn.getText().toString().isEmpty())
                {
                    myDBHelp.deleteStudent(etSsn.getText().toString());
                    switchToMain = true;
                }
                else
                {
                    Toast.makeText(this, "Invalid SSN", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Log.d(TAG, "onClick: not a button");
                break;
        }
        if(switchToMain)
        {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }
    }
}
