package com.bubu.workoutwithclient.userinterface

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.bubu.workoutwithclient.databinding.FragmentPostNewBinding
import com.bubu.workoutwithclient.retrofitinterface.UserCreateCommunityData
import com.bubu.workoutwithclient.retrofitinterface.UserCreateCommunityModule
import com.bubu.workoutwithclient.retrofitinterface.UserError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


suspend fun postCommunity(title : String, picture: File, content:String) {
    val postObject = UserCreateCommunityModule(UserCreateCommunityData(title,picture,content))
    val result = postObject.getApiData()
    if(result in 200..299) {
        Log.d("Community","Successful!")
    } else if(result is UserError) {

    } else {

    }
}


class PostNewFragment : Fragment() {

    lateinit var majorScreen: MajorScreen
    lateinit var binding: FragmentPostNewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostNewBinding.inflate(inflater, container, false)
        binding.btnPost.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                postCommunity(binding.editPostTitle.text.toString(),File("/storage/0B0A-1E06/Download/test.png"),binding.editPostContent.text.toString())
            }

            val bundle = bundleOf("valueKey" to "테스트")
            setFragmentResult("request", bundle)
            val direction = PostNewFragmentDirections.actionPostNewFragmentToCommunityFragment()
            findNavController().navigate(direction)
        }
        return binding.root
    }

    fun getdata() : MutableList<Community> {
        val data : MutableList<Community> = mutableListOf()
        val title = binding.editPostTitle.text.toString()
        val content = binding.editPostContent.text.toString()
        val editor = "작성자"
        val date = System.currentTimeMillis()

        //var community = Community(title,content,editor,"editTime")
        //data.add(community)
        return data
    }
}