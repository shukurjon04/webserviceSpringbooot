package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.Postservice;
import com.example.demo.model.Post;



@RestController
@RequestMapping("/api")
public class PostController {

    private final Postservice postservice;

    public PostController(Postservice postservice) {
        this.postservice = postservice;
    }


    @PostMapping("/posts")
    public  ResponseEntity create(@RequestBody Post post){
        Post result  = postservice.save(post);
        return ResponseEntity.ok(result);
    }
    

    @GetMapping("/posts")
    public ResponseEntity getAll() {
        Object result = postservice.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/posts/params")
    public ResponseEntity getAllByParam(@RequestParam Long postid){
        List<Post> result =  postservice.findQueryParam(postid);
        return ResponseEntity.ok(result);
    }
    
}
