package com.example.contact_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import com.example.contact_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnQoshish.setOnClickListener{
            val intent =Intent(this,AddActivity::class.java)
            intent.putExtra("key",2)
            startActivity(intent)
        }

        MySharedPreferense.init(this)
    }

    override fun onResume() {
        super.onResume()
        val list=MySharedPreferense.list
        rvAdapter= RvAdapter(list,  object : RvAdapter.RvActian{
            override fun clikImageItem(imageView: ImageView, position: Int, myContact: My_Contact) {
                val popupMenu = PopupMenu(this@MainActivity,imageView)
                popupMenu.inflate(R.menu.item_menu)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.deleit->{
                            list.removeAt(position)
                            MySharedPreferense.list=list
                            onResume()
                        }
                        R.id.edit->{
                            val intent=Intent(this@MainActivity,AddActivity::class.java)
                            intent.putExtra("key",1)
                            intent.putExtra("keyPosition",position)
                            startActivity(intent)
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        })
        binding.rv.adapter=rvAdapter
    }
}