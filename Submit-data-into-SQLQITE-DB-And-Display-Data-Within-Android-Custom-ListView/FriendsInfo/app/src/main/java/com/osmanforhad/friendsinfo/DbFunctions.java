package com.osmanforhad.friendsinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbFunctions extends SQLiteOpenHelper {
    //Declare Variable for DB and Table
    private static final String DATABASE_NAME = "mydb";
    private static final String TABLE_NAME = "mytab";
    //Declare variable for DB table Column
    private static final String TAB_ID = "id";
    private static final String TAB_NAME = "name";
    private static final String TAB_NO = "number";
    //Constructor Method
    DbFunctions(Context c){
        //Invoke Super Class using Super Keyword and pass db variable
        super(c,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Query for crating table
        String table = "CREATE TABLE "+TABLE_NAME+"("+TAB_ID+" INTEGER PRIMARY KEY, "+TAB_NAME+" TEXT, "+TAB_NO+" TEXT)";
        //Passing table argument for execute
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*Create Method for Adding data into database
    * and Passing the Class DataTemp for find get set*/
    void addingDataintoTable(DataTemp dt){
        /*Create an object for permission from Sqlite database
        *for adding data into  DB*/
        SQLiteDatabase sqd = this.getWritableDatabase();
//Create an object for initial data from user input
        ContentValues cv = new ContentValues();
        cv.put(TAB_NAME, dt.getName());
        cv.put(TAB_NO, dt.getContact());
//Command for Insert data into table
        sqd.insert(TABLE_NAME, null, cv);
        //Command for Close DB
        sqd.close();

    }

    /*Create Method for Display data*/
    String[] display_data(){
        SQLiteDatabase sqd = this.getReadableDatabase();
        //Query for select data from table
        String table = "SELECT * FROM "+TABLE_NAME;
        //store data into c from selected query
        Cursor c = sqd.rawQuery(table, null);
        //Declare array variable for fetch data from c
        String[] recived_data = new String[c.getCount()];
        /*Call moveToFirst Method for goto
         1st  data which we query and store into c*/
        c.moveToFirst();
        //checking is it true
        if (c.moveToFirst()){
            //Declare a variable for count which start from 0
            int counter = 0;
            do {
                //Passing data to recived_data array from c
                recived_data[counter] = c.getString(c.getColumnIndex(TAB_NAME+"")) +"\nContactNo: "+
                        c.getString(c.getColumnIndex(TAB_NO+""));
                //It will increment 1
                counter = counter+1;
            }
            //After increment for goto next
            while (c.moveToNext());

        }
        //The display_data Method return String array which we Declare for fetch data
        return recived_data;
    }
}
