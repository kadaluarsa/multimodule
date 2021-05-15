package co.id.kadaluarsa.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.id.kadaluarsa.core.domain.model.Resource
import co.id.kadaluarsa.core.domain.repository.GithubRepository
import co.id.kadaluarsa.core.utils.AbsentLiveData
import androidx.lifecycle.switchMap
import co.id.kadaluarsa.core.domain.model.User
import co.id.kadaluarsa.testing.OpenForTesting
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@OpenForTesting
@HiltViewModel
class SearchViewModel @Inject constructor(private var githubRepository: GithubRepository) : ViewModel(){
    private val _selectedUser = MutableLiveData<String>()
    val selectedUser: LiveData<String>
        get() = _selectedUser

    val listuser: LiveData<Resource<List<User>>> = _selectedUser.switchMap { username ->
        if (username.isBlank()) {
            AbsentLiveData.create()
        } else {
            githubRepository.searchUser(page = 1, username = username)
        }
    }

    val detailuser: LiveData<Resource<User>> = _selectedUser.switchMap { username ->
        if (username.isBlank()) {
            AbsentLiveData.create()
        } else {
            githubRepository.viewUserDetail(username = username)
        }
    }

    fun setUsername(login: String) {
        if (_selectedUser.value != login) {
            _selectedUser.value = login
        }
    }
}