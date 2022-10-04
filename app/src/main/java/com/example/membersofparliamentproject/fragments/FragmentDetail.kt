package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.databinding.FragmentDetailBinding
import com.example.membersofparliamentproject.viewModels.FragmentDetailViewModel
import com.example.membersofparliamentproject.viewModels.FragmentDetailViewModelFactory
import com.example.membersofparliamentproject.viewModels.FragmentPartiesViewModel
import kotlinx.android.synthetic.main.fragment_detail.view.*
import java.time.LocalDate
import kotlin.properties.Delegates

/**
 * This fragment is used to display the details of parliament members
 */

class FragmentDetail : Fragment() {
    private val args by navArgs<FragmentDetailArgs>()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        //Displaying member details in textViews
        binding.party.text = getString(R.string.partyDisplay, args.clickedMember.party)
        binding.name.text =
            getString(R.string.name, args.clickedMember.firstname, args.clickedMember.lastname)
        binding.hetekaId.text = getString(R.string.HetekaId, args.clickedMember.hetekaId)
        binding.seatNumber.text = getString(R.string.seatNumber, args.clickedMember.seatNumber)
        binding.minister.text = getString(R.string.minister, args.clickedMember.minister)
        val url = getString(R.string.url, args.clickedMember.pictureUrl)
        //Introducing glide for getting members pictures for imageView
        Glide.with(this).load(url).into(binding.mpImage)

        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Initialising ViewModel
        viewModel = ViewModelProvider(
            this,
            FragmentDetailViewModelFactory(requireActivity().application)
        )[FragmentDetailViewModel::class.java]
        val memberHetekaId = args.clickedMember.hetekaId
        viewModel.getExtraInfo(memberHetekaId)

        val extraInfo = viewModel.extraInfo.value

        if (extraInfo != null) {
            binding.seatNumber.text = extraInfo.bornYear.toString()
            Log.d("ExtraInfoTest", "working till here ")
        }


    }


}