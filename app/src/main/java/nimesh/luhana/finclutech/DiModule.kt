package nimesh.luhana.finclutech

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nimesh.luhana.finclutech.data.repository.DataRepository
import nimesh.luhana.finclutech.data.repository.InMemoryRequestRepository
import nimesh.luhana.finclutech.data.repository.MockDataRepository
import nimesh.luhana.finclutech.data.repository.RequestRepository
import nimesh.luhana.finclutech.utils.JsonParser
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {


    @Provides
    @Singleton
    fun provideDataRepository(
        @ApplicationContext context: Context
    ): DataRepository {
        return MockDataRepository(JsonParser(context))
    }


    @Provides
    @Singleton
    fun provideRequestRepository(): RequestRepository {
        return InMemoryRequestRepository()
    }


    @Provides
    fun provideJsonParser(@ApplicationContext context: Context): JsonParser {
        return JsonParser(context)
    }
}