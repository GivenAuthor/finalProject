package com.example.philbert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.DialogInterface
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val register = findViewById<Button>(R.id.register)
        register.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener{
            val email = findViewById<EditText>(R.id.username).getText().toString()
            val password = findViewById<EditText>(R.id.password).getText().toString()

            val db = FirebaseFirestore.getInstance()
            var response = ""
            val docRef = db.collection("users").document(email)
            val passwordFromDB = docRef.get()
                    .addOnSuccessListener { document ->
                        print("document successfully fetched")
                        response = document.getString("password").toString()
                        if (response == password) {
                            val intent = Intent(this, homePage::class.java)
                            intent.putExtra("Username", email)
                            startActivity(intent)
                        }
                        else
                        {
                            val dialogBuilder = AlertDialog.Builder(this)
                            dialogBuilder.setMessage("password: " + password + "response: " + response)
                                    .setCancelable(false)
                                    .setNegativeButton("Okay", DialogInterface.OnClickListener {
                                        dialog, id -> dialog.cancel()
                                    })

                            val alert = dialogBuilder.create()
                            alert.setTitle("AlertDialogExample")
                            alert.show()
                        }
                    }
                    .addOnFailureListener {
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setMessage("Invalid username or password")
                                .setCancelable(false)
                                .setNegativeButton("Okay", DialogInterface.OnClickListener {
                                    dialog, id -> dialog.cancel()
                                })

                        val alert = dialogBuilder.create()
                        alert.setTitle("AlertDialogExample")
                        alert.show()
                    }
        }
    }
}