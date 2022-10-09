package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.database.ParliamentMembersLikeAndComment
import com.example.membersofparliamentproject.databinding.FragmentCommentBinding
import com.example.membersofparliamentproject.databinding.FragmentDetailBinding
import com.example.membersofparliamentproject.viewModels.FragmentCommentViewModel
import com.example.membersofparliamentproject.viewModels.FragmentCommentViewModelFactory
import kotlin.properties.Delegates


class FragmentComment : Fragment() {
    private val args by navArgs<FragmentCommentArgs>()
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentCommentViewModel
    private var like: Boolean  = true
    private var savedComment: String = ""
    private var commentId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)


        binding.addCommentBtn.setOnClickListener {
            //binding.textViewComment.text = binding.addComment.text


        }
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            FragmentCommentViewModelFactory(requireActivity().application)
        )[FragmentCommentViewModel::class.java]
        viewModel.getAllComments()
        val clickedMemberHetekaId = args.hetekaId

        val commentObserver = Observer<List<ParliamentMembersLikeAndComment>> {comment ->
            for ( i in comment) {
                if (i.hetekaId == clickedMemberHetekaId) {
                    binding.textViewComment.text = getString(R.string.comment,i.comment)
                    binding.textViewLike.text = getString(R.string.likeOrDislike,i.like)
                    like = i.like
                    savedComment = i.comment
                    commentId = i.commentId
                    break
                }else {
                    binding.textViewComment.text = getString(R.string.comment,"")
                    binding.textViewLike.text = getString(R.string.likeOrDislike,i.like)
                }
            }

        }
        //Adding setOnClickListener for add comment button to add comments
        binding.addCommentBtn.setOnClickListener {
                savedComment = binding.addComment.text.toString()
                viewModel.addComment(
                    ParliamentMembersLikeAndComment(
                        commentId,
                        savedComment.toString(),
                        like,
                        clickedMemberHetekaId
                    )
                )
                binding.textViewComment.text = getString(R.string.comment, binding.addComment.text)
            }

        //Adding setOnClickListener for like button
        binding.like.setOnClickListener {

                viewModel.addComment(
                    ParliamentMembersLikeAndComment(
                        commentId,
                        savedComment,
                        true,
                        clickedMemberHetekaId
                    )
                )
            like = true

            binding.textViewLike.text = getString(R.string.likeOrDislike,like)
        }

        //Adding setOnClickListener for dislike button.
        binding.dislike.setOnClickListener {

            viewModel.addComment(
                ParliamentMembersLikeAndComment(
                    commentId,
                    savedComment,
                    false,
                    clickedMemberHetekaId
                )

            )
            like = false

            binding.textViewLike.text = getString(R.string.likeOrDislike,like)
        }
        //Starting observer for comment
        viewModel.comment.observe(viewLifecycleOwner,commentObserver)


    }


}