package com.example.myroomdbapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.myroomdbapplication.R
import com.example.myroomdbapplication.database.CarDatabase
import com.example.myroomdbapplication.database.CarEntity
import kotlinx.android.synthetic.main.car_insert_layout.*

class InputFragment : Fragment() {

    interface FragmentListener{
        fun updateResults()
    }

    fun setListener(fragmentListener: FragmentListener){
        this.fragmentListener = fragmentListener
    }


    lateinit var fragmentListener: FragmentListener
    lateinit var database: CarDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.car_insert_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { fragmentActivity ->
            database = Room.databaseBuilder(
                fragmentActivity.applicationContext,
                CarDatabase::class.java,
                "Cars.db"
            )
                .allowMainThreadQueries()
                .build()
        }


        add_car_button.setOnClickListener {
            val name = carname_edittext.text.toString()
            val model = carmodel_edittext.text.toString()
            val price = Integer.parseInt(carprice_edittext.text.toString())
            val carEntity = CarEntity(name, model, price)
            database.carDAO().addNewCar(carEntity)
            clearTextFields()
            fragmentListener.updateResults()
            fragmentManager?.popBackStack()
        }
    }


    private fun clearTextFields() {
        carmodel_edittext.text.clear()
        carname_edittext.text.clear()
        carprice_edittext.text.clear()
    }
}