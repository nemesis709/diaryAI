package edu.skku.graduation.diaryAI

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import edu.skku.graduation.diaryAI.adapter.ServerAdapter
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LoginActivity : AppCompatActivity() {

    lateinit var ID: String
    lateinit var PW: String
    private val server = ServerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.sign).setOnClickListener {
            ID = findViewById<EditText>(R.id.user_id).text.toString()
            PW = findViewById<EditText>(R.id.user_pw).text.toString()

            //서버로 전송
            lifecycleScope.launch {
                val result = server.signupRequest(ID, PW)
                Log.d("RESULT:::::::::::::", result)
            }
        }

        findViewById<Button>(R.id.login).setOnClickListener {
            ID = findViewById<EditText>(R.id.user_id).text.toString()
            PW = findViewById<EditText>(R.id.user_pw).text.toString()

//            서버로 전송
            lifecycleScope.launch {
                val result = server.loginRequest(ID, PW)
                Log.d("RESULT:::::::::::::", result)

                //성공
                val jsonObject = JSONObject(result)
                val token = jsonObject.getString("token")
                Log.d("TOKEN:::::::::::::", token)
            }
        }
    }

}