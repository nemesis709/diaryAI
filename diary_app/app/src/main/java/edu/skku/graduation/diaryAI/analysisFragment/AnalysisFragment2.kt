package edu.skku.graduation.diaryAI.analysisFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.server).setOnClickListener(){
            Toast.makeText(activity,"send",Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.prev).setOnClickListener(){
            navController.navigate(R.id.action_analysisFragment2_to_analysisFragment1)
        }

        view.findViewById<Button>(R.id.home).setOnClickListener(){
            requireActivity().finish()
        }
    }
}
