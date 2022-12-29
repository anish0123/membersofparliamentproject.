package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.membersofparliamentproject.database.ParliamentMembersLikeAndComment
import com.example.membersofparliamentproject.databinding.FragmentChangeCommentBinding
import com.example.membersofparliamentproject.viewModels.FragmentChangeCommentViewModel
import com.example.membersofparliamentproject.viewModels.FragmentChangeCommentViewModelFactory

/**
 * This fragment is used to delete and update comments
 * Source for safeArgs: https://medium.com/androiddevelopers/navigating-with-safeargs-bf26c17b1269
 */
class FragmentChangeComment : Fragment() {
    private val args by navArgs<FragmentChangeCommentArgs>()
    private var _binding: FragmentChangeCommentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentChangeCommentViewModel

    /**
     * This starts the fragment and inflates the view
     * @param inflater and container
     * @return view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangeCommentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    /**
     * This function starts immediately after onCreateView and initialise viewModel
     * @param view and savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialising ViewModel
        viewModel = ViewModelProvider(
            this,
            FragmentChangeCommentViewModelFactory(requireActivity().application)
        )[FragmentChangeCommentViewModel::class.java]
        val clickedComment = args.comment

        //Adding onclickListener for delete and update Button
        binding.deleteBtn.setOnClickListener {
            viewModel.deleteComment(clickedComment)
            //val action = FragmentChangeCommentDirections.actionFragmentChangeCommentToFragmentComment(clickedComment.hetekaId)
            //findNavController().navigate(action)


        }
        //Adding clickListener on update button and get the comment from editText
        binding.updateBtn.setOnClickListener {
            val newComment = binding.editTextUpdateComment.text.toString()
            if (newComment != "") {
                viewModel.deleteComment(clickedComment)
                viewModel.addComment(
                    ParliamentMembersLikeAndComment(
                        newComment,
                        clickedComment.hetekaId
                    )
                )
                //val action = FragmentChangeCommentDirections.actionFragmentChangeCommentToFragmentComment(clickedComment.hetekaId)
                //findNavController().navigate(action)
            } else {
                Toast.makeText(
                    context,
                    "Unable to submit empty comment. Please write comment",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }


}