package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Postdata;

@Repository
public interface Postrepository extends JpaRepository<Postdata, Long> {

}
