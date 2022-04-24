package kr.ac.kpu.ce2019152012.fillme.Fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.ac.kpu.ce2019152012.fillme.Adapter.PagerFragmentStateAdpater
import kr.ac.kpu.ce2019152012.fillme.FragmentsOfViewPagerTabLayout.DayTabViewpager
import kr.ac.kpu.ce2019152012.fillme.FragmentsOfViewPagerTabLayout.MonthTabViewpager
import kr.ac.kpu.ce2019152012.fillme.FragmentsOfViewPagerTabLayout.WeekTabViewpager
import kr.ac.kpu.ce2019152012.fillme.R
import kr.ac.kpu.ce2019152012.fillme.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private val tabTitleAttay = arrayOf(
        "월간",
        "주간",
        "일간"
    )
    private val tabIconArray = arrayOf(
        R.drawable.month_main,
        R.drawable.week_main,
        R.drawable.day_main
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        arguments?.let {
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = PagerFragmentStateAdpater(requireActivity())
        // 3개의 Fragment Add
        pagerAdapter.addFragment(MonthTabViewpager())
        pagerAdapter.addFragment(WeekTabViewpager())
        pagerAdapter.addFragment(DayTabViewpager())

        binding.viewPager.adapter = pagerAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment","Page ${position+1}")
            }
        })

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->
            tab.text = tabTitleAttay[position]
            tab.icon = AppCompatResources.getDrawable(requireContext(),tabIconArray[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{

    }
}