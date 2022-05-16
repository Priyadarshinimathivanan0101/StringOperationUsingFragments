package com.example.stringoperation.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.stringoperation.MainActivity
import com.example.stringoperation.R

class Fragment_B : Fragment() {
    private lateinit var string1: EditText
    private lateinit var string2: EditText
    private lateinit var operationButton : Button
    private lateinit var operation : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment__b, container, false)
        operation =arguments?.getString("operation").toString()
        string1 = view.findViewById(R.id.string1)
        string2 = view.findViewById(R.id.string2)
        operationButton = view.findViewById(R.id.doneButton)
        operationButton.text = operation
        // Inflate the layout for this fragment
        if(operation.equals("append"))
            string2.visibility = View.VISIBLE
        operationButton.setOnClickListener {
            when (operation.toString()) {
                "append" -> {
                    sendToFragment1("${string1.text} ${string2.text}")
                }
                "reverse" -> {
                    sendToFragment1((string1.text).toString().reversed())
                }
                "split" -> {
                    val chars: List<Char> = string1.text.toList()
                    sendToFragment1(chars.joinToString("  "))
                }
                "splitComma" -> {
                    val chars: List<Char> = string1.text.toList()
                    sendToFragment1(chars.joinToString(","))
                }
            }
        }
        return view
    }
    fun sendToFragment1(output: String) {
        MainActivity.mode = 1
        setFragmentResult("0", bundleOf("output" to output))
        parentFragmentManager.popBackStack()
    }
}