package com.example.calculatriceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput : TextView? =null
    var lastNumeric :Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         tvInput = findViewById(R.id.tvInput)
    }
    fun onDigit(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot= false

    }
    fun onClear(view: View){
        tvInput?.text =""
    }

    fun onDecimalPoint(view: View){
        if ( lastNumeric && !lastDot){
            tvInput?.append(".")
            lastDot = true
            lastNumeric= false
        }
    }
    fun onOperationAdd(view: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperationAdd(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    private fun isOperationAdd(value: String) :Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
    private fun removeZeroAfterDot( result: String) : String{
        var value =result
        if (value.contains(".0"))
            value= result.substring(0,result.length -2)

        return value
    }

    fun OnEgalisation(view: View){
        if (lastNumeric){

            var tvvalue=tvInput?.text.toString()
            var prefix= ""

            try {
                if(tvvalue.startsWith("-")){
                    prefix ="-"
                    tvvalue.substring(1)
                }
                if (tvvalue.contains("-")){
                    val splitvalue = tvvalue.split("-")
                    var one = splitvalue[0]
                    val two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one= prefix + one
                    }
                    tvInput?.text=removeZeroAfterDot(( one.toDouble()-two.toDouble()).toString())
                } else if(tvvalue.contains("+")){
                    val splitvalue = tvvalue.split("+")
                    var one = splitvalue[0]
                    val two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one= prefix + one
                    }
                    tvInput?.text=removeZeroAfterDot(( one.toDouble()+two.toDouble()).toString())

            } else if(tvvalue.contains("/")){
                    val splitvalue = tvvalue.split("/")
                    var one = splitvalue[0]
                    val two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one= prefix + one
                    }
                    tvInput?.text=removeZeroAfterDot(( one.toDouble()/two.toDouble()).toString())
            }else if(tvvalue.contains("*")){
                    val splitvalue = tvvalue.split("*")
                    var one = splitvalue[0]
                    val two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one= prefix + one
                    }
                    tvInput?.text=removeZeroAfterDot(( one.toDouble()*two.toDouble()).toString())
            }
                } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}