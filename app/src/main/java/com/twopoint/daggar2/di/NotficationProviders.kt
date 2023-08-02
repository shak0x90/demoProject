package com.twopoint.daggar2.di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NotficationProviders(private val noticount:Int)  {
    @messege
    @Provides
    fun getNotificationProviderEmail():NotificaionSercies{
        return MagaageService(noticount)
    }
    @Named("email")
    @Provides
    fun getNotificationProviderMessege(userEmailServices:UserEmailServices):NotificaionSercies{
        return userEmailServices
    }
}