package com.example.spowi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spowi.databinding.ActivityPlayListBinding

class PlayListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPink)
        binding = ActivityPlayListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}