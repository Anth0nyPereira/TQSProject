package ua.deti.tqs.uv_tqs_project.service;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.deti.tqs.uv_tqs_project.UVValue;
import ua.deti.tqs.uv_tqs_project.entity.UVCache;

@Service
public class UVService {
    private UVCache uvCache;

    public UVValue getUVValue(double latitude, double longitude) {
        String key = latitude + "_" + longitude;
        if (uvCache.containsKey(key)) {
            System.out.println("exists");
        } else {
            String uri = "https://api.openuv.io/api/v1/uv?lat=" + latitude + "&lng=" + longitude

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            System.out.println(result);

            Request request = new Request.Builder()
                    .url("https://api.openuv.io/api/v1/uv?lat=-33.34&lng=115.342&dt=2018-01-24T10%3A50%3A52.283Z")
                    .get()
                    .addHeader("x-access-token", "6f38291aabb8acfab87af3bcf2e19568")
                    .build();

            Response response = client.newCall(request).execute();
        }
    }

}
