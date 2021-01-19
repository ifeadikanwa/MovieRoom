package com.example.movieroom

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//the movie database(tmdb) api base url
private const val BASE_URL = "https://api.themoviedb.org/3/"

//the movie database(tmdb) api key
private const val API_KEY = BuildConfig.API_KEY

//tmdb genre ids
val GENRES = mapOf<Int, String>(28 to "Action", 12 to "Adventure", 16 to "Animation",
        35 to "Comedy", 80 to "Crime", 18 to "Drama",
        10751 to "Family", 14 to "Fantasy", 27 to "Horror",
        9648 to "Mystery", 10749 to "Romance", 878 to "Science Fiction",
        53 to "Thriller")


//Use the Retrofit builder to build a retrofit object using a Gson converter.
private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

//A public interface that exposes all our data fetching methods
interface MovieApiService{
    /**
     * all functions returns a Coroutine [List] of [Movie] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates the endpoint that will be requested with the GET HTTP method
     */

    //get trending movies of the day
    @GET("trending/movie/day?api_key=$API_KEY")
    suspend fun getTrendingMovies() : Movie

    //get top action movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=28&sort_by=vote_average.desc")
    suspend fun getActionMovies() : Movie

    //get top adventure movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=12&sort_by=vote_average.desc")
    suspend fun getAdventureMovies() : Movie

    //get top animation movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=16&sort_by=vote_average.desc")
    suspend fun getAnimationMovies() : Movie

    //get top comedy movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=35&sort_by=vote_average.desc")
    suspend fun getComedyMovies() : Movie

    //get top crime movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=80&sort_by=vote_average.desc")
    suspend fun getCrimeMovies() : Movie

    //get top drama movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=18&sort_by=vote_average.desc")
    suspend fun getDramaMovies() : Movie

    //get top family movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=10751&sort_by=vote_average.desc")
    suspend fun getFamilyMovies() : Movie

    //get top fantasy movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=14&sort_by=vote_average.desc")
    suspend fun getFantasyMovies() : Movie

    //get top horror movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=27&sort_by=vote_average.desc")
    suspend fun getHorrorMovies() : Movie

    //get top mystery movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=9648&sort_by=vote_average.desc")
    suspend fun getMysteryMovies() : Movie

    //get top romance movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=10749&sort_by=vote_average.desc")
    suspend fun getRomanceMovies() : Movie

    //get top science fiction movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=878&sort_by=vote_average.desc")
    suspend fun getScienceFictionMovies() : Movie

    //get top thriller movies
    @GET("discover/movie?api_key=${API_KEY}&with_genres=53&sort_by=vote_average.desc")
    suspend fun getThrillerMovies() : Movie

}

//A public Api object that exposes the lazy-initialized Retrofit service
object MovieApi {
    val retrofitService : MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}