package edu.skku.graduation.diaryAI.adapter

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ServerAdapter {
    suspend fun loginRequest(ID:String,PW:String): String = suspendCoroutine { continuation ->
        val jsonObject = JSONObject()
        try {
            jsonObject.put("userid", ID)
            jsonObject.put("password", PW)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val client = OkHttpClient()
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonObject.toString().toRequestBody(mediaType)
        val request: Request = Request.Builder()
            .url("http://3.39.61.211:8080/join")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e) // resume calling coroutine
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    val resStr = response.body!!.string()
                    Log.d("result::::::",resStr)
                }
            }
        })
    }
}