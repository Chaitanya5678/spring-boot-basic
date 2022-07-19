package com.askchaitanya.blackops;

import com.askchaitanya.blackops.configurations.HttpClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BlackOpsApplicationTests {

	OkHttpClient client = HttpClient.getClient();

	@Test
    void fetchDogs() {
        Request getRequest = new Request.Builder()
                .url("https://dog.ceo/api/breeds/image/random")
                .build();
        try {
            Response response = client.newCall(getRequest).execute();
			System.out.println(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
