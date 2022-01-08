package com.example.workout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteOpenHelper(context : Context , factory:SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context , DATABASE_NAME , factory , DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " +
                TABLE_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_COMPLETED_DATE
                + " TEXT" + ")") // Create History Table Query.
        db?.execSQL(CREATE_HISTORY_TABLE) // Executing the create table query.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP THE TABLE IF EXISTS" + TABLE_HISTORY)
        onCreate(db)
    }

    fun addDate(date: String){
        val values = ContentValues()
        values.put(COLUMN_COMPLETED_DATE , date) //we only need to add 1 thing in our database and that is date , the second one is just the ID
        val db = this.writableDatabase
        db.insert(TABLE_HISTORY,null,values)
        db.close()
    }

    fun getAllCompletedDatesList() : ArrayList<String>{
        val list = ArrayList<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY",null)

        while(cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE)))
        }
        cursor.close()
        return list
    }

    companion object{
        private val DATABASE_VERSION = 1 //This Database Version
        private val DATABASE_NAME = "SevenMinutesWorkout.db" // Name of the database
        private val TABLE_HISTORY = "history" //Table Name
        private val COLUMN_ID = "_id" //column id (Underscore is added with id because it is the primary key)
        private val COLUMN_COMPLETED_DATE = "completed_date" //Column for storing the "completed dates"
    }
}