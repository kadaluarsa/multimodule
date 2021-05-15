package co.id.kadaluarsa.core.domain.repository

import androidx.lifecycle.LiveData
import co.id.kadaluarsa.core.domain.model.Resource
import co.id.kadaluarsa.core.domain.model.User

interface GithubRepository {
    fun searchUser(page: Int, username: String): LiveData<Resource<List<User>>>
    fun viewUserDetail(username : String) : LiveData<Resource<User>>
}