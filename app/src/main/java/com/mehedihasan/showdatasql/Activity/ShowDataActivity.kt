package com.mehedihasan.showdatasql.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mehedihasan.showdatasql.Adapter.UserAdapter
import com.mehedihasan.showdatasql.DatabaseHelper
import com.mehedihasan.showdatasql.ModelClass.User
import com.mehedihasan.showdatasql.R
import java.util.ArrayList

class ShowDataActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var userList: List<User>
    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var toolBar: Toolbar
    private lateinit var imageView: ImageView
    private lateinit var addIcon: ImageView

    private lateinit var searchEdt: EditText
    private lateinit var searchBtn: ImageView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
//TODO Connecting Toolbar
        toolBar=findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
//TODO Connecting Back Arrow
        imageView=findViewById(R.id.backImage)
        addIcon=findViewById(R.id.addIcon)

        //TODO For Search
        searchEdt=findViewById(R.id.searchEdt)
        searchBtn=findViewById(R.id.searchBtn)

        //TODO All Collection.........
        databaseHelper= DatabaseHelper(this)
        recyclerView=findViewById(R.id.recyclerViewShowData)
        recyclerView.layoutManager=LinearLayoutManager(this)
        userList = ArrayList()
        adapter = UserAdapter(userList as ArrayList<User>)
        recyclerView.adapter = adapter

        //TODO Insert Data Code
        val cursor=databaseHelper.showData()
        while (cursor.moveToNext()){
           val id= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID))
           val tittle= cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TITTLE))
           val description= cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRIPTION))

            (userList as ArrayList<User>).add(User(id, tittle, description))
            adapter.notifyDataSetChanged()
        }


        //TODO ON Click Listner to Back Arrow
        imageView.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        })

        //TODO On Click Listner to Add Icon
        addIcon.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity :: class.java))
        })


        //TODO On Click Listner To Search Button
        searchBtn.setOnClickListener(View.OnClickListener {

            var Id=searchEdt.getText().toString()

            if (Id.isEmpty()){
                Toast.makeText(this@ShowDataActivity,"Enter ID First",Toast.LENGTH_SHORT).show()
            }else{
                val cursor=databaseHelper.searchData(Integer.parseInt(Id))

                while (cursor.moveToNext()){
                    val tittle= cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TITTLE))
                    val description= cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRIPTION))


                    // Create the object of AlertDialog Builder class
                    val builder = AlertDialog.Builder(this)

                    // Set the message show for the Alert time
                    builder.setMessage("TITLE:" +tittle +"\n Description:" +description)
                    // Set Alert Title
                    builder.setTitle("Search Result For ID:"+Id)
                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                    builder.setCancelable(false)

                    // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setNegativeButton("Cancel") {
                        // If user click no then dialog box is canceled.
                            dialog, which -> dialog.cancel()
                    }
                    // Create the Alert dialog
                    val alertDialog = builder.create()
                    // Show the Alert Dialog box
                    alertDialog.show()
                }


                //Toast.makeText(this@ShowDataActivity,"Title:"+tittle +"Description"+description,Toast.LENGTH_SHORT).show()
            }
        })
    }
}