package com.example.lawre.week2day2homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.lawre.week2day2homework.DatabaseConstants.DATABASE_NAME;
import static com.example.lawre.week2day2homework.DatabaseConstants.DATABASE_VERSION;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_CITY;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_DOB;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_GPA;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_MAJOR;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_MINOR;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_NAME;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_SSN;
import static com.example.lawre.week2day2homework.DatabaseConstants.FIELD_STATE;
import static com.example.lawre.week2day2homework.DatabaseConstants.TABLE_NAME;


public class MySQLHelper extends SQLiteOpenHelper
{

    public MySQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createQuery = "CREATE TABLE " + TABLE_NAME + "(" + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_CITY + " TEXT, "
                + FIELD_STATE + " TEXT, "
                + FIELD_SSN + " TEXT PRIMARY KEY)";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void insertStudent(Student stu)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues conVal = new ContentValues();
        if(stu != null)
        {
             conVal.put(FIELD_NAME,stu.getName());
            conVal.put(FIELD_MAJOR,stu.getMajor());
            conVal.put(FIELD_MINOR,stu.getMinor());
            conVal.put(FIELD_GPA,stu.getGpa());
            conVal.put(FIELD_DOB,stu.getDob());
            conVal.put(FIELD_CITY,stu.getHomeCity());
            conVal.put(FIELD_STATE,stu.getHomeState());
            conVal.put(FIELD_SSN,stu.getSsn());

            db.insert(TABLE_NAME,null,conVal);
        }
    }

    public ArrayList<Student> getAllStudents()
    {
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor myCur = myDB.rawQuery(query, null);
        if(myCur.moveToFirst())
        {
            ArrayList<Student> studentList = new ArrayList<>();
            do {
                String name = myCur.getString(myCur.getColumnIndex(FIELD_NAME));
                String major = myCur.getString(myCur.getColumnIndex(FIELD_MAJOR));
                String minor = myCur.getString(myCur.getColumnIndex(FIELD_MINOR));
                String gpa = myCur.getString(myCur.getColumnIndex(FIELD_GPA));
                String dob = myCur.getString(myCur.getColumnIndex(FIELD_DOB));
                String city = myCur.getString(myCur.getColumnIndex(FIELD_CITY));
                String state = myCur.getString(myCur.getColumnIndex(FIELD_STATE));
                String ssn = myCur.getString(myCur.getColumnIndex(FIELD_SSN));
                studentList.add(new Student(name,major,minor,gpa,dob,city,state,ssn));
            } while (myCur.moveToNext());
            myCur.close();
            return studentList;
        }
        else
        {
            myCur.close();
            return null;
        }
    }

    public int updatePerson(Student stu)
    {
        if(stu != null)
        {
            String whereClause = FIELD_SSN + " = \"" + stu.getSsn() + "\"";
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues conVal = new ContentValues();
            conVal.put(FIELD_NAME,stu.getName());
            conVal.put(FIELD_MAJOR,stu.getMajor());
            conVal.put(FIELD_MINOR,stu.getMinor());
            conVal.put(FIELD_GPA,stu.getGpa());
            conVal.put(FIELD_DOB,stu.getDob());
            conVal.put(FIELD_CITY,stu.getHomeCity());
            conVal.put(FIELD_STATE,stu.getHomeState());
            conVal.put(FIELD_SSN,stu.getSsn());

            db.insert(TABLE_NAME,null,conVal);

            return db.update(TABLE_NAME, conVal, whereClause, null);
        }
        else
            return -1;
    }

    public int deleteStudent(String passedSsn)
    {
        String whereClause = FIELD_SSN + " = \"" + passedSsn + "\"";
        SQLiteDatabase myDB = this.getWritableDatabase();
        return myDB.delete(TABLE_NAME,whereClause,null);
    }

    public Student getStudent(String passedSsn)
    {
        Student returnStudent = null;
        if(passedSsn != null && !passedSsn.isEmpty())
        {
            SQLiteDatabase myDB = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + FIELD_SSN + " = \"" + passedSsn + "\"";
            Cursor myCur = myDB.rawQuery(query, null);
            if (myCur.moveToFirst())
            {
                String name = myCur.getString(myCur.getColumnIndex(FIELD_NAME));
                String major = myCur.getString(myCur.getColumnIndex(FIELD_MAJOR));
                String minor = myCur.getString(myCur.getColumnIndex(FIELD_MINOR));
                String gpa = myCur.getString(myCur.getColumnIndex(FIELD_GPA));
                String dob = myCur.getString(myCur.getColumnIndex(FIELD_DOB));
                String city = myCur.getString(myCur.getColumnIndex(FIELD_CITY));
                String state = myCur.getString(myCur.getColumnIndex(FIELD_STATE));
                String ssn = myCur.getString(myCur.getColumnIndex(FIELD_SSN));
                returnStudent = new Student(name,major,minor,gpa,dob,city,state,ssn);
            }
            myCur.close();
        }
        return returnStudent;
    }
}
