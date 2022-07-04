package com.example.userdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.userdemo.R
import com.example.userdemo.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserListBinding.inflate(inflater)
        // TODO: call the view model method that calls the amphibians api
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = UserListAdapter(UserListener { user ->
            viewModel.onUserClicked(user)
            findNavController()
                .navigate(R.id.action_userListFragment_to_userDetailFragment)
        })

        return binding.root
    }
}