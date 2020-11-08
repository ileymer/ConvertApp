package com.example.convert;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Rub
{
    @GET("/latest?base=RUB")
    Call<String> rub();

}
