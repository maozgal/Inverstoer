package com.gal.investor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.gal.investor.add_data.add.apartment.AddApartmentActivity
import com.gal.investor.add_data.add.element.AddInvestmentElementActivity
import com.gal.investor.display.apartments.DisplayApartmentsActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<Button>(R.id.show_button).setOnClickListener {
            val intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.add_button_type).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.add_button_element).setOnClickListener {
            val intent = Intent(this, AddInvestmentElementActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.add_button_apartment).setOnClickListener {
            val intent = Intent(this, AddApartmentActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.show_apartments).setOnClickListener {
            val intent = Intent(this, DisplayApartmentsActivity::class.java)
            startActivity(intent)
        }
    }
}