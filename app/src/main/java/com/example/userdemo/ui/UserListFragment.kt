package com.example.userdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.userdemo.R
import com.example.userdemo.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserListBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = userViewModel
            recyclerView.adapter = UserListAdapter(UserListener { user ->
                userViewModel.onUserClicked(user)
                findNavController()
                    .navigate(
                        R.id.action_userListFragment_to_userDetailFragment,
                        UserDetailFragmentArgs(user).toBundle()
                    )
            })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.getUserList()
    }
}