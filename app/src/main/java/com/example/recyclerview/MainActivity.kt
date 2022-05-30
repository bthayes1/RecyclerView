package com.example.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout)
        val data = createData()
        val rvContact = findViewById<RecyclerView>(R.id.rvContacts)
        val adapter = ContactAdapter(this, data)
        rvContact.adapter = adapter
        rvContact.layoutManager = LinearLayoutManager(this)

        val swipeToDelete = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val currentData = data[position]
                Log.i(TAG, "Item swiped at position: $position")
                data.removeAt(position)
                adapter.notifyItemRemoved(position)
                Snackbar.make(constraintLayout, "Item was deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo", UndoListener(position, currentData))
                    .setActionTextColor(Color.WHITE)
                    .show()
            }

            inner class UndoListener(private val position: Int, private val deletedItem: Any) :
                View.OnClickListener{

                override fun onClick(p0: View?) {
                    data.add(position, deletedItem)
                    adapter.notifyItemInserted(position)
                    Log.i(TAG, "onClick: Undo was pressed. Data was added at position: $position")
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(rvContact)
    }



    private fun createData() : MutableList<Any>{
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