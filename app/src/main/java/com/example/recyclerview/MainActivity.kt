package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvContact = findViewById<RecyclerView>(R.id.rvContacts)
        rvContact.adapter = ContactAdapter(this, createData())
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun createData() : List<Any>{
        val list = mutableListOf<Any>()
        for (i in 0..150){
            val rand = (22..70).random()
            if (i % 4 == 0){
                list.add(Advertisment("No ad available"))
            }
            else {
                list.add(Contact("My name is $rand", rand))
            }
        }
        return list
    }
}