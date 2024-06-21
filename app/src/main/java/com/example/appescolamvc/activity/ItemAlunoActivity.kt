package com.example.appescolamvc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appescolamvc.databinding.ActivityItemAlunoBinding

class ItemAlunoActivity : AppCompatActivity() {

    private val binding by lazy {
       ActivityItemAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}