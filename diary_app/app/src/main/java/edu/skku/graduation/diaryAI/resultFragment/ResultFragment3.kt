package edu.skku.graduation.diaryAI.resultFragment

import android.os.Bundle
import android.util.Log
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
import edu.skku.graduation.diaryAI.db.DBHelper
import edu.skku.graduation.diaryAI.db.DiaryData

class ResultFragment3 : Fragment() {

    lateinit var navController: NavController

    private var param1: Float = 3F
    private var param2: Float = 3F
    private var param3: Float = 3F
    private var diaryID: Int = 0
    private lateinit var helper:DBHelper
    private lateinit var diary: DiaryData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        helper = DBHelper(requireContext(), "diary", null, 1)
        diaryID = arguments!!.getInt("finalID")
        diary = helper.selectDiary(diaryID)
        Log.d("ResultID", diaryID.toString())
        // Inflate the layout for this fragment
        param1 = diary.rate1
        param2 = diary.rate2
        param3 = diary.rate3

        return inflater.inflate(R.layout.fragment_result3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RatingBar>(R.id.ratingBar1).rating = param1
        view.findViewById<RatingBar>(R.id.ratingBar2).rating = param2
        view.findViewById<RatingBar>(R.id.ratingBar3).rating = param3

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.home).setOnClickListener() {
            requireActivity().finish()
        }

        view.findViewById<RatingBar>(R.id.ratingBar1).onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ -> param1 = rating }
        view.findViewById<RatingBar>(R.id.ratingBar2).onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ -> param2 = rating }
        view.findViewById<RatingBar>(R.id.ratingBar3).onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ -> param3 = rating }

        view.findViewById<Button>(R.id.evaluate).setOnClickListener() {
            val text = "$param1//$param2//$param3"
            diary.rate1 = param1
            diary.rate2 = param2
            diary.rate3 = param3
            helper.updateDiary(diary)

            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }
}