package com.mehedihasan.showdatasql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {
    //TODO Create Query
    private val CREATE_TABLE = "create table " + TABLE_NAME + " (" + COL_ID + " Integer primary key autoincrement, " + COL_TITTLE + " TEXT, " + COL_DESCRIPTION + " TEXT )"

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        //TODO Invoke The Query
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    //TODO Insert Data Method
    fun insertData(tittle: String?, description: String?): Long {
        //TODO Insert Value By Contents Value

        val contentValues = ContentValues()
        contentValues.put(COL_TITTLE, tittle)
        contentValues.put(COL_DESCRIPTION, description)
        val sqLiteDatabase = writableDatabase
        val id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
        sqLiteDatabase.close()

        return id
    }

    fun showData(): Cursor {
        val ALL_DATA_QUERY = "select * from " + TABLE_NAME
        val sqLiteDatabase = readableDatabase
        val cursor = sqLiteDatabase.rawQuery(ALL_DATA_QUERY, null)

        return cursor
    }

    fun searchData(ID:Int):Cursor{
        val SEARCH_QUERY= "select * from " + TABLE_NAME+ " where " +COL_ID+ "=" +ID
        val sqLiteDatabase=readableDatabase
        val cursor=sqLiteDatabase.rawQuery(SEARCH_QUERY,null)
        return cursor
    }

    companion object {
        private const val DATABASE_NAME = "User.db"
        private const val TABLE_NAME = "User"
        var COL_ID: String = "Id"
        var COL_TITTLE: String = "Tittle"
        var COL_DESCRIPTION: String = "Description"

        private const val VERSION = 1
    }
}