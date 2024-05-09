package com.rafifadhila440.myrecyclerview_1

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailKucing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_detail_kucing)

        supportActionBar?.apply {
            title = getString(R.string.detail_kucing)
        }

        val detailKucing = intent.getParcelableExtra<Kucing>(MainActivity.INTENT_PARCEABLE)

        val photo = findViewById<ImageView>(R.id.photo_img)
        val name = findViewById<TextView>(R.id.jenis_kucing)
        val usia = findViewById<TextView>(R.id.usia)
        val detail = findViewById<TextView>(R.id.description)

        photo.setImageResource(detailKucing?.photo!!)
        name.text = detailKucing.name
        usia.text= detailKucing.usia
        detail.text= detailKucing.detail
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }
}