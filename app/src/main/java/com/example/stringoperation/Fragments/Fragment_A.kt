package com.example.stringoperation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import com.example.stringoperation.MainActivity
import com.example.stringoperation.R

class Fragment_A : Fragment() {
    private lateinit var reverseButton: Button
    private lateinit var splitButton: Button
    private lateinit var appendButton: Button
    private lateinit var splitComma: Button
    private lateinit var resultText: TextView
    private lateinit var result: TextView
    private lateinit var optionButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment__a, container, false)
        reverseButton = view.findViewById(R.id.reverseButton)
        splitButton = view.findViewById(R.id.splitButton)
        appendButton = view.findViewById(R.id.appendButton)
        splitComma = view.findViewById(R.id.splitcomButton)
        resultText = view.findViewById(R.id.resultText)
        result = view.findViewById(R.id.result)
        optionButton = view.findViewById(R.id.optionButton)
        appendButton.setOnClickListener {
            passOperation("append")
        }
        reverseButton.setOnClickListener {
            passOperation("reverse")
        }
        splitButton.setOnClickListener {
            passOperation("split")
        }
        splitComma.setOnClickListener {
            passOperation("splitComma")
        }
        optionButton.setOnClickListener {
            MainActivity.mode = 0
            displayHome()
            result.text = ""
        }
        if(savedInstanceState?.getInt("mode") == 1){
            result.text = savedInstanceState.getString("result")
            if(MainActivity.mode == 1){
                displayResult()
            }
            if(MainActivity.mode == 0) {
                displayHome()
            }

        }
        setFragmentResultListener("0"){ requestKey, bundle ->
            result.text = bundle.getString("output")
            displayResult()
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("mode",1)
        outState.putString("result", result.text.toString())
    }
    private fun passOperation(operation : String) {
        val bundle = Bundle()
        bundle.putString("operation", operation)
        val transaction = parentFragmentManager.beginTransaction()
        val fragmentB = Fragment_B()
        fragmentB.arguments = bundle
        transaction.replace(R.id.fragmentContainer, fragmentB )
        transaction.addToBackStack(null)
        transaction.commit()
    }

     fun displayHome() {
        appendButton.visibility = View.VISIBLE
        reverseButton.visibility = View.VISIBLE
        splitButton.visibility = View.VISIBLE
        splitComma.visibility = View.VISIBLE
        resultText.visibility = View.GONE
        result.visibility = View.GONE
        optionButton.visibility = View.GONE
    }
    fun displayResult() {
        MainActivity.mode = 1
        appendButton.visibility = View.GONE
        reverseButton.visibility = View.GONE
        splitButton.visibility = View.GONE
        splitComma.visibility = View.GONE
        resultText.visibility = View.VISIBLE
        result.visibility = View.VISIBLE
        optionButton.visibility = View.VISIBLE
    }
}