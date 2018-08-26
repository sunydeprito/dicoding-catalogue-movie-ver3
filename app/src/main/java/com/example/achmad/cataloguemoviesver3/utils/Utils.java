package com.example.achmad.cataloguemoviesver3.utils;

import com.example.achmad.cataloguemoviesver3.BuildConfig;

/**
 * Created by Achmad on 23-08-2018
 **/

public class Utils {
    public final static String DATE_FORMAT = "dd MMMM yyyy";
    public final static String DATE_FORMAT_DAY = "EEEE, MMM d, yyyy";
    public final static String BASE_URL = "http://api.themoviedb.org/3/";
    public final static String API_KEY = BuildConfig.ApiKey;
    public final static String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185";
    public final static String BASE_BACKDROP_URL = "http://image.tmdb.org/t/p/w780";
    public final static String MOVIE_DETAIL = "movie_detail";
    public static final String INTENT_SEARCH = "intent_search";
    public static final String INTENT_TAG = "tag";
    public static final String INTENT_DETAIL = "detail";
}
