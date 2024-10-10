package com.mehedihasan.showdatasql.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mehedihasan.showdatasql.ModelClass.User
import com.mehedihasan.showdatasql.R

class UserAdapter (private val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.model_design_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = users[position]
        holder.idTv.text="ID:"+user.id.toString()
        holder.tittleTv.text=user.tittle
        holder.descriptionTv.text=user.description
    }

    override fun getItemCount(): Int {
       // return  users.size
        return users.count()
    }

    //TODO View Holder Class
    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
         val idTv: TextView=itemView.findViewById(R.id.idTv)
        val tittleTv: TextView=itemView.findViewById(R.id.tittleTv)
        val descriptionTv: TextView=itemView.findViewById(R.id.descriptionTv)

    }
}