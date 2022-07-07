package com.example.userdemo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdemo.data.repository.Repository
import com.example.userdemo.network.User
import com.example.userdemo.network.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(listOf())
    val users = _users.asStateFlow()
    private val _user = MutableStateFlow(
        UserDetail(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            0,
            0,
            "",
            "",
            true,
            "",
            "",
            "",
            "",
            "",
            ""
        )
    )
    val user = _user.asStateFlow()

    fun getUserList() {
        viewModelScope.launch {
            repository.getUsers(0, 20)
                .catch { e -> Log.e("Demo", "$e") }
                .collect { users ->
                    _users.update { users }
                }
        }
    }

    fun getUser(username: String?) {
        repository.getUser(username ?: "")
            .onEach { user -> _user.update { user } }
            .catch { e -> Log.e("Demo", "$e") }
            .launchIn(viewModelScope)
    }

    fun onUserClicked(user: User) {
        getUser(user.login)
    }
}
