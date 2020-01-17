package com.dubornogolpo.dubornogolpo2.tools

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

class DatabaseHelper(val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object
    {
        private val DATABASE_NAME = "duborno2.db"
        private val DATABASE_VERSION = 2
        private val BOOK_TABLE = "bookimages"
        private val BOOK_TABLE_PAGES = "pages"
        private val BOOK_TABLE_IMG = "img"
        private val BOOK_TABLE_STORY_NAME = "story_name"
        private val STORIES_TABLE = "stories"
        private val STORIES_TABLE_INDEX = "indexnum"
        private val STORIES_TABLE_NAME = "story_name"
        private val STORIES_TABLE_AUTHOR = "author_name"
        private val AUTHORS_TABLE = "authors"
        private val AUTHORS_TABLE_NAME = "author_name"
        private val AUTHORS_TABLE_BIRTHPLACE = "birthplace"
        private val AUTHORS_TABLE_SCHOOL = "school"
        private val AUTHORS_TABLE_COLLEGE = "college"
        private val AUTHORS_TABLE_BLOOD = "bloodgroup"
        private val AUTHORS_TABLE_HOBBIES = "hobbies"
        private val AUTHORSIMG_TABLE = "authorimages"
        private val AUTHORSIMG_TABLE_NAME = "name"
        private val AUTHORSIMG_TABLE_IMG = "img"
        private val CREATE_BOOKIMG_TABLE = "create table " + BOOK_TABLE + " (" + BOOK_TABLE_PAGES + " integer primary key, " + BOOK_TABLE_IMG + " blob, " + BOOK_TABLE_STORY_NAME + " varchar2(255));"
        private val CREATE_STORIES_TABLE = "create table " + STORIES_TABLE + " (" + STORIES_TABLE_INDEX + " integer primary key, " + STORIES_TABLE_NAME + " varchar2(255), " + STORIES_TABLE_AUTHOR + " varchar2(255));"
        private val CREATE_AUTHORS_TABLE = "create table " + AUTHORS_TABLE + "(" + AUTHORS_TABLE_NAME+ " varchar2(255) primary key, " + AUTHORS_TABLE_BIRTHPLACE + " varchar2(255), " + AUTHORS_TABLE_BLOOD +" varchar2(255), " + AUTHORS_TABLE_SCHOOL + " varchar2(255), " + AUTHORS_TABLE_COLLEGE+ " varchar2(255), " + AUTHORS_TABLE_HOBBIES + " varchar2(255));"
        private val CREATE_AUTHORSIMG_TABLE = "create table " + AUTHORSIMG_TABLE + "(" + AUTHORSIMG_TABLE_NAME + " varchar2(255), " + AUTHORSIMG_TABLE_IMG + " blob);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db!!.execSQL(CREATE_BOOKIMG_TABLE)
            db!!.execSQL(CREATE_STORIES_TABLE)
            db!!.execSQL(CREATE_AUTHORS_TABLE)
            db!!.execSQL(CREATE_AUTHORSIMG_TABLE)
        }
        catch (e: Exception)
        {
            Toast.makeText(context, "Exception ${e.toString()}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try
        {
            db!!.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE + ";")
            db!!.execSQL("DROP TABLE IF EXISTS " + AUTHORS_TABLE + ";")
            db!!.execSQL("DROP TABLE IF EXISTS " + STORIES_TABLE + ";")
            db!!.execSQL("DROP TABLE IF EXISTS " + AUTHORSIMG_TABLE + ";")
            onCreate(db)
        }
        catch (e: Exception)
        {
            Toast.makeText(context, "Exception ${e.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

//    Read up on async tasks and co routines and all that jazz
    fun writeAuthorsToDatabase(name: String, birthplace: String, bloodgroup: String, school: String, college: String, hobbies: String)
    {
//        val imgutil = ImageUtil()
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(AUTHORS_TABLE_NAME, name)
        cv.put(AUTHORS_TABLE_BIRTHPLACE, birthplace)
        cv.put(AUTHORS_TABLE_BLOOD, bloodgroup)
        cv.put(AUTHORS_TABLE_SCHOOL, school)
        cv.put(AUTHORS_TABLE_COLLEGE, college)
        cv.put(AUTHORS_TABLE_HOBBIES, hobbies)
        db.insert(AUTHORS_TABLE, null, cv)
        db.close()
//        val imgbmp =
    }

    fun writeBookImagesToDatabase(pagenum: Int, img: ByteArray, storyName: String)
    {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(BOOK_TABLE_PAGES, pagenum)
        cv.put(BOOK_TABLE_IMG, img)
        cv.put(BOOK_TABLE_STORY_NAME, storyName)
        db.insert(BOOK_TABLE, null, cv)
        db.close()
    }
}