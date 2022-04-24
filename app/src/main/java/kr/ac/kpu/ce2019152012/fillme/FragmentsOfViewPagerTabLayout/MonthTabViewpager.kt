package kr.ac.kpu.ce2019152012.fillme.FragmentsOfViewPagerTabLayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import kr.ac.kpu.ce2019152012.fillme.R
import kr.ac.kpu.ce2019152012.fillme.databinding.TabViewpagerMonthBinding
import kr.ac.kpu.ce2019152012.fillme.view.EventDecorator
import kr.ac.kpu.ce2019152012.fillme.view.TodayDecorator
import java.util.*

class MonthTabViewpager : Fragment() {

    private lateinit var binding: TabViewpagerMonthBinding

    lateinit var calendar : MaterialCalendarView

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

// 화면
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabViewpagerMonthBinding.inflate(inflater,container,false)
        val view = binding.root

        calendar = view.findViewById(R.id.calendar)
        calendar.setSelectedDate(CalendarDay.today())
        calendar.addDecorator(TodayDecorator())
        return view
    }

    // 기능
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendar.setOnDateChangedListener(object: OnDateSelectedListener{
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                binding.calendar.addDecorator(EventDecorator(Collections.singleton(date)))
            }
        })

    }
}