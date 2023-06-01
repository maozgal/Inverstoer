package com.gal.investor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowActivity : AppCompatActivity() {
    private val viewModel by viewModels<ShowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        viewModel.data.observe(this) {
            findViewById<TextView>(R.id.textView).text = it
        }
    }
}