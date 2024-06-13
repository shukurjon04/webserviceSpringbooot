package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Postdata;
import com.example.demo.Repository.Postrepository;
import com.example.demo.model.Post;

@Service
public class PostdataService {

    private final Postrepository postrepository;

    public PostdataService(Postrepository postrepository) {
        this.postrepository = postrepository;
    }

    public Postdata save(Postdata p){
        return postrepository.save(p);
    }

    public List<Postdata> saveall(Post[] post){
        List<Postdata> list= new ArrayList<>();
        for (Post post1 : post) {
            Postdata postdata1 = new Postdata();
            postdata1.setPostId(post1.getId());
            postdata1.setTitle(post1.getTitle());
            postdata1.setUserId(post1.getUserId());
            postdata1.setBody(post1.getBody());
           list.add(postdata1);
        }

        return postrepository.saveAll(list);
    }
}

