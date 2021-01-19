package com.example.movieroom

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//the movie database(tmdb) api base url
private const val BASE_URL = "https://api.themoviedb.org/3"

//the movie database(tmdb) api key
private const val API_KEY = BuildConfig.API_KEY

//tmdb genre ids
val GENRES = mapOf<Int, String>(28 to "Action", 12 to "Adventure", 16 to "Animation",
        35 to "Comedy", 80 to "Crime", 18 to "Drama",
        10751 to "Family", 14 to "Fantasy", 27 to "Horror",
        9648 to "Mystery", 10749 to "Romance", 878 to "Science Fiction",
        53 to "Thriller")

//Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for full Kotlin compatibility.
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

//Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi object above.
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

//A public interface that exposes all our data fetching methods
interface MovieApiService{
    /**
     * all functions returns a Coroutine [List] of [Movie] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates the endpoint that will be requested with the GET HTTP method
     */

    //get trending movies of the day
    @GET("/trending/movie/day?api_key=$API_KEY")
    suspend fun getTrendingMovies() : List<Movie>

    //get top action movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=28&sort_by=vote_average.desc")
    suspend fun getActionMovies() : List<Movie>

    //get top adventure movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=12&sort_by=vote_average.desc")
    suspend fun getAdventureMovies() : List<Movie>

    //get top animation movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=16&sort_by=vote_average.desc")
    suspend fun getAnimationMovies() : List<Movie>

    //get top comedy movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=35&sort_by=vote_average.desc")
    suspend fun getComedyMovies() : List<Movie>

    //get top crime movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=80&sort_by=vote_average.desc")
    suspend fun getCrimeMovies() : List<Movie>

    //get top drama movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=18&sort_by=vote_average.desc")
    suspend fun getDramaMovies() : List<Movie>

    //get top family movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=10751&sort_by=vote_average.desc")
    suspend fun getFamilyMovies() : List<Movie>

    //get top fantasy movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=14&sort_by=vote_average.desc")
    suspend fun getFantasyMovies() : List<Movie>

    //get top horror movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=27&sort_by=vote_average.desc")
    suspend fun getHorrorMovies() : List<Movie>

    //get top mystery movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=9648&sort_by=vote_average.desc")
    suspend fun getMysteryMovies() : List<Movie>

    //get top romance movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=10749&sort_by=vote_average.desc")
    suspend fun getRomanceMovies() : List<Movie>

    //get top science fiction movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=878&sort_by=vote_average.desc")
    suspend fun getScienceFictionMovies() : List<Movie>

    //get top thriller movies
    @GET("/discover/movie?api_key=${API_KEY}&with_genres=53&sort_by=vote_average.desc")
    suspend fun getThrillerMovies() : List<Movie>

}

//A public Api object that exposes the lazy-initialized Retrofit service
object MovieApi {
    val retrofitService : MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}