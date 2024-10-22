package com.example.contact_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contact_app.MySharedPreferense.list
import com.example.contact_app.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val a = intent.getIntExtra("key",0)
        MySharedPreferense.init(this)
        if (a == 2) {
            addContact()
        } else if (a==1) {
            editContact()
        }
    }

    fun addContact() {

        binding.apply {
            btnSave.setOnClickListener {
                if (binding.number.text.isNotBlank() && binding.edtname.text.isNotBlank()) {
                    val myContact = My_Contact(
                        edtname.text.toString(),
                        number.text.toString(),
                        UserName.text.toString()
                    )
                    val list = MySharedPreferense.list
                    list.add(myContact)
                    MySharedPreferense.list = list
                    Toast.makeText(this@AddActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                    finish()
                } else {

                        Toast.makeText(this@AddActivity, "Malumot tolig kiritilmagan", Toast.LENGTH_SHORT)
                            .show()


                }
            }
        }
    }

    fun editContact() {
       val  position = intent.getIntExtra("keyPosition", -1)
        val list = MySharedPreferense.list
        val myContact = list[position]
        binding.apply {
            edtname.setText(myContact.name)
            number.setText(myContact.number)
            UserName.setText(myContact.userName)

            btnSave.setOnClickListener {
                myContact.name = edtname.text.toString()
                myContact.number = number.text.toString()
                myContact.userName=UserName.text.toString()
                list[position] = myContact
                MySharedPreferense.list = list
                finish()
            }
        }
    }
}
