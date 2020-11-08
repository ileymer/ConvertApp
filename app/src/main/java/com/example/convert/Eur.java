package com.example.convert;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Eur
{
    @GET("/latest?base=EUR")
    Call<String> eur();

}