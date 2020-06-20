package com.enigma.newspotify


import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun keTiga(view: View) {
        val newIntent = Intent(this, ThirdActivity::class.java)
        startActivity(newIntent)
    }

    fun kirimEmpat(view: View) {
        val newInsert = Intent(this, ThirdActivity::class.java)
        startActivity(newInsert)
    }

    fun seeList(view: View) {
        val newList = Intent(this, ListActivityS::class.java)
        startActivity(newList)
    }
}