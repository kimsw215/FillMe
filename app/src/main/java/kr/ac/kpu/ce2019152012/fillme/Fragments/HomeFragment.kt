package kr.ac.kpu.ce2019152012.fillme.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import kr.ac.kpu.ce2019152012.fillme.R
import kr.ac.kpu.ce2019152012.fillme.databinding.FragmentHomeBinding
import java.util.*
import java.util.logging.Logger.global

class HomeFragment: Fragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    // 버튼 체크 됐는지
    var btnCheck1 = false
    var btnCheck2 = false
    var btnCheck3 = false
    var btnCheck4 = false

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR).toString()
    val month = calendar.get((Calendar.MONTH) + 1).toString()
    val day = calendar.get(Calendar.DATE)

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
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.battery1.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery2.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))

        return view
    }

    // 기능
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.battery1.setOnClickListener {
            btnCheck1 = true
            btnCheck2 = false
            btnCheck3 = false
            btnCheck4 = false
            binding.battery1.setBackgroundColor(resources.getColor(R.color.batteryone))
            binding.battery2.setBackgroundColor(resources.getColor(R.color.noborderwhite))
            binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
            binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))

            binding.gaugeNumber.setText("25")
        }
        binding.battery2.setOnClickListener {
            btnCheck1 = false
            btnCheck2 = true
            btnCheck3 = false
            btnCheck4 = false
            binding.battery1.setBackgroundColor(resources.getColor(R.color.batterytwo))
            binding.battery2.setBackgroundColor(resources.getColor(R.color.batterytwo))
            binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
            binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
            binding.gaugeNumber.setText("50")
        }
        binding.battery3.setOnClickListener {
            btnCheck1 = false
            btnCheck2 = false
            btnCheck3 = true
            btnCheck4 = false
            binding.battery1.setBackgroundColor(resources.getColor(R.color.batterythree))
            binding.battery2.setBackgroundColor(resources.getColor(R.color.batterythree))
            binding.battery3.setBackgroundColor(resources.getColor(R.color.batterythree))
            binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
            binding.gaugeNumber.setText("75")
        }
        binding.battery4.setOnClickListener {
            btnCheck1 = false
            btnCheck2 = false
            btnCheck3 = false
            btnCheck4 = true
            binding.battery1.setBackgroundColor(resources.getColor(R.color.batteryfour))
            binding.battery2.setBackgroundColor(resources.getColor(R.color.batteryfour))
            binding.battery3.setBackgroundColor(resources.getColor(R.color.batteryfour))
            binding.battery4.setBackgroundColor(resources.getColor(R.color.batteryfour))
            binding.gaugeNumber.setText("100")
        }

        binding.fillBtn.setOnClickListener {
        /* bool 값 체크에 따라서 db.collection - document(22.04) <- 연도.월  -  field(오늘의 요일)에 넣고
           set으로 다시 갱신*/
            if(btnCheck1){
                db.collection(auth.currentUser?.email.toString())
                    .document("${year}.${month}")
                    .update(day.toString(),25)
            } else if(btnCheck2){
                db.collection(auth.currentUser?.email.toString())
                    .document("${year}.${month}")
                    .update(day.toString(),50)
            } else if(btnCheck3){
                db.collection(auth.currentUser?.email.toString())
                    .document("${year}.${month}")
                    .update(day.toString(),75)
            } else if(btnCheck4){
                db.collection(auth.currentUser?.email.toString())
                    .document("${year}.${month}")
                    .update(day.toString(),100)
            }
        }

    }

    fun setup2() {
        db = FirebaseFirestore.getInstance()
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        db.firestoreSettings = settings
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}