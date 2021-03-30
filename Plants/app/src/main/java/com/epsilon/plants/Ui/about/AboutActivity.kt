package com.epsilon.plants.Ui.about

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epsilon.plants.Data.MyData
import com.epsilon.plants.R
import com.epsilon.plants.Ui.Home.AdapterMain
import com.epsilon.plants.Ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_home.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        rvProduct.setHasFixedSize(true)
        list.addAll(getListMyData())
        setSupportActionBar(toolbar)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvProduct.layoutManager = LinearLayoutManager(this)
        val AdapterMain = AdapterMain(list, this@AboutActivity)
        rvProduct.adapter = AdapterMain

        fab.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/3pkKEqp"))
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }
        }
    }

    private val list = ArrayList<MyData>()
    private fun getListMyData(): ArrayList<MyData> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listMyData = ArrayList<MyData>()
        for (position in dataName.indices) {
            val myData = MyData(
                dataName[position],
                dataPrice[position],
                dataPhoto[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflate = menuInflater
        menuInflate.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.getItemId()){
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "Feature is locked", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}