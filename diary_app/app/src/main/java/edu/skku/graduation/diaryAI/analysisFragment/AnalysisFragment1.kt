package edu.skku.graduation.diaryAI.analysisFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import edu.skku.graduation.diaryAI.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AnalysisFragment1 : Fragment() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.kakao).setOnClickListener(){
            openApplicationOrMarket("com.kakao.talk")
        }

//        view.findViewById<Button>(R.id.file).setOnClickListener(){
//            //filepicker
//        }
//
//        val client = OkHttpClient()
//        var file:File
//
//        val mediaType = "application/json; charset=utf-8".toMediaType()
//        val requestBody:RequestBody = MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("file", file.name,
//                File("your absolute file path").asRequestBody(mediaType)
//            )
//            .build();
//
//        val request = Request.Builder()
//            .url("Your url")
//            .post(requestBody)
//            .build();

        view.findViewById<Button>(R.id.prev).setOnClickListener(){
            requireActivity().finish()
        }

        view.findViewById<Button>(R.id.next).setOnClickListener(){
            navController.navigate(R.id.action_analysisFragment1_to_analysisFragment2)
        }
    }

    fun openApplicationOrMarket(packageName: String) {
        var intent = requireContext().packageManager.getLaunchIntentForPackage(packageName)
        if (intent == null) {
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=$packageName")
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        requireContext().startActivity(intent)
    }
}