package edu.skku.graduation.diaryAI.analysisFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import edu.skku.graduation.diaryAI.R


class AnalysisFragment2 : Fragment() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        view.findViewById<Button>(R.id.prev).setOnClickListener(){
            navController.navigate(R.id.action_analysisFragment2_to_analysisFragment1)
        }

        view.findViewById<Button>(R.id.home).setOnClickListener(){
            requireActivity().finish()
        }
    }

    private fun showProgress(isShow:Boolean) {
        val progress = view?.findViewById<ProgressBar>(R.id.progress)
        if (isShow) progress?.visibility = View.VISIBLE
        else progress?.visibility = View.INVISIBLE
    }

}
