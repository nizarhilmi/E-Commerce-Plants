package com.epsilon.plants.Ui.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epsilon.plants.Data.MyData
import com.epsilon.plants.R
import com.epsilon.plants.Ui.about.AboutActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.fab
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        val myData by
        getParcelableExtra<MyData>(DetailActivity.EXTRA_MYDATA)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        product_name.text = myData?.name.toString()
        product_price.text = myData?.price.toString()

        Glide.with(this)
            .load(myData?.photo.toString())
            .apply(RequestOptions().override(700, 700))
            .into(product_photo)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Berhasil Menyukai Produk", Snackbar.LENGTH_LONG).show()
        }

        btnBuy.setOnClickListener{ view ->
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/3ogX1Cj"))
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }
        }
    }

    companion object {
        const val EXTRA_MYDATA = "extra_mydata"
    }
    private inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = lazy {
        intent.getParcelableExtra<T>(key)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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