package ru.startandroid.testgit.di.module

import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.startandroid.testgit.BuildConfig
import ru.startandroid.testgit.di.skope.ApiScope
import ru.startandroid.testgit.usecases.repository.remote_data_source.RepositoryRemoteDataSource
import ru.startandroid.testgit.usecases.repository.remote_data_source.RepositoryRemoteDataSourceImpl
import ru.startandroid.testgit.usecases.repository.remote_data_source.communicator.ApiService
import ru.startandroid.testgit.usecases.repository.remote_data_source.communicator.ServerCommunicator
import java.util.concurrent.TimeUnit

@Module// аннотируются классы в которых реализованы зависимости
class ApiModule {

    companion object { //объявление вспомогательного объекта
        private val API_URL = "https://api.github.com/" //создание приватного поля
    }

    @Provides //ставится на каждом методе который что-то создаёт
    @ApiScope //область видимости по ApiScope
    fun provideOkHttpClient(): OkHttpClient { //наследуем provideOkHttpClient от OkHttpClient
        var httpClient= OkHttpClient.Builder()//создание нового екзеспляра с пользовательскими настройками
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            //максум холостых подключений=5
            //время жизни=30
            //еденица измерения=секунды
            .connectTimeout(30, TimeUnit.SECONDS)
            //время подключения в секундах
            .readTimeout(30, TimeUnit.SECONDS)
            //время чтения в секундах
            .writeTimeout(30, TimeUnit.SECONDS)
        //время записи в секундах
        if (BuildConfig.DEBUG) {//если
            httpClient=httpClient//операция прошла успешно??
                .addInterceptor( //добавляем слушатель(перехватчик)
                    HttpLoggingInterceptor().apply//с перехватчиком(слушателем) внутри и применяем HttpLoggingInterceptor.Level
                    {
                        level= HttpLoggingInterceptor.Level.BODY//регистрирует строки запроса, ответа их заголовки и тела
                    }
                )
        }

        return httpClient.build()//возвращаем новосозданный екземпляр
    }

    @Provides//ставится на каждом методе который что-то создаёт
    @ApiScope//область видимости по ApiScope
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {//наследуем provideRetrofitBuilder от Retrofit.Builder
        // в конструктор помещаем client наследуемый от OkHttpClient
        return Retrofit.Builder()//возвращаем Retrofit.Builder
            .client(client)//подключаемся к OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())//добавляем конвертер паралельно создавая его
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//подключаем к цепочке rx
    }

    @Provides//ставится на каждом методе который что-то создаёт
    @ApiScope//область видимости по ApiScope
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {//наследуем provideRetrofit от Retrofit
        //в конструктор помещаем builder наследуемый от Retrofit.Builder
        return builder.baseUrl(API_URL).build()//возвращаем созданный в конструкторе builder с API_URL
    }

    @Provides//ставится на каждом методе который что-то создаёт
    @ApiScope//область видимости по ApiScope
    fun provideApiService(retrofit: Retrofit): ApiService {//наследуем provideApiService от ApiService
        //в конструктор помещаем retrofit наследуемый от Retrofit
        return retrofit.create<ApiService>(//возвращаем созданный в конструкторе retrofit и создаём ApiService
            ApiService::class.java)
    }

    @Provides//ставится на каждом методе который что-то создаёт
    @ApiScope//область видимости по ApiScope
    fun provideCommunicator(apiService: ApiService): ServerCommunicator {//наследуем provideCommunicator от ServerCommunicator
        //в конструктор помещаем apiService наследуемый от ApiService
        return ServerCommunicator(//возвращаем ServerCommunicator с apiService внутри конструктора
            apiService
        )
    }

    @Provides//ставится на каждом методе который что-то создаёт
    @ApiScope//область видимости по ApiScope
    fun providesFeedRemoteDataSource(serverCommunicator: ServerCommunicator): RepositoryRemoteDataSource {
        //наследуем providesFeedRemoteDataSource от RepositoryRemoteDataSource
        //в конструктор помещаем serverCommunicator наследуемый от ServerCommunicator
        return RepositoryRemoteDataSourceImpl(serverCommunicator)
        //возвращаем RepositoryRemoteDataSourceImpl с serverCommunicator в конструкторе
    }
}