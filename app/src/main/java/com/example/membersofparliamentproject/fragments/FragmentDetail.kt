package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.databinding.FragmentDetailBinding
import java.time.LocalDate
import kotlin.properties.Delegates


class FragmentDetail : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var clickedMemberHetekaId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =FragmentDetailBinding.inflate(inflater,container,false)
        setFragmentResultListener("requestKeyTwo") { requestKey, bundle ->
            Log.d("Fragment Detail", "working till here")
            // We use a String here, but any type that can be put in a Bundle is supported
            val memberResult = bundle.get("bundleKeyTwo")
            Log.d("CLicked Member2", "$memberResult")
            //clickedMemberHetekaId = result
        }
            val view = binding.root
        return view

    }


}