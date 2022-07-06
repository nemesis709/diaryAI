package edu.skku.graduation.diaryAI.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import edu.skku.graduation.diaryAI.R

class ResultFragment3 : Fragment() {

    lateinit var navController: NavController

    private var param1:Float = 3F
    private var param2:Float = 3F
    private var param3:Float = 3F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.home).setOnClickListener(){
            requireActivity().finish()
        }

        view.findViewById<RatingBar>(R.id.ratingBar1).onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ -> param1 = rating }
        view.findViewById<RatingBar>(R.id.ratingBar2).onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ -> param2 = rating }
        view.findViewById<RatingBar>(R.id.ratingBar3).onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ -> param3 = rating }

        view.findViewById<Button>(R.id.evaluate).setOnClickListener(){
            val text = "$param1//$param2//$param3"
            Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
        }
    }
}