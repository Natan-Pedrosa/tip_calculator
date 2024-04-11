package com.example.tipcalculator

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.editText.doOnTextChanged  { text, _, _, _ ->
            val valueText = text.toString()

            if(valueText.isNotEmpty() && valueText.toBigDecimal() > BigDecimal.ZERO){
                val amount = valueText.toBigDecimal().setScale(2, RoundingMode.UP)
                val tipPercent = binding.seekBar.progress
                val tipAmount = amount * tipPercent.toBigDecimal() / 100.toBigDecimal()

                binding.billValueTv.text = "Bill Value: $$amount"
                binding.tipPercentTv.text = "Tip: $tipPercent%"
                binding.tipAmountTv.text = "Tip Amount: $$tipAmount"
            }else{

                binding.billValueTv.text = ""
                binding.tipPercentTv.text = ""
                binding.tipAmountTv.text = ""
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val valueText = binding.editText.text.toString()

                if(valueText.isNotEmpty() && valueText.toBigDecimal() > BigDecimal.ZERO){
                    val amount = valueText.toBigDecimal().setScale(2, RoundingMode.UP)
                    val tipAmount = amount * p1.toBigDecimal() / 100.toBigDecimal()
                    binding.tipPercentTv.text = "Tip: $p1%"
                    binding.tipAmountTv.text = "Tip Amount: $$tipAmount"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

    }
}
