package com.example.userdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdemo.network.User
import com.example.userdemo.network.UsersApi
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            try {
                _users.value = UsersApi.retrofitService.getUsers(0,20)
            } catch (e: Exception) {
                _users.value = listOf()
            }
        }
    }

    fun onUserClicked(user: User) {
        _user.value = user
    }
}