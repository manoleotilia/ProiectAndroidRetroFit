package com.example.proiectandroidretrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/latest")
    Call<ExchangeRates> latest(@Query("access_key") String access_key, @Query("symbols") String  symbols);
    @Headers({"X-RapidAPI-Key:e37a1be58emshf41be34c3fe0092p1119cejsn2d246d03f3fe", "X-RapidAPI-Host:public-holiday.p.rapidapi.com"})
    @GET("/{year}/{countryCode}")
    Call<List<PublicHolidays>> getAllPublicHolidays(@Path(value = "year", encoded = false) String year, @Path(value = "countryCode", encoded = false) String countryCode);
}
