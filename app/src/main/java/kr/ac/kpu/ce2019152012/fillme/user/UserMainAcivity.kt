package kr.ac.kpu.ce2019152012.fillme.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import kr.ac.kpu.ce2019152012.fillme.Fragments.HomeFragment
import kr.ac.kpu.ce2019152012.fillme.Fragments.MainFragment
import kr.ac.kpu.ce2019152012.fillme.Fragments.MyFragment
import kr.ac.kpu.ce2019152012.fillme.R
import kr.ac.kpu.ce2019152012.fillme.databinding.ActivityUserMainAcivityBinding

class UserMainAcivity : AppCompatActivity(),NavigationBarView.OnItemSelectedListener {
    lateinit var selectedFragment: Fragment
    private lateinit var binding: ActivityUserMainAcivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMainAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
        supportFragmentManager.beginTransaction().add(R.id.linearLayout,HomeFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_main -> {
                selectedFragment = MainFragment()
                show(selectedFragment)
            }
            R.id.menu_home -> {
                selectedFragment = HomeFragment()
                show(selectedFragment)
            }
            R.id.menu_mypage -> {
                selectedFragment = MyFragment()
                show(selectedFragment)
            }
        }
        return true
    }

    private fun show(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.linearLayout, fragment)
            .commit()
    }
}