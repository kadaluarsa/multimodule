/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.id.kadaluarsa.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import co.id.kadaluarsa.core.AppExecutors
import co.id.kadaluarsa.core.data.db.GithubDb
import co.id.kadaluarsa.core.data.db.RepoDao
import co.id.kadaluarsa.core.data.db.UserDao
import co.id.kadaluarsa.core.data.network.GithubService
import co.id.kadaluarsa.core.domain.repository.GithubRepository
import co.id.kadaluarsa.core.domain.repository.GithubRepositoryImpl
import co.id.kadaluarsa.core.utils.LiveDataCallAdapterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGithubService(client: OkHttpClient): GithubService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build()
            .create(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain ->
                var request = chain.request()
                /* If there is Internet, get the cache that was stored 5 seconds ago.
                 * If the cache is older than 5 seconds, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-age' attribute is responsible for this behavior.
                 */
                request =
                    request.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 5).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideDb(app: Application): GithubDb {
        return Room
            .databaseBuilder(app, GithubDb::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: GithubDb): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideRepoDao(db: GithubDb): RepoDao {
        return db.repoDao()
    }

    @Provides
    @Singleton
    fun provideGithubRepository(
        appExecutors: AppExecutors,
        db: GithubDb,
        userDao: UserDao,
        githubService: GithubService
    ): GithubRepository {
        return GithubRepositoryImpl(
            appExecutors, db, userDao, githubService
        )
    }
}
