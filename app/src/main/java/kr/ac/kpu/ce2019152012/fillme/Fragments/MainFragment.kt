package kr.ac.kpu.ce2019152012.fillme.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kr.ac.kpu.ce2019152012.fillme.Adapter.DetailPagerAdapter
import kr.ac.kpu.ce2019152012.fillme.FragmentsOfMain.DayTabViewpager
import kr.ac.kpu.ce2019152012.fillme.FragmentsOfMain.MonthTabViewpager
import kr.ac.kpu.ce2019152012.fillme.FragmentsOfMain.WeekTabViewpager
import kr.ac.kpu.ce2019152012.fillme.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore

    lateinit var myfrag : View
    lateinit var viewPagers : ViewPager
    lateinit var tabLayouts : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewPager()
        tabLayouts.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setUpViewPager() {
        var adapter = DetailPagerAdapter(requireFragmentManager())
        adapter.addFragment(MonthTabViewpager(),"month")
        adapter.addFragment(WeekTabViewpager(),"week")
        adapter.addFragment(DayTabViewpager(),"day")

        binding.viewPager!!.adapter = adapter
        binding.tabLayout!!.setupWithViewPager(binding.viewPager)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{

    }
}