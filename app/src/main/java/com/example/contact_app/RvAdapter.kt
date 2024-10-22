package com.example.contact_app

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.contact_app.databinding.ItemBinding


class RvAdapter(var list: ArrayList<My_Contact>, var rvAction:RvActian ) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class  Vh( var item: ItemBinding):RecyclerView.ViewHolder(item.root) {
        fun onBind(myContact: My_Contact , position: Int) {
            item.itemName.text = myContact.name
            item.itemPassword.text=myContact.userName
            item.itemNumber.text = myContact.number
            item.more.setOnClickListener{
                rvAction.clikImageItem(item.more,position,myContact)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    interface RvActian{
        fun clikImageItem(imageView: ImageView, position:Int,myContact: My_Contact)
    }
}