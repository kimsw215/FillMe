package kr.ac.kpu.ce2019152012.fillme.FragmentsOfViewPagerTabLayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.ac.kpu.ce2019152012.fillme.databinding.TabViewpagerWeekBinding
import java.util.*

class WeekTabViewpager : Fragment() {
    private lateinit var binding: TabViewpagerWeekBinding
    val calendar =  Calendar.getInstance()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabViewpagerWeekBinding.inflate(inflater,container,false)
        val view = binding.root

        // 오늘 날
        calendar.time = Date()

        // 이번 주가 몇 번 째 주인지
        binding.weekText.text = "${calendar.get(Calendar.MONTH).toString()} ${calendar.get(Calendar.WEEK_OF_YEAR).toString()} 주"
        //일요일
        binding.weekDayNum1.text = calendar.add(Calendar.DAY_OF_MONTH, (8-calendar.get(Calendar.DAY_OF_WEEK))).toString()
        //토요일
        binding.weekDayNum7.text = calendar.add(Calendar.DAY_OF_MONTH, (7-calendar.get(Calendar.DAY_OF_WEEK))).toString()
        //금요일
        binding.weekDayNum6.text = calendar.add(Calendar.DAY_OF_MONTH, (6-calendar.get(Calendar.DAY_OF_WEEK))).toString()
        //목요일
        binding.weekDayNum5.text = calendar.add(Calendar.DAY_OF_MONTH, (5-calendar.get(Calendar.DAY_OF_WEEK))).toString()
        //수요일
        binding.weekDayNum4.text = calendar.add(Calendar.DAY_OF_MONTH, (4-calendar.get(Calendar.DAY_OF_WEEK))).toString()
        //화요일
        binding.weekDayNum3.text = calendar.add(Calendar.DAY_OF_MONTH, (3-calendar.get(Calendar.DAY_OF_WEEK))).toString()
        //월요일
        binding.weekDayNum2.text = calendar.add(Calendar.DAY_OF_MONTH, (2-calendar.get(Calendar.DAY_OF_WEEK))).toString()

        // 날짜로 document 받아서 퍼센트 게이지 표시



        // 평균 데이터
        // when 으로 범위 나눠서 반올림해서 하는 식으로

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}