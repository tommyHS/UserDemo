package com.example.userdemo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdemo.network.User
import com.example.userdemo.network.UserDetail
import com.example.userdemo.network.UsersApi
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    private val _user = MutableLiveData<UserDetail>()
    val user: LiveData<UserDetail> = _user

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

    private fun getUser(username : String?) {
        viewModelScope.launch {
            try {
                _user.value = UsersApi.retrofitService.getUser(username)
            } catch (e: Exception) {
                _user.value = null
                Log.d("TAG_E",e.toString())
            }
        }
    }

    fun onUserClicked(user: User) {
        getUser(user.login)
    }
}