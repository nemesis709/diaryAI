package edu.skku.graduation.diaryAI.adapter

import android.content.Context
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
import edu.skku.graduation.diaryAI.db.DBManager
import edu.skku.graduation.diaryAI.db.DiaryData
import java.text.SimpleDateFormat

class RecyclerAdapter(private val view: ViewGroup?) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var listData = ArrayList<DiaryData>()
    var helper: DBManager? = null
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary: DiaryData = listData[position]
        holder.setMemo(diary)
        var navController: NavController?
        context = view?.context!!

        holder.apply {
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("clickID", diary.diary_id!!)
                navController = Navigation.findNavController(itemView)
                navController!!.navigate(R.id.action_resultFragment1_to_resultFragment2,bundle)
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
            date.text = SimpleDateFormat("yy/MM/dd").format(diary.datetime)
            val rate = (diary.rate1 + diary.rate2 + diary.rate3)/3
            rating.rating = rate
        }
    }
}
