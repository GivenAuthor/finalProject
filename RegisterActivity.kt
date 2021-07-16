package com.example.philbert

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val Username=intent.getStringExtra("Username").toString()

        val backToSignIn = findViewById<Button>(R.id.backToLogin)
        backToSignIn.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            intent.putExtra("Username", Username)
            startActivity(intent)
        }

        val createAccount = findViewById<Button>(R.id.createAccount)
            createAccount.setOnClickListener {
                val email = findViewById<EditText>(R.id.username2).getText().toString()
                val password = findViewById<EditText>(R.id.password2).getText().toString()

                if (email == "" || password == "") {
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setMessage("Username and Password cannot be blank")
                            .setCancelable(false)
                            .setNegativeButton("Okay", DialogInterface.OnClickListener {
                                dialog, id -> dialog.cancel()
                            })

                    val alert = dialogBuilder.create()
                    alert.setTitle("AlertDialogExample")
                    alert.show()
                }
                else {
                    val passwordMap = hashMapOf("password" to password)
                    val db = FirebaseFirestore.getInstance()
                    val docRef = db.collection("users").document(email)
                    docRef.set(passwordMap); // adds a document

                    val intent = Intent(this, loginActivity::class.java)
                    intent.putExtra("Username", Username)
                    startActivity(intent)
                }
            }
    }
}