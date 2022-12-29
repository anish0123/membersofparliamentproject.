package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.databinding.FragmentStartBinding
import com.example.membersofparliamentproject.viewModels.FragmentStartViewModel
import com.example.membersofparliamentproject.viewModels.FragmentStartViewModelFactory

/**
 * This is a start fragment (showing intro and fetch data network)
 *
 */
class FragmentStart : Fragment() {
    private lateinit var viewModel: FragmentStartViewModel
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    /**
     * It starts the lifecycle of fragmentStart.
     * It imports the data from network and save it into the database
     * @param inflater and container
     * @return view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialise FragmentStartViewModel
        viewModel = ViewModelProvider(
            this,
            FragmentStartViewModelFactory(requireActivity().application)
        )[FragmentStartViewModel::class.java]
        //Get the data from the URI and save it into database
        viewModel.getMembers()
        viewModel.saveDataToDatabase()
        // Get the extra details from the URI and save it into database.
        viewModel.getExtras()
        viewModel.saveExtraToDatabase()
        //Using start button to move to fragement where it displays party List
        binding.startBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentStart3_to_fragmentParties2)
            //Toast added for start Button
            Toast.makeText(context, "Let's Start", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}

