package com.gal.investor.display.apartments

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gal.investor.R
import com.gal.investor.data.entities.Apartment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DisplayApartmentsActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null
    private val viewModel by viewModels<DisplayApartmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diaplay_apartments)

        setUI()
        setObservers()
    }

    private fun setObservers() {
        viewModel.apartmentsList.observe(this){
            (recycler?.adapter as ApartmentsAdapter).setApartmentsList(it)
        }
        viewModel.showMessage.observe(this){
            AlertDialog.Builder(this)
                .setMessage(it) // Specifying a listener allows you to take an action before
                .setPositiveButton(android.R.string.ok
                ) { _, _ ->
                    finish()
                }
                .show()
        }
    }

    private fun setUI() {
        recycler = findViewById(R.id.apartments_rv)
        recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.adapter = ApartmentsAdapter(object : ApartmentsAdapter.ApartmentItemListener{
            override fun onClick(apartment: Apartment) {
                viewModel.apartmentSelected(apartment)
            }
        })
    }
}