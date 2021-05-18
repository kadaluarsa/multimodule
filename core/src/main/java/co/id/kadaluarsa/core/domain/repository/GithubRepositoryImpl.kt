package co.id.kadaluarsa.core.domain.repository

import androidx.lifecycle.LiveData
import co.id.kadaluarsa.core.AppExecutors
import co.id.kadaluarsa.core.data.db.GithubDb
import co.id.kadaluarsa.core.data.network.ApiResponse
import co.id.kadaluarsa.core.data.network.GithubService
import co.id.kadaluarsa.core.domain.model.Resource
import co.id.kadaluarsa.core.domain.model.User
import co.id.kadaluarsa.core.domain.model.Users
import co.id.kadaluarsa.core.data.db.UserDao
import co.id.kadaluarsa.core.data.network.ApiSuccessResponse
import co.id.kadaluarsa.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
@Singleton
class GithubRepositoryImpl @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: GithubDb,
    private val userDao: UserDao,
    private val githubUserService: GithubService
) : GithubRepository {

    override fun searchUser(_perPage: Int, username: String): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, Users>(appExecutors) {
            override fun saveCallResult(item: Users) {
                userDao.insertUsers(item.items)
            }

            override fun shouldFetch(data: List<User>?): Boolean = data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<User>> = userDao.getUsers()

            override fun createCall(): LiveData<ApiResponse<Users>> {
                return githubUserService.searchUser(query = username, perPage = _perPage, page = 1)
            }

            override fun processResponse(response: ApiSuccessResponse<Users>): Users {
                val body = response.body
                body.nextPage = response.nextPage
                return body
            }
        }.asLiveData()
    }

    override fun viewUserDetail(username: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insertUser(item)
            }

            override fun shouldFetch(data: User?): Boolean = data == null

            override fun loadFromDb(): LiveData<User> = userDao.findByUsername(username = username)

            override fun createCall(): LiveData<ApiResponse<User>> =
                githubUserService.getUser(login = username)

        }.asLiveData()
    }
}