package com.twopoint.daggar2.di

import com.twopoint.daggar2.MainActivity
import dagger.Component

@Component(modules = [RepositoryModule::class,NotficationProviders::class])
interface UserRegistationComp {
//    fun userRegServices(): UserRegServices
    fun injectchoda(mainActivity: MainActivity)

}