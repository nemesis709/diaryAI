package edu.skku.graduation.diaryAI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    lateinit var ID:String
    lateinit var PW:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.sign).setOnClickListener{
            ID = findViewById<EditText>(R.id.user_id).text.toString()
            PW = findViewById<EditText>(R.id.user_pw).text.toString()

            //서버로 전송
            setGetFun(ID,PW)

            Toast.makeText(this, "$ID//$PW",Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.login).setOnClickListener{
            ID = findViewById<EditText>(R.id.user_id).text.toString()
            PW = findViewById<EditText>(R.id.user_pw).text.toString()

            //서버로 전송
//            if (ID=="1"&&PW=="1"){
                startActivity(Intent(this, MainActivity::class.java))
//            }else{
//                Toast.makeText(this, "ID나 비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun setGetFun(ID:String, PW:String) {
// create your json here
        val jsonObject =JSONObject();
        try {
            jsonObject.put("userid", ID);
            jsonObject.put("password", PW);
        } catch (e: JSONException) {
            e.printStackTrace();
        }

        val client = OkHttpClient()
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonObject.toString().toRequestBody(mediaType)
        val request: Request = Request.Builder()
            .url("http://3.39.61.211:8080/join")
            .post(body)
            .build()

        val response: Response?
        try {
            response = client.newCall(request).execute()
            val resStr = response.body!!.string()
            Log.d("result::::::",resStr)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}