package edu.skku.graduation.diaryAI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.skku.graduation.diaryAI.manager.DBManager
import edu.skku.graduation.diaryAI.manager.DiaryData
import java.util.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val helper = DBManager(this, "diary", null, 1)
        createDB(helper)
        setContentView(R.layout.activity_result)
    }
    fun createDB(helper: DBManager) {
        helper.clearDiary()
        //server 통신
        helper.insertDiary(DiaryData(0, "test", "테스트입니다1",4.0f,4.0f,3.0f, Calendar.getInstance().time.time))
        helper.insertDiary(DiaryData(0, "test", "테스트입니다2", 1.0f,2.0f,1.0f, Calendar.getInstance().time.time))
    }
}