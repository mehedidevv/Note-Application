package com.mehedihasan.showdatasql.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.ID
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mehedihasan.showdatasql.DatabaseHelper
import com.mehedihasan.showdatasql.R
import kotlin.properties.Delegates

class UpdateDataActivity : AppCompatActivity() {
    private lateinit var searchEdt: EditText
    private lateinit var searchBtn: ImageView
    private lateinit var tittleEdt: EditText
    private lateinit var descriptionEdt: EditText
    private lateinit var updateBtn: Button
    private lateinit var backImage: ImageView
    private lateinit var toolBarUpdate: Toolbar

    lateinit var Id: String

    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_data)


       // var ID: Int

        //TODO Connection
        searchEdt=findViewById(R.id.searchEdt)
        searchBtn=findViewById(R.id.searchBtn)
        tittleEdt=findViewById(R.id.tittleEdt)
        descriptionEdt=findViewById(R.id.descriptionEdt)
        updateBtn=findViewById(R.id.insertBtn)
        backImage=findViewById(R.id.backImage)
        toolBarUpdate=findViewById(R.id.toolBarUpdate)

        databaseHelper=DatabaseHelper(this)

        //TODO Toolbar
        setSupportActionBar(toolBarUpdate)
        backImage.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        })

        //TODO ON Click Listner to Search Button

        searchBtn.setOnClickListener(View.OnClickListener {

             Id=searchEdt.getText().toString()

            if (Id.isEmpty()) {
                Toast.makeText(this@UpdateDataActivity, "Enter ID First", Toast.LENGTH_SHORT).show()
            }else{
                val cursor=databaseHelper.searchData(Integer.parseInt(Id))

                while (cursor.moveToNext()) {
                    val tittle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TITTLE))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRIPTION))

                    tittleEdt.setText(tittle)
                    descriptionEdt.setText(description)
                }
            }

        })

        //TODO ON Click Listner to Update Button
        updateBtn.setOnClickListener(View.OnClickListener {

            val tittle=tittleEdt.getText().toString()
            val description=descriptionEdt.getText().toString()

            if (tittle.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Enter Tittle", Toast.LENGTH_SHORT).show()

            }else if (description.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Enter Description", Toast.LENGTH_SHORT).show()

            }else{
                //TODO Update Logic Will Here
                //val check: Boolean=databaseHelper.updateData(Integer.parseInt(id),tittle,description)
                val check = databaseHelper.updateData(Id.toInt(), tittle, description)

                if (check){
                    Toast.makeText(this,"Update Succesfully",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Something Wrong",Toast.LENGTH_SHORT).show()

                }
            }

        })

    }
}