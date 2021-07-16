package com.example.philbert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.DialogInterface
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener{
            val email = findViewById<EditText>(R.id.username).getText().toString()
            val password = findViewById<EditText>(R.id.password).getText().toString()
            val encryptKey = "blue"
            val encryptedPassword = "$password $encryptKey"

            val canConnect = true;
            /*
                val verification = dbcollection(users).document(email).get()
                if (verification === "password") { canConnect = true}
             */

            if (canConnect) {
                val intent = Intent(this, homePage::class.java)
                intent.putExtra("Username", email)
                startActivity(intent)
            }
            else
            {
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