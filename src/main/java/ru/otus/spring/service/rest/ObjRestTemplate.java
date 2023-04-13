package ru.otus.spring.service.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class ObjRestTemplate<T> extends RestTemplate {

    public String saveObj(T obj, String url) {

        HttpEntity<T> request = new HttpEntity<>(obj);

        ResponseEntity<String> resp = this.exchange(url, HttpMethod.POST, request, String.class);

        return resp.getHeaders().getFirst("Location");
    }

}
