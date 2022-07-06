package edu.skku.graduation.diaryAI.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class DiaryData(var diary_id: Int?, var title: String, var content: String, var rate: Double, var datetime:Long) {}

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        val create: String = "CREATE TABLE if not exists diary (" +
                "diary_id integer primary key autoincrement," +
                "title TEXT NOT NULL, " +
                "content TEXT NOT NULL, " +
                "datetime integer NOT NULL," +
                "rate TEXT NOT NULL);";

        db.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql: String = "DROP TABLE if exists diary"
        db.execSQL(sql)
        onCreate(db)
    }

    //insert 메소드
    fun insertDiary(diary: DiaryData) {
        val values = ContentValues()
        //넘겨줄 컬럼의 매개변수 지정
        values.put("title", diary.title)
        values.put("content", diary.content)
        values.put("rate", diary.rate)
        values.put("datetime",diary.datetime)
        //쓰기나 수정이 가능한 데이터베이스 변수
        val wd = writableDatabase
        wd.insert("diary", null, values)
        //사용이 끝나면 반드시 close()를 사용하여 메모리누수 가 되지않도록 합시다.
        wd.close()
    }


    //select 메소드
    fun selectDiary(): MutableList<DiaryData> {
        val list = mutableListOf<DiaryData>()
        //전체조회
        val selectAll = "select * from diary"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll, null)

        //반복문을 사용하여 list 에 데이터를 넘겨 줍시다.
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("diary_id"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            val rate = cursor.getDouble(cursor.getColumnIndexOrThrow("rate"))
            val datetime = cursor.getLong(cursor.getColumnIndexOrThrow("datetime"))

            list.add(DiaryData(id, title, content, rate,datetime))
        }
        cursor.close()
        rd.close()

        return list
    }

    //update 메소드
    fun updateDiary(diary: DiaryData) {
        val values = ContentValues()

        values.put("title", diary.title)
        values.put("content", diary.content)
        values.put("rate", diary.rate)
        values.put("datetime",diary.datetime)

        val wd = writableDatabase
        wd.update("memo", values, "id=${diary.diary_id}", null)
        wd.close()

    }

    //delete 메소드
    fun deleteDiary(diary: DiaryData) {
        val delete = "delete from diary where id = ${diary.diary_id}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }

    //clear
    fun clearDiary() {
        val delete = "delete from diary"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }

}