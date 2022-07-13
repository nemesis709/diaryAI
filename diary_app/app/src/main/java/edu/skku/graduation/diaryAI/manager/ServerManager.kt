package edu.skku.graduation.diaryAI.manager

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ServerManager {

    suspend fun signupRequest(ID:String, PW:String): String = suspendCoroutine { continuation ->
        val jsonObject = JSONObject()
        try {
            jsonObject.put("userId", ID)
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
                    val result = response.body!!.string()
                    continuation.resume(result) // resume calling coroutine
                }
            }
        })
    }

    suspend fun loginRequest(ID:String, PW:String,): String = suspendCoroutine { continuation ->
        val jsonObject = JSONObject()
        try {
            jsonObject.put("userId", ID)
            jsonObject.put("password", PW)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val client = OkHttpClient()
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonObject.toString().toRequestBody(mediaType)
        val request: Request = Request.Builder()
            .url("http://3.39.61.211:8080/login")
            .post(body)
            .build()

        var result:String

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e) // resume calling coroutine
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    result = response.body!!.string()
                    continuation.resume(result) // resume calling coroutine

                }
            }
        })
    }

    suspend fun getDiaryRequest(): String = suspendCoroutine { continuation ->
        val client = OkHttpClient()
        val token = PrefManager.prefs.getString("token","")
        val request: Request = Request.Builder()
            .url("http://3.39.61.211:8080/login")
            .addHeader("X-AUTH-TOKEN", token)
            .build()

        var result:String

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e) // resume calling coroutine
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    result = response.body!!.string()
                    continuation.resume(result) // resume calling coroutine

                }
            }
        })
    }
}