package com.mehedihasan.showdatasql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class UserAdapter (private val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.model_design_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {

        val user = users[position]
        holder.idTv.text="ID:"+user.id.toString()
        holder.tittleTv.text=user.tittle
        holder.descriptionTv.text=user.description
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    //TODO View Holder Class
    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
         val idTv: TextView=itemView.findViewById(R.id.idTv)
        val tittleTv: TextView=itemView.findViewById(R.id.tittleTv)
        val descriptionTv: TextView=itemView.findViewById(R.id.descriptionTv)

    }
}