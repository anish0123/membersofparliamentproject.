package com.example.membersofparliamentproject.network

import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.database.ParliamentMembersExtra
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.create
import retrofit2.http.GET

/**
 * Object used to get the JSON data from the URI
 */
private const val BASE_URL =
    "https://users.metropolia.fi/~peterh/"

//Introducing moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Introducing retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//Introducing interface for getting JSON data
interface ParliamentMemberApiService {
    @GET("seating.json")
    suspend fun getParliamentMembersList(): List<ParliamentMembers>

    @GET("extras.json")
    suspend fun getParliamentMembersExtra(): List<ParliamentMembersExtra>
}

//Starting the connection to get the data
object ParliamentMemberApi {
    val retrofitService: ParliamentMemberApiService by lazy {
        retrofit.create(ParliamentMemberApiService::class.java)
    }
}
        