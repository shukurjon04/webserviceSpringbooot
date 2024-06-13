package com.example.demo.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Post;

@Service
public class Postservice {

    private final RestTemplate restTemplate;

    private final PostdataService postdataService;


    @Value("${api.jsonplaceholder}")
    private String api;

    public Postservice(PostdataService postdataService, RestTemplate restTemplate) {
        this.postdataService = postdataService;
        this.restTemplate = restTemplate;
    }

    public Post save(Post post){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity  = new HttpEntity<>(post,headers);
        Post result = restTemplate.postForObject(this.api+"/posts", entity, Post.class);
        return result;
    }


    public Post update(Long id,Post post){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity  = new HttpEntity<>(post,headers);
        Post result = restTemplate.exchange(this.api+"/posts/"+id+"/comments", HttpMethod.PUT,entity, Post.class).getBody();
        return result;
    }


    
    public Object findAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post[]> entity  = new HttpEntity<>(headers);
        Post[] result  =  restTemplate.exchange(this.api+"/posts",HttpMethod.GET,entity, Post[].class).getBody();
        postdataService.saveall(result);
        return result;
    }

    public List<Post> findQueryParam(Long postid){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String urltemplate = UriComponentsBuilder.fromHttpUrl("")
        .queryParam("postid","{postid}")
        .encode().toUriString();

        Map<String,Object> maps = new HashMap<>();
        maps.put("postId", postid); 
        HttpEntity<List<Post>> entity  = new HttpEntity<>(headers);
        List<Post> result  =  restTemplate.exchange(urltemplate,HttpMethod.GET,entity, List.class,maps).getBody();
        return result;

    }
}
