package com.epsilon.plants.Ui.Home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.epsilon.plants.Data.MyData
import com.epsilon.plants.R
import com.epsilon.plants.Ui.about.AboutActivity
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var carouselView: CarouselView

    private val sampleImages = intArrayOf(R.drawable.fotomonstera, R.drawable.fotopotgroot, R.drawable.fotokaktus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        rvProduct.setHasFixedSize(true)
        supportActionBar?.title = "Plants"
        supportActionBar?.elevation = 0F

        carouselView = findViewById(R.id.carouselView)
        carouselView.pageCount = sampleImages.size
        carouselView.setImageListener(imageListener)

        list.addAll(getListMyData())
        showRecyclerList()
    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            imageView.setImageResource(sampleImages[position])
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

    private fun showRecyclerList() {
        rvProduct.layoutManager = LinearLayoutManager(this)
        val AdapterMain = AdapterMain(list, this@HomeActivity)
        rvProduct.adapter = AdapterMain
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflate = menuInflater
        menuInflate.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        intent = Intent(applicationContext, AboutActivity::class.java)
        when(item.getItemId()){
            R.id.action_about -> {
                startActivity(intent)
            }
            R.id.action_cart -> {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/3ojCr4j"))
                if(intent.resolveActivity(packageManager)!=null){
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}