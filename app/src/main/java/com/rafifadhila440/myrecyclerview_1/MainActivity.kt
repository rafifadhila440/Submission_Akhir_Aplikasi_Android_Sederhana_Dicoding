package com.rafifadhila440.myrecyclerview_1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvKucings: RecyclerView
    private val list = ArrayList<Kucing>()

    companion object{
        val INTENT_PARCEABLE = "OBJECT_INTENT"
    }

    // RecyclerView rv_kucings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvKucings = findViewById(R.id.rv_kucings)
        rvKucings.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvKucings.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvKucings.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, About::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //Mengambil data pada string
    private fun getListHeroes(): ArrayList<Kucing> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDetail = resources.getStringArray(R.array.detail)
        val dataUsia = resources.getStringArray(R.array.usia)
        val listKucings = ArrayList<Kucing>()
        for (i in dataName.indices) {
            val kucing = Kucing(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataUsia[i], dataDetail[i])
            listKucings.add(kucing)
        }
        return listKucings
    }

    private fun showRecyclerList() {
        rvKucings.layoutManager = LinearLayoutManager(this)
        val listKucingAdapter = ListKucingAdapter(list)
        rvKucings.adapter = listKucingAdapter

        listKucingAdapter.setOnItemClickCallback(object : ListKucingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Kucing) {
                showSelectedKucing(data)
            }
        })

    }

    //Muncul pesan "Kamu memilih kucing...
    private fun showSelectedKucing(kucing: Kucing) {
        Toast.makeText(this, "Kamu memilih " + kucing.name, Toast.LENGTH_SHORT).show()

        //Pindah ke halaman detail kucing
        val intent = Intent(this, DetailKucing::class.java)
        intent.putExtra(INTENT_PARCEABLE, kucing)
        startActivity(intent)
    }

}