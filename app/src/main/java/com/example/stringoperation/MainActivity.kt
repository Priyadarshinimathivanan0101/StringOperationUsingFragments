package com.example.stringoperation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stringoperation.Fragments.Fragment_A
import com.example.stringoperation.Fragments.Fragment_B

class MainActivity : AppCompatActivity() {
    companion object{
        var mode: Int = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragmentContainer, Fragment_A())
            fragmentTransaction.commit()
        }
    }

    override fun onBackPressed() {
        if(mode == 0) {
            super.onBackPressed()
        }
        else
        {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, Fragment_A())
            fragmentTransaction.commit()
            mode =0
        }
    }

}