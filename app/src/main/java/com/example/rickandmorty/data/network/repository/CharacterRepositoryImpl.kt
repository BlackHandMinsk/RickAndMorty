package com.example.rickandmorty.data.network.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.R
import com.example.rickandmorty.data.mappers.MapCharacterPojoToCharacterInfo
import com.example.rickandmorty.data.network.RickAndMortyApiService
import com.example.rickandmorty.data.paging.SearchPagingSource
import com.example.rickandmorty.domain.model.CharacterInfo
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_SIZE = 40

class CharacterRepositoryImpl(
    private val service: RickAndMortyApiService,
    private val mapCharacterPojoToCharacterInfo: MapCharacterPojoToCharacterInfo,
    private val context: Context
) : CharacterRepository {

    override fun getListCharacters(): Flow<PagingData<CharacterInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = STARTING_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    service = service
                )
            }
        ).flow.map { pagingData ->
            pagingData.map {
                mapCharacterPojoToCharacterInfo.map(it)
            }
        }
    }

    override fun getCharacter(id: String): Flow<Resource<CharacterInfo>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getCharacter(id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Resource.Success(data = mapCharacterPojoToCharacterInfo.map(body)))
                }
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(context.getString(R.string.message_http_error))
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(context.getString(R.string.message_internet_connecton_error))
            )
        }
    }
}