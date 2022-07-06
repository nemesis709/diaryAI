package edu.skku.graduation.diaryAI.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.skku.graduation.diaryAI.R
import edu.skku.graduation.diaryAI.adapter.RecyclerAdapter
import edu.skku.graduation.diaryAI.db.DBHelper

class ResultFragment1 : Fragment() {

    lateinit var navController: NavController
    lateinit var helper: DBHelper
    private var linearLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result1, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerDiary)
        val adapter = RecyclerAdapter()

        helper = DBHelper(requireContext(),"diary",null,1)
        adapter.listData.addAll(helper.selectDiary())
        adapter.helper = helper
        adapter.itemClick = object : RecyclerAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                //do
            }
        }

            linearLayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.prev).setOnClickListener(){
            requireActivity().finish()
        }

        view.findViewById<Button>(R.id.next).setOnClickListener(){
            navController.navigate(R.id.action_resultFragment1_to_resultFragment2)
        }
    }
}