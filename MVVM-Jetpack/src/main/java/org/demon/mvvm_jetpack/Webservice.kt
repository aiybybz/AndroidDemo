package org.demon.mvvm_jetpack

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {
    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<User>

}