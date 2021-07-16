package com.example.philbert


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class notes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        val Username=intent.getStringExtra("Username").toString()

        val userNote = findViewById<EditText>(R.id.addNote).getText().toString()
        //val date = findViewById<EditText>(R.id.noteDate).getText().toString()

        val submit = findViewById<Button>(R.id.saveNote)
        submit.setOnClickListener {
            //val noteFull = "$userNote $date"

            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("notes").document("gre18007@byui.edu")
            docRef.set("test")

            val toHome = findViewById<Button>(R.id.toHome)
            toHome.setOnClickListener{
                val intent = Intent(this, com.example.philbert.homePage::class.java)
                intent.putExtra("Username", Username)
                startActivity(intent)
            }

        }
    }
}