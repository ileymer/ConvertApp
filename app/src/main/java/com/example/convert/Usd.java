package com.example.convert;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Usd
{
    @GET("/latest?base=USD")
    Call<String> usd();

}
