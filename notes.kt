package com.example.philbert


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import com.google.firebase.firestore.FirebaseFirestore

class notes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        MultiDex.install(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        val Username=intent.getStringExtra("Username").toString()

        val userNote = findViewById<EditText>(R.id.addNote).getText().toString()
        val date = findViewById<EditText>(R.id.noteDate).getText().toString()

        val submit = findViewById<Button>(R.id.saveNote)
        submit.setOnClickListener {
            val noteFull = "$userNote $date"
            val setNote = hashMapOf("note" to noteFull)

            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("notes").document("test")
            docRef.set(setNote)

            val toHome = findViewById<Button>(R.id.toHome)
            toHome.setOnClickListener{
                val intent = Intent(this, com.example.philbert.homePage::class.java)
                intent.putExtra("Username", Username)
                startActivity(intent)
            }

        }
    }
}