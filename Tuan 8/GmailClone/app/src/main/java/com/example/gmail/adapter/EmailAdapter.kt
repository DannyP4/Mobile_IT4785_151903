package com.example.gmail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.R
import com.example.gmail.model.Content
import com.example.gmail.model.Image
import com.example.gmail.model.Sender
import com.example.gmail.model.Time
import com.example.gmail.model.Title

class EmailAdapter(
    private val context: Context,
    private val senders: List<Sender>,
    private val titles: List<Title>,
    private val contents: List<Content>,
    private val times: List<Time>,
    private val images: List<Image>,
) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    inner class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView = itemView.findViewById(R.id.sender_name)
        val titleTextView: TextView = itemView.findViewById(R.id.title_content)
        val contentTextView: TextView = itemView.findViewById(R.id.msg_content)
        val timeTextView: TextView = itemView.findViewById(R.id.time)
        val image: ImageView = itemView.findViewById(R.id.avatar)
        val checked: CheckBox = itemView.findViewById(R.id.check_star)





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_view, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val sender = senders[position]
        val title = titles[position]
        val content = contents[position]
        val time = times[position]
        val image = images[position]

        holder.senderTextView.text = context.resources.getString(sender.stringResourceId)
        holder.titleTextView.text = context.resources.getString(title.stringResourceId)
        holder.contentTextView.text = context.resources.getString(content.stringResourceId)
        holder.timeTextView.text = context.resources.getString(time.stringResourceId)
        holder.image.setImageResource(image.stringResourceId)
        holder.checked.setOnClickListener{
            holder.checked.setButtonDrawable(android.R.drawable.star_big_on)

        }




    }

    override fun getItemCount(): Int {
        return senders.size
    }

}