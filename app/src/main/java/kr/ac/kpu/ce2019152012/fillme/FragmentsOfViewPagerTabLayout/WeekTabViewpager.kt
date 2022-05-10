package kr.ac.kpu.ce2019152012.fillme.FragmentsOfViewPagerTabLayout

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import kr.ac.kpu.ce2019152012.fillme.R
import kr.ac.kpu.ce2019152012.fillme.databinding.TabViewpagerWeekBinding
import java.lang.Exception
import java.util.*

class WeekTabViewpager : Fragment() {
    private lateinit var binding: TabViewpagerWeekBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var itemRef = db.collection(auth.currentUser?.email.toString().trim())

    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR).toString()

    // 이번 달
    val _month = calendar.get((Calendar.MONTH))
    val month = (_month).toString()

    // 오늘 날짜
    val _day = calendar.get(Calendar.DATE) // 오늘 날짜
    val day = (_day + 1).toString()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setup2()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabViewpagerWeekBinding.inflate(inflater, container, false)
        val view = binding.root

        // 오늘 날짜로 초기화
        calendar.time = Date()

        // 이번 주가 몇 번 째 주인지
        binding.weekText.text =
            "${calendar.get(Calendar.MONTH)} ${calendar.get(Calendar.WEEK_OF_MONTH)} 주"

        //일요일
        calendar.add(Calendar.DAY_OF_MONTH, (8 - calendar.get(Calendar.DAY_OF_WEEK)))
        val sundayDate = calendar.time
        val sunday = dateDay(sundayDate)
        binding.weekDayNum1.setText(sunday)

        // 토요일
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, (7 - calendar.get(Calendar.DAY_OF_WEEK)))
        val satdayDate = calendar.time
        val satday = dateDay(satdayDate)
        binding.weekDayNum7.setText(satday)

        // 금요일
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, (6 - calendar.get(Calendar.DAY_OF_WEEK)))
        val fridayDate = calendar.time
        val friday = dateDay(fridayDate)
        binding.weekDayNum6.setText(friday)

        // 목요일
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, (5 - calendar.get(Calendar.DAY_OF_WEEK)))
        val thudayDate = calendar.time
        val thuday = dateDay(thudayDate)
        binding.weekDayNum5.setText(thuday)

        // 수요일
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, (4 - calendar.get(Calendar.DAY_OF_WEEK)))
        val weddayDate = calendar.time
        val wedday = dateDay(weddayDate)
        binding.weekDayNum4.setText(wedday)

        // 화요일
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, (3 - calendar.get(Calendar.DAY_OF_WEEK)))
        val tuedayDate = calendar.time
        val tueday = dateDay(tuedayDate)
        binding.weekDayNum3.setText(tueday)

        // 월요일
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, (2 - calendar.get(Calendar.DAY_OF_WEEK)))
        val mondayDate = calendar.time
        val monday = dateDay(mondayDate)
        binding.weekDayNum2.setText(monday)

        // 날짜로 document 받아서 퍼센트 게이지 표시

        // 날짜에 데이터가 없으면 0 표기 있으면 값 표기
        db.collection(auth.currentUser?.email.toString().trim()).document("${year}.${month}")
            .get().addOnSuccessListener {
                val weekSumGauge = hashMapOf<String,Int>(
                    sunday to 0,
                    satday to 0,
                    friday to 0,
                    thuday to 0,
                    wedday to 0,
                    tueday to 0,
                    monday to 0,
                )
                //일요일 게이지
                if (it[sunday]?.toString() == null){ WeekGaugeCheck(sunday)
                } else{ // 이번주 일요일 field에 value 값이 있다면
                    WeekGaugeText(sunday)
                    weekSumGauge.replace(sunday,it[sunday].toString().toInt())
                }
                //토요일 게이지
                if (it[satday]?.toString() == null){ WeekGaugeCheck(satday)
                } else{ // 이번주 일요일 field에 value 값이 있다면
                    WeekGaugeText(satday)
                    weekSumGauge.replace(satday,it[satday].toString().toInt())}
                //금요일 게이지
                if (it[friday]?.toString() == null){ WeekGaugeCheck(friday)
                } else{ // 이번주 일요일 field에 value 값이 있다면
                    WeekGaugeText(friday)
                    weekSumGauge.replace(friday,it[friday].toString().toInt())}
                //목요일 게이지
                if (it[thuday]?.toString() == null){ WeekGaugeCheck(thuday)
                } else{ // 이번주 일요일 field에 value 값이 있다면
                    WeekGaugeText(thuday)
                    weekSumGauge.replace(thuday,it[thuday].toString().toInt())}
                //수요일 게이지
                if (it[wedday]?.toString() == null){ WeekGaugeCheck(wedday)
                } else{ // 이번주 일요일 field에 value 값이 있다면
                    WeekGaugeText(wedday)
                    weekSumGauge.replace(wedday,it[wedday].toString().toInt())}
                //화요일 게이지
                if (it[tueday]?.toString() == null){ WeekGaugeCheck(tueday)
                } else{ // 이번주 일요일 field에 value 값이 있다면
                    WeekGaugeText(tueday)
                    weekSumGauge.replace(tueday,it[tueday].toString().toInt())}
                //월요일 게이지
                if (it[monday]?.toString() == null){ WeekGaugeCheck(monday)
                } else{ // 이번주 일요일 field에 value 값이 있다면
                    WeekGaugeText(monday)
                    weekSumGauge.replace(monday,it[monday].toString().toInt())}

                // 평균 데이터
                // when 으로 범위 나눠서 반올림해서 하는 식으로
                var sum :Double = 0.0
                for ((key,value) in weekSumGauge) {
                    sum += value
                }
                var res = sum/7
                when(sum){
                    in 0.0..25.0 ->
                    {
                        binding.battery1.setBackgroundColor(resources.getColor(R.color.batteryone))
                        binding.battery2.setBackgroundColor(resources.getColor(R.color.noborderwhite))
                        binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
                        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
                    }
                    in 26.0..50.0 ->
                    {
                        binding.battery1.setBackgroundColor(resources.getColor(R.color.batterytwo))
                        binding.battery2.setBackgroundColor(resources.getColor(R.color.batterytwo))
                        binding.battery3.setBackgroundColor(resources.getColor(R.color.noborderwhite))
                        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
                    }
                    in 51.0..75.0 ->
                    {
                        binding.battery1.setBackgroundColor(resources.getColor(R.color.batterythree))
                        binding.battery2.setBackgroundColor(resources.getColor(R.color.batterythree))
                        binding.battery3.setBackgroundColor(resources.getColor(R.color.batterythree))
                        binding.battery4.setBackgroundColor(resources.getColor(R.color.noborderwhite))
                    }
                    else ->
                    {
                        binding.battery1.setBackgroundColor(resources.getColor(R.color.batteryfour))
                        binding.battery2.setBackgroundColor(resources.getColor(R.color.batteryfour))
                        binding.battery3.setBackgroundColor(resources.getColor(R.color.batteryfour))
                        binding.battery4.setBackgroundColor(resources.getColor(R.color.batteryfour))
                    }
                }


            }.addOnFailureListener {
                Log.d("WeekGauge","Error")
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

    @RequiresApi(Build.VERSION_CODES.N)
    fun dateDay(date: Date): String {
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
        val day = dayFormat.format(date)
        return day
    }

    fun WeekGaugeCheck(day : String){
            db.collection(auth.currentUser?.email.toString())
                .document("${year}.${month}")
                .update(day, 0)
    }
    fun WeekGaugeText(day: String){
        itemRef.document("${year}.${month}")
            .get().addOnSuccessListener {
                binding.gaugeNumber1.text = it[day]?.toString()!!.trim()
            }
    }

}