package com.example.imc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.MainIMCActivity.Companion.IMC_KEY
import com.example.imc.databinding.ActivityResultImcactivityBinding

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultImcactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initUi(result)
        initListener()
    }

    private fun initListener() {

        binding.btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUi(result: Double) {

        binding.tvImc.text = result.toString()

        when (result) {
            in 0.00..18.50 -> {
                binding.tvResult.text = getString(R.string.low_weight)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.warning_color))

                binding.tvDescription.text = getString(R.string.low_weight_description)
            }

            in 18.51..24.99 -> {
                binding.tvResult.text = getString(R.string.medium_weight)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_color))


                binding.tvDescription.text = getString(R.string.medium_weight_description)
            }

            in 25.00..29.99 -> {
                binding.tvResult.text = getString(R.string.high_weight)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.danger_color))


                binding.tvDescription.text = getString(R.string.high_weight_description)
            }

            in 30.00..99.00 -> {
                binding.tvResult.text = getString(R.string.fat_weight)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.very_danger_color))

                binding.tvDescription.text = getString(R.string.fat_weight_description)
            }

            else -> {

                binding.tvImc.text = getString(R.string.error)
                binding.tvResult.text = getString(R.string.error)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.very_danger_color))

                binding.tvDescription.text = getString(R.string.error)
            }
        }
    }
}