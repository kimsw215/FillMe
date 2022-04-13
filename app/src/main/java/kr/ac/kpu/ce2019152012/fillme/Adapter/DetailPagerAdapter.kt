package kr.ac.kpu.ce2019152012.fillme.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class DetailPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager
    , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var fragmentList : MutableList<Fragment> = arrayListOf()
    var titleList : MutableList<String> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        //return fragmentList[position]
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //return titleList[position]
        return titleList.get(position)
    }
}