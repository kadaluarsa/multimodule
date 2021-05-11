package co.id.kadaluarsa.gigithubuser

import org.junit.Before
import org.junit.Test

class UseCaseTest {
    private lateinit var useCase : GetUserUseCase
    private val mockRepo = mock(UserRepositoryImp::class.java)
    private val userList = listOf(User(1,"Dony","Nuransyah"))


    @Before
    fun setUp(){
        userCase = GetUserUseCase(mockRepo)
    }

    @Test
    fun `test Get Users returns success`(){

    }
}