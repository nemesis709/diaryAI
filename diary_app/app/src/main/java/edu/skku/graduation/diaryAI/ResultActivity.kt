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
        setContentView(R.layout.activity_result)
    }
}