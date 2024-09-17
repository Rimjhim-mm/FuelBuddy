package com.example.fuelfrenzy;

import android.content.Context;
import android.widget.Toast;

import com.example.fuelfrenzy.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    private final Context context;
    private final Retrofit retrofit;

    public RequestManager(Context context) {
        this.context = context;
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getNewsHeadlines(OnFetchDataListener listener, String category, String query) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call = callNewsApi.callHeadlines("in", query, context.getString(R.string.news_api));

        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                listener.onFetchData(response.body().getArticles(), response.message());
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
                listener.onError("Request Failed: " + throwable.getMessage());
            }
        });
    }

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("q") String query,
                @Query("apiKey") String apiKey
        );
    }
}
