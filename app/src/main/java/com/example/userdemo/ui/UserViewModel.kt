package com.example.userdemo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdemo.data.repository.Repository
import com.example.userdemo.network.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(listOf())
    val users = _users.asStateFlow()
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun getUserList() {
        viewModelScope.launch {
            repository.getUsers(0, 20)
                .catch { e -> Log.e("Demo", "$e") }
                .collect { users ->
                    _users.update { users }
                }
        }
    }

    fun onUserClicked(user: User) {
        _user.value = user
    }
}