package com.mehedihasan.showdatasql

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var tittleEdt: EditText
    private lateinit var descriptionEdt: EditText
    private lateinit var insertBtn: Button
    private lateinit var showDataBtn: Button

    //TODO DatabaseHelper Object Creation
    private lateinit var databaseHelper: DatabaseHelper


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //TODO Connection
        tittleEdt=findViewById(R.id.tittleEdt)
        descriptionEdt=findViewById(R.id.dexcriptionEdt)
        insertBtn=findViewById(R.id.insertBtn)
        showDataBtn=findViewById(R.id.showDataBtn)

        databaseHelper=DatabaseHelper(this)

        //TODO On Click Listner to Insert Button
        insertBtn.setOnClickListener(View.OnClickListener {

            var tittle: String =tittleEdt.getText().toString()
            var description: String =descriptionEdt.getText().toString()

            if (tittle.isEmpty()){
                Toast.makeText(this@MainActivity,"Enter Tittle",Toast.LENGTH_SHORT).show()
            }else if (description.isEmpty()){
                Toast.makeText(this@MainActivity,"Enter Description",Toast.LENGTH_SHORT).show()
            }else{
                //TODO Logic Will Aplly Here
                val id: Long=databaseHelper.insertData(tittle,description)
                Toast.makeText(this@MainActivity,"Data is Inserted And ID:"+id,Toast.LENGTH_SHORT).show()
            }

        })

        //TODO On Click Listner to Show Data Button
        showDataBtn.setOnClickListener(View.OnClickListener {

            startActivity(Intent(this, ShowDataActivity::class.java))

        })

    }
}