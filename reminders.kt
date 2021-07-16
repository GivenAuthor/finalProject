package com.example.philbert

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class reminders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)
        val Username=intent.getStringExtra("Username").toString()

        val toHome = findViewById<Button>(R.id.back)
        toHome.setOnClickListener{
            val intent = Intent(this, homePage::class.java)
            intent.putExtra("Username", Username)
            startActivity(intent) }

        val save = findViewById<Button>(R.id.save)
        save.setOnClickListener{

            val time = findViewById<EditText>(R.id.editTextTime).getText().toString()
            val hour1 = time.get(0)
            val hour2 = time.get(1)
            val hour = "$hour1 $hour2"

            val min1 = time.get(0)
            val min2 = time.get(1)
            val min = "$min1 $min2"

            val reminderNote = findViewById<EditText>(R.id.editTextTextPersonName).getText().toString()
            val i = Intent(AlarmClock.ACTION_SET_ALARM)

            val monday = findViewById<Switch>(R.id.monday).isChecked
            if (monday) {i.putExtra(AlarmClock.EXTRA_DAYS, "monday")}

            val tuesday = findViewById<Switch>(R.id.tuesday).isChecked
            if (tuesday) {i.putExtra(AlarmClock.EXTRA_DAYS, "tuesday")}

            val wednesday = findViewById<Switch>(R.id.wednesday).isChecked
            if (wednesday) {i.putExtra(AlarmClock.EXTRA_DAYS, "wednesday")}

            val thursday = findViewById<Switch>(R.id.thursday).isChecked
            if (thursday) {i.putExtra(AlarmClock.EXTRA_DAYS, "thursday")}

            val friday = findViewById<Switch>(R.id.friday).isChecked
            if (friday) {i.putExtra(AlarmClock.EXTRA_DAYS, "friday")}

            val saturday = findViewById<Switch>(R.id.saturday).isChecked
            if (saturday) {i.putExtra(AlarmClock.EXTRA_DAYS, "saturday")}

            val sunday = findViewById<Switch>(R.id.sunday).isChecked
            if (sunday) {i.putExtra(AlarmClock.EXTRA_DAYS, "sunday")}

            i.putExtra(AlarmClock.EXTRA_MESSAGE, reminderNote)
            i.putExtra(AlarmClock.EXTRA_HOUR, hour)
            i.putExtra(AlarmClock.EXTRA_MINUTES, min)
            startActivity(i)

            val intent = Intent(this, homePage::class.java)
            intent.putExtra("Username", Username)
            startActivity(intent)
        }

    }
}