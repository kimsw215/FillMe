package kr.ac.kpu.ce2019152012.fillme.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kpu.ce2019152012.fillme.databinding.ActivityUserMainAcivityBinding

class UserMainAcivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserMainAcivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMainAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}