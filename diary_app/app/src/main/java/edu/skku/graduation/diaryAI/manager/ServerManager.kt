package edu.skku.graduation.diaryAI.manager

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class ServerManager {

    private val okHttpClient: OkHttpClient by lazy { OkHttpClient.Builder().build() }

    suspend fun joinRequest(ID:String, PW:String): String = suspendCoroutine { continuation ->
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

    suspend fun loginRequest(ID: String, PW: String): String = suspendCoroutine { continuation ->
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
        val token = AccountManager.token.getString("token","")
        val request: Request = Request.Builder()
            .url("http://3.39.61.211:8080/getDiary")
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
                    Log.d("GET DIARY::::::::",result)
                    continuation.resume(result)
                }
            }
        })
    }

    private fun readFromFile(context: Context, path:String): String? {

        val sdCard = Environment.getExternalStorageDirectory()
        val dir = File(sdCard.absolutePath.toString() + path)
        var ret = ""
        try {
            val inputStream: InputStream = FileInputStream(dir)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { receiveString = it } != null) {
                stringBuilder.append("\n").append(receiveString)
            }
            inputStream.close()
            ret = stringBuilder.toString()
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: $e")
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }
        return ret
    }

    suspend fun postDiaryRequest(context: Context,contentResolver:ContentResolver,uri:Uri): String = suspendCoroutine { continuation ->

        val text = readFromFile(context, "/Documents/KakaoTalk/Chats/NewTextFile.txt")
        Log.e("CONTENT:::::::::", text.toString())
    }

    suspend fun updateDiaryRequest(diary:DiaryData): String = suspendCoroutine { continuation ->
        val jsonObject = JSONObject()
        try {
            jsonObject.put("diaryId", diary.diary_id)
            jsonObject.put("rating1", diary.rating1)
            jsonObject.put("rating2", diary.rating2)
            jsonObject.put("rating3", diary.rating3)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val client = OkHttpClient()
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonObject.toString().toRequestBody(mediaType)
        val request: Request = Request.Builder()
            .url("http://3.39.61.211:8080/updateDiary")
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
                    Log.d("UPDATE DIARY::::::::",result)
                    continuation.resume(result) // resume calling coroutine
                }
            }
        })
    }
}