package kr.ac.kpu.ce2019152012.fillme.FragmentsOfViewPagerTabLayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import kr.ac.kpu.ce2019152012.fillme.R
import kr.ac.kpu.ce2019152012.fillme.databinding.TabViewpagerDayBinding
import java.util.*

class DayTabViewpager : Fragment() {
    private lateinit var binding: TabViewpagerDayBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR).toString()
    // 이번 달
    val _month = calendar.get((Calendar.MONTH))
    val month = (_month).toString()
    // 오늘 날짜
    val _day = calendar.get(Calendar.DATE) // 오늘 날짜
    val day = (_day+1).toString()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        setup2()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabViewpagerDayBinding.inflate(inflater,container,false)
        val view = binding.root

        // 오늘 날짜 설정
        binding.monthDayText.setText("${year}/${month}/${day}")

        // 오늘 날의 퍼센트 설정
        db.collection(auth.currentUser?.email.toString()).document("${year}.${month}")
            .get().addOnSuccessListener {
                binding.gaugeNumber.setText(it["${day}"].toString())

                // 배터리 설정
                when(day.toInt()){
                    0 -> switch0()
                    25 -> switch1()
                    50 -> swtich2()
                    75 -> swtich3()
                    100 -> swtich4()
                }
            }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setup2() {
        db = FirebaseFirestore.getInstance()
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        db.firestoreSettings = settings
    }

    fun switch0(){
        binding.battery1.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery2.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
    }

    fun switch1(){
        binding.battery1.setBackgroundColor(resources.getColor(R.color.batteryone))
        binding.battery2.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
    }

    fun swtich2(){
        binding.battery1.setBackgroundColor(resources.getColor(R.color.batterytwo))
        binding.battery2.setBackgroundColor(resources.getColor(R.color.batterytwo))
        binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
    }
    fun swtich3(){
        binding.battery1.setBackgroundColor(resources.getColor(R.color.batterythree))
        binding.battery2.setBackgroundColor(resources.getColor(R.color.batterythree))
        binding.battery3.setBackgroundColor(resources.getColor(R.color.batterythree))
        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
    }
    fun swtich4(){
        binding.battery1.setBackgroundColor(resources.getColor(R.color.batteryfour))
        binding.battery2.setBackgroundColor(resources.getColor(R.color.batteryfour))
        binding.battery3.setBackgroundColor(resources.getColor(R.color.batteryfour))
        binding.battery4.setBackgroundColor(resources.getColor(R.color.batteryfour))
    }
}