package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.imc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainIMCActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var cvMaleSelected: Boolean = true
    private var cvFemaleSelected: Boolean = false
    private var currentWeight: Int = 50
    private var currentAge: Int = 30
    private var currentHeight: Int = 120

    companion object {
        const val IMC_KEY: String = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initUI()
    }

    private fun initListener() {

        binding.cvMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        binding.cvFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        binding.rsHeight.addOnChangeListener { _, value, _ ->

            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            binding.tvHeight.text = getString(R.string.cmHeight, "$currentHeight")
        }

        binding.btnAddWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        binding.btnGetWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        binding.btnAddAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        binding.btnGetAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        binding.btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {

        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {

        val df = DecimalFormat("#.##")
        val height: Double = currentHeight.toDouble() / 100
        val imc: Double = currentWeight / (height * height)
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        binding.tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        binding.tvWeight.text = currentWeight.toString()
    }

    private fun changeGender() {
        cvMaleSelected = !cvMaleSelected
        cvFemaleSelected = !cvFemaleSelected
    }


    private fun setGenderColor() {

        binding.cvMale.setCardBackgroundColor(getBackgroundColor(isSelectedComponent = cvMaleSelected))
        binding.cvFemale.setCardBackgroundColor(getBackgroundColor(isSelectedComponent = cvFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }


}