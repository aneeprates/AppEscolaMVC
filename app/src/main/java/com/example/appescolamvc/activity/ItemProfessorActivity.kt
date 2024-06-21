package com.example.appescolamvc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appescolamvc.databinding.ActivityItemProfessorBinding

class ItemProfessorActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityItemProfessorBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}