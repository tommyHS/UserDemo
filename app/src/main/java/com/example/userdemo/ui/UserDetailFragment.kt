package com.example.userdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.userdemo.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private val viewModel: UserViewModel by viewModels()
    private val args by navArgs<UserDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentUserDetailBinding.inflate(inflater)
        //binding.userDetailFrgment = this@UserDetailFragment
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.user = args.user
        binding.ibtClose.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}
