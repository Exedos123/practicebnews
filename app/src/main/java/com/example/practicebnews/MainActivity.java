package com.example.practicebnews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public TextView textMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMy = findViewById(R.id.textView1);

        Retrofit baseurl = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        News endpoint = baseurl.create(News.class);

        Call<Topnews> calling = endpoint.getMyNews();
        calling.enqueue(new Callback<Topnews>() {
            @Override
            public void onResponse(Call<Topnews> call, Response<Topnews> response) {
                Topnews card = response.body();
                String matter = "";
                matter += "TotalResults :" + card.getTotalResults() + "\n";
                textMy.append(matter);
                List<Article> cardone = response.body().getArticles();
                for (Article i : cardone) {
                    String matterone = "";

                    matterone += "\n \n";
                    matterone += "Author :" + i.getAuthor() + "\n";
                    matterone += "Url :" + i.getUrl() + "\n";
                    matterone += "Title :" + i.getTitle() + "\n";
                    matterone += "Content :" + i.getContent() + "\n";
                    textMy.append(matterone);


                }


            }

            @Override
            public void onFailure(Call<Topnews> call, Throwable t) {
                textMy.setText("Failed" + t.getMessage());


            }
        });


    }
}
