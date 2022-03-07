package com.apirest.bookshop.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.apirest.bookshop.models.saleModel;
import com.apirest.bookshop.repositories.saleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transactionService {
    @Autowired
    saleRepository saleRepository;

    public Map<String, Object> getTransactions(Long bookId, String from, String to){
        Map<String, Object> response = new HashMap<String, Object>();
        float totalRevenue = 0;
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> customers = new ArrayList<String>();
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        dates.add(from);
        dates.add(to);

        ArrayList<saleModel> sales = this.saleRepository.searchAllBySaleDateBetween(fromDate, toDate);
       
        for (saleModel sale : sales) {
            customers.add(sale.getCustomerEmail());
            totalRevenue = sale.getSalePrice() + totalRevenue;
        }

        response.put("bookId", bookId);
        response.put("sales", dates);
        response.put("totalRevenue", totalRevenue);
        response.put("customers", customers);

        return response;
    }
}
