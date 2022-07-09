package edu.skku.graduation.diaryAI.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import edu.skku.graduation.diaryAI.R
import edu.skku.graduation.diaryAI.db.DBHelper
import edu.skku.graduation.diaryAI.db.DiaryData
import edu.skku.graduation.diaryAI.resultFragment.ResultFragment1
import edu.skku.graduation.diaryAI.resultFragment.ResultFragment2
import java.text.SimpleDateFormat


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var listData = ArrayList<DiaryData>()
    var helper: DBHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary: DiaryData = listData[position]
        holder.setMemo(diary)
        var navController: NavController?

        holder.apply {
            itemView.setOnClickListener {
                val resultFragment2 = ResultFragment2()
                val bundle = Bundle()
                bundle.putInt("ID", diary.diary_id!!)
                resultFragment2.arguments = bundle
                navController = Navigation.findNavController(itemView)
                navController!!.navigate(R.id.action_resultFragment1_to_resultFragment2)
            }
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val diaryID: TextView = itemView.findViewById(R.id.textId)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val rating: RatingBar = itemView.findViewById(R.id.rating)
        private val content: TextView = itemView.findViewById(R.id.content)
        private val date: TextView = itemView.findViewById(R.id.date)

        fun setMemo(diary: DiaryData) {
            diaryID.text = diary.diary_id.toString()
            title.text = diary.title
            content.text = diary.content
            val simpleDateFormat = SimpleDateFormat("yy/MM/dd")
            val text_date = simpleDateFormat.format(diary.datetime)
            date.text = text_date
            rating.rating = 3.0f
        }
    }
}
