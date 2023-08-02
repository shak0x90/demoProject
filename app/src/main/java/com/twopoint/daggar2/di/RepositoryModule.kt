package com.twopoint.daggar2.di

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun getUserRepsatory(userRepoSql: UserRepoSql):UserRepo
}