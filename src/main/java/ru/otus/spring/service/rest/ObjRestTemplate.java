package ru.otus.spring.service.rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ObjRestTemplate<T> extends RestTemplate {

    final Class<T> typeParameterClass;

    public ObjRestTemplate(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public String saveObj(T obj, String url) {

        HttpEntity<T> request = new HttpEntity<>(obj);

        ResponseEntity<String> resp = this.exchange(url, HttpMethod.POST, request, String.class);

        return resp.getHeaders().getFirst("Location");
    }

}
