package co.id.kadaluarsa.core.domain.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<User>
){
    var nextPage: Int? = null
}

@Entity(primaryKeys = ["login"])
data class User(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("company")
    val company: String?,
    @field:SerializedName("repos_url")
    val reposUrl: String?,
    @field:SerializedName("blog")
    val blog: String?,
    val bio: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val html_url: String?,
    val id: Int?,
    val location: String?,
    val public_gists: Int?,
    val public_repos: Int?,
    val twitter_username: String?
)