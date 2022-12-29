package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.membersofparliamentproject.adapters.CommentAdapter
import com.example.membersofparliamentproject.database.ParliamentMembersLikeAndComment
import com.example.membersofparliamentproject.databinding.FragmentCommentBinding
import com.example.membersofparliamentproject.viewModels.FragmentCommentViewModel
import com.example.membersofparliamentproject.viewModels.FragmentCommentViewModelFactory

/**
 * This fragment is used to display and add comments and like to the members.
 * Source for RecyclerView: https://developer.android.com/develop/ui/views/layout/recyclerview
 * Source for safeArgs: https://medium.com/androiddevelopers/navigating-with-safeargs-bf26c17b1269
 */
class FragmentComment : Fragment() {
    private val args by navArgs<FragmentCommentArgs>()
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentCommentViewModel
    private lateinit var adapter: CommentAdapter


    /**
     * This function starts the fragment and inflates the view
     * @param inflater and container
     * @return view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    /**
     * It starts immediately after onCreateView.
     * It initalise fragmentCommentViewModel and start the observer to get the like and comments about parliament members
     *  @param view and savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewComment.layoutManager = layoutManager
        binding.recyclerViewComment.setHasFixedSize(true)

        viewModel = ViewModelProvider(
            this,
            FragmentCommentViewModelFactory(requireActivity().application)
        )[FragmentCommentViewModel::class.java]

        //Getting the hetekaId from detail fragment and getting comments according to hetekaId
        val clickedMemberHetekaId = args.hetekaId
        viewModel.getCommentByHetekaId(clickedMemberHetekaId)

        //Initialising observer
        val commentObserver = Observer<List<ParliamentMembersLikeAndComment>> { comment ->
            adapter = CommentAdapter(comment)
            binding.recyclerViewComment.adapter = adapter
            adapter.setonItemClickListener(object : CommentAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val clickedComment = comment[position]
                    val action =
                        FragmentCommentDirections.actionFragmentCommentToFragmentChangeComment(
                            clickedComment
                        )
                    findNavController().navigate(action)
                    Toast.makeText(context, "Opening Comment Edit Page", Toast.LENGTH_SHORT).show()
                }
            })
        }
        //Starting observer for comment
        viewModel.commentByHetekaId.observe(viewLifecycleOwner, commentObserver)

        //adding clickListener for adding comment
        binding.addCommentBtn.setOnClickListener {
            val newComment = binding.editTextComment.text.toString()
            if (newComment != "") {
                viewModel.addComment(
                    ParliamentMembersLikeAndComment(
                        newComment,
                        clickedMemberHetekaId
                    )
                )
                //Updating the recyclerView.
                viewModel.getCommentByHetekaId(clickedMemberHetekaId)
                //viewModel.commentByHetekaId.observe(viewLifecycleOwner, commentObserver)
                binding.editTextComment.setText("")
                Toast.makeText(context, "Comment Submitted", Toast.LENGTH_SHORT).show()
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