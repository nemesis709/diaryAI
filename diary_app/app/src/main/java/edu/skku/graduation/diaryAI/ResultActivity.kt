package edu.skku.graduation.diaryAI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import edu.skku.graduation.diaryAI.manager.DBManager
import edu.skku.graduation.diaryAI.manager.DiaryData
import edu.skku.graduation.diaryAI.manager.PrefManager
import edu.skku.graduation.diaryAI.manager.ServerManager
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val helper = DBManager(this, "diary", null, 1)
//        helper.clearDiary()
//        lifecycleScope.launch {
//            val server = ServerManager()
//            val result = server.getDiaryRequest()
//            //성공
//            try {
//                val array = JSONArray(result)
//
//                for (i in 0 until array.length()) {
//                    val testModel =
//                        Gson().fromJson(array.getJSONObject(i).toString(), DiaryData::class.java)
//                    helper.insertDiary(testModel)
//                }
//                Log.d("DATE:::::", Calendar.getInstance().time.time.toString())
//            } catch (e: Exception) {
//                Log.d("result:::::::::::::", "???")
//            } finally {
//                setContentView(R.layout.activity_result)
//            }
//        }
        setContentView(R.layout.activity_result)
    }
}