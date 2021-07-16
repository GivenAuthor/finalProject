package com.example.philbert

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class homePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val Username=intent.getStringExtra("Username").toString()

        val db = FirebaseFirestore.getInstance()
        var response = ""
        val docRef = db.collection("notes").document(Username)
        val noteFromDB = docRef.get()
                .addOnSuccessListener { document ->
                    print("document successfully fetched")
                    response = document.getString("note").toString()
                    val userEditText = findViewById<EditText>(R.id.notes)
                    userEditText.setText(response.toString())
                    }
                .addOnFailureListener {
                    val userEditText = findViewById<EditText>(R.id.notes)
                    userEditText.setText("No current notes")
                }

        val toReminder = findViewById<Button>(R.id.addReminder)
        toReminder.setOnClickListener{
            val intent = Intent(this, reminders::class.java)
            intent.putExtra("Username", Username)
            startActivity(intent)
        }

        val toNotes = findViewById<Button>(R.id.addNote)
        toNotes.setOnClickListener{
            val intent = Intent(this, notes::class.java)
            intent.putExtra("Username", Username)
            startActivity(intent)
        }
    }
}