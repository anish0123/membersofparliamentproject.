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

//Introducing interface to get the data
interface ParliamentMemberExtraApiService {
    @GET("extras.json")
    suspend fun getParliamentMembersExtra() : List<ParliamentMembersExtra>
}

//starting the connection to get the data
object ParliamentMemberExtraApi {
    val retrofitService: ParliamentMemberExtraApiService by lazy {
        retrofit.create(ParliamentMemberExtraApiService::class.java)
    }
}
