package edu.skku.graduation.diaryAI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var ID:String
    lateinit var PW:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.sign).setOnClickListener{
            ID = findViewById<EditText>(R.id.user_id).text.toString()
            PW = findViewById<EditText>(R.id.user_pw).text.toString()
            Toast.makeText(this, "$ID//$PW",Toast.LENGTH_SHORT).show()
            //서버로 전송
        }

        findViewById<Button>(R.id.login).setOnClickListener{
            ID = findViewById<EditText>(R.id.user_id).text.toString()
            PW = findViewById<EditText>(R.id.user_pw).text.toString()
            //서버로 전송
            if (ID=="1"&&PW=="1"){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "ID나 비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }
        }
    }
}