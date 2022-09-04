package edu.skku.graduation.diaryAI.analysisFragment

import android.content.ContentValues.TAG
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import edu.skku.graduation.diaryAI.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.UnknownHostException


class AnalysisFragment2 : Fragment() {

    lateinit var navController: NavController
    private var filePath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        filePath = requireArguments().getString("path").toString()
        return inflater.inflate(R.layout.fragment_analysis2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.server).setOnClickListener() {
            showProgress(true)
            Handler(Looper.getMainLooper()).postDelayed({
                // Your Code
                Toast.makeText(requireContext(),"전송되었습니다",Toast.LENGTH_SHORT).show()
                showProgress(false)
            }, 3000)
            //use volley to http
        }
        val uri = Uri.parse(filePath)
        val name = getNameFromURI(uri)
        view.findViewById<TextView>(R.id.filename).text=name

        view.findViewById<Button>(R.id.prev).setOnClickListener(){
            navController.navigate(R.id.action_analysisFragment2_to_analysisFragment1)
        }

        view.findViewById<Button>(R.id.home).setOnClickListener(){
            requireActivity().finish()
        }
    }

    private fun getNameFromURI(uri: Uri?): String? {
        val contentResolver = context!!.contentResolver
        val c: Cursor? = uri?.let { contentResolver.query(it, null, null, null, null) }
        c?.moveToFirst()
        return c?.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }

    private fun showProgress(isShow:Boolean) {
        val progress = view?.findViewById<ProgressBar>(R.id.progress)
        if (isShow) progress?.visibility = View.VISIBLE
        else progress?.visibility = View.INVISIBLE
    }

}
