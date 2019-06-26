package com.example.pets.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pets.R
import com.example.pets.adapter.PetsAdapter
import com.example.pets.viewmodel.ContentViewModel
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var contentViewModel : ContentViewModel? = null
    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initValues()
        attachObserver()
    }

    /**
     * Initializing views
     */
    private fun initValues() {
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        rv_male_list.layoutManager = LinearLayoutManager(this)
        rv_female_list.layoutManager = LinearLayoutManager(this)
        contentViewModel?.getContents()
    }

    /**
     * Observer method for live data from viewmodel
     */
    private fun attachObserver() {
        contentViewModel?.petsListMaleOwner?.observe(this, Observer{ petsList ->
            contentAvailable()
            if(petsList.isEmpty()){
                tv_male.visibility = View.GONE
            }else{
                tv_male.visibility = View.VISIBLE
                rv_male_list.adapter = PetsAdapter(petsList.sortedBy { it.name }, this)
            }
        })

        contentViewModel?.petsListFemaleOwner?.observe(this, Observer{ petsList ->
            contentAvailable()
            if(petsList.isEmpty()){
                tv_female.visibility = View.GONE
            }else{
                tv_female.visibility = View.VISIBLE
                rv_female_list.adapter = PetsAdapter(petsList.sortedBy { it.name }, this)
            }
        })

        contentViewModel?.errorMessage?.observe(this, Observer{ message ->
            Log.e(TAG, message)
            noInternetConnection()
            Snackbar.make(content,getString(R.string.network_error),Snackbar.LENGTH_LONG).show()
        })
    }

    /**
     * set views when internet not available
     */
    private fun noInternetConnection(){
        no_network.visibility = View.VISIBLE
        content.visibility = View.GONE
    }

    /**
     * set views when content is availabe
     */
    private fun contentAvailable(){
        no_network.visibility = View.GONE
        content.visibility = View.VISIBLE
    }
}
