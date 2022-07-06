package edu.skku.graduation.diaryAI.resultFragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import edu.skku.graduation.diaryAI.R


class ResultFragment2 : Fragment() {

    lateinit var navController: NavController
    var index = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.clipboard).setOnClickListener(){
            val clipboard: ClipboardManager =
                requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", view.findViewById<TextView>(R.id.result).text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(activity,"복사되었습니다",Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.evaluate).setOnClickListener(){
            navController.navigate(R.id.action_resultFragment2_to_resultFragment3)
        }
    }
}