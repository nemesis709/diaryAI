package edu.skku.graduation.diaryAI.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import edu.skku.graduation.diaryAI.R
import edu.skku.graduation.diaryAI.db.DBHelper
import edu.skku.graduation.diaryAI.db.DiaryData
import edu.skku.graduation.diaryAI.resultFragment.ResultFragment2
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var listData = ArrayList<DiaryData>()
    var helper: DBHelper? = null

    interface ItemClick{ //인터페이스
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)

        view.setOnClickListener {
            Toast.makeText(
                parent.context,
                view.findViewById<TextView>(R.id.textId).text,
                Toast.LENGTH_SHORT
            ).show()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary: DiaryData = listData[position]
        holder.setMemo(diary)
//        if (itemClick != null) {
//            holder.binding.memoItemCardview.setOnClickListener(View.OnClickListener {
//                itemClick?.onClick(it, position)
//            })
//        }
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
            val pattern = "yy/MM/dd"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val date_text = simpleDateFormat.format(diary.datetime)
            date.text = date_text
            rating.rating = 3.0f;
        }
    }
}
