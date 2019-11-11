package com.example.myroomdbapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.myroomdbapplication.R
import com.example.myroomdbapplication.adapter.CarsAdapter
import com.example.myroomdbapplication.database.CarDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InputFragment.FragmentListener {

    lateinit var carDB: CarDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this)
            .load(getString(R.string.logo_uri))
            .into(logo_imageview)

        carDB = Room.databaseBuilder(
            this,
            CarDatabase::class.java,
            "Cars.db"
        )
            .allowMainThreadQueries()
            .build()

        insert_car.setOnClickListener {
            val fragment = InputFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_framelayout, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        }
        setUpAdapter()

        val itemDecoration =  DividerItemDecoration(this, VERTICAL)
        cars_recyclerview.addItemDecoration(itemDecoration)
        cars_recyclerview.addItemDecoration(itemDecoration)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is InputFragment)
            (fragment as InputFragment).setListener(this)
    }

    private fun setUpAdapter() {
        val adapter = CarsAdapter(carDB.carDAO().retrieveAllCars())
        cars_recyclerview.adapter = adapter
        cars_recyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun updateResults() {
        setUpAdapter()
    }
}
