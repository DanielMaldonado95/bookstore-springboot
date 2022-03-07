package com.apirest.bookshop.controllers;

import java.util.Map;

import com.apirest.bookshop.models.likeModel;
import com.apirest.bookshop.services.likeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class likeController {
    
    @Autowired
    likeService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Ok")
    public Map<String, Object> postLike(@RequestBody likeModel like) throws Exception{
        return this.service.postLike(like);
    }

}
