package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.database.ParliamentMembersLike
import com.example.membersofparliamentproject.databinding.FragmentLikeBinding
import com.example.membersofparliamentproject.viewModels.FragmentLikeViewModel
import com.example.membersofparliamentproject.viewModels.FragmentLikeViewModelFactory

/**
 * This fragment is for submitting like or dislike to parliament Members
 */
class FragmentLike : Fragment() {

    private val args by navArgs<FragmentLikeArgs>()
    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentLikeViewModel
    private var changeLike: Boolean? = null


    /**
     * This function is called as soon as fragment has started and it inflates it's view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLikeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }


    /**
     * This function is called immediately after onCreateView.
     * IT initialises viewModel and also an observer
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            FragmentLikeViewModelFactory(requireActivity().application)
        )[FragmentLikeViewModel::class.java]
        val clickedMemberHetekaId = args.hetekaId
        viewModel.getAllLike()

        val likeObserver = Observer<List<ParliamentMembersLike>> {like ->
            for( i in like) {
                if ( i.hetekaId == clickedMemberHetekaId) {
                    if(i.like) {
                        binding.likeView.setBackgroundResource(R.drawable.like)
                    }else {
                        binding.likeView.setBackgroundResource(R.drawable.dislike)
                    }
                }
            }

        }
        //Starting observer
        viewModel.like.observe(viewLifecycleOwner,likeObserver)

        //Adding click listener for like Button
        binding.likeBtn.setOnClickListener {
            viewModel.addLike(ParliamentMembersLike(true,clickedMemberHetekaId))
            binding.likeView.setBackgroundResource(R.drawable.like)
            Toast.makeText(context,"Liked!",Toast.LENGTH_SHORT).show()
        }

        //Adding click listener for dislike button.
        binding.disLikeBtn.setOnClickListener {
            viewModel.addLike(ParliamentMembersLike(false,clickedMemberHetekaId))
            binding.likeView.setBackgroundResource(R.drawable.dislike)
            Toast.makeText(context,"Disliked!",Toast.LENGTH_SHORT).show()
        }

    }


}