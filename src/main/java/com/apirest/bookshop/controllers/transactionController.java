package com.apirest.bookshop.controllers;

import java.util.Map;

import com.apirest.bookshop.services.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/books")
public class transactionController {

    @Autowired
    transactionService service;

    @GetMapping("/{bookId}")
    public Map<String, Object> getTransactions(
        @PathVariable Long bookId, 
        @RequestParam String from,
        @RequestParam String to
        ) {
        Map<String, Object> response = this.service.getTransactions(bookId, from, to);

        return response;
    }

}
