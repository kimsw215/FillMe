package kr.ac.kpu.ce2019152012.fillme.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import kr.ac.kpu.ce2019152012.fillme.databinding.FragmentMainBinding
import kr.ac.kpu.ce2019152012.fillme.databinding.FragmentMyBinding

class MyFragment:Fragment() {
    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore

    private val itemsCollectionRef = db.collection("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        setup2()

        // User 아이디 받아오는건데 auth.currentUser 로 받아올 수 있으면 버림
        var UserIdList = arrayListOf<String>()
        db.collection("User")
            .get()
            .addOnSuccessListener { result ->
                for(document in result) {
                    UserIdList.add(document.id.trim())
                    Log.d("list",UserIdList.toString())
                }
            }.addOnFailureListener { exception ->
                Log.d("Cloud",UserIdList.toString(),exception)
            }

        val user = Firebase.auth.currentUser

        if (user != null){
        } else{
            queryItem(user.toString().trim())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    // 기능 넣는 곳
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 다크모드
        binding.darkswitch.setOnClickListener {

        }

        // 로그아웃
        binding.logoutBtn.setOnClickListener {
            Firebase.auth.signOut()
        }
    }

    // Cloud Firestore - 데이터 읽기 -> Document의 필드 읽기
    private fun queryItem(itemID: String){
        itemsCollectionRef.document(itemID).get()
            .addOnSuccessListener{
                binding.nickname.setText(it["nickname"].toString() + " 님")
            }.addOnFailureListener{
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