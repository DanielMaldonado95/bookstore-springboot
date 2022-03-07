package com.apirest.bookshop.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import com.apirest.bookshop.models.saleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface saleRepository extends JpaRepository<saleModel, Long> {
    public ArrayList<saleModel> searchAllBySaleDateBetween(LocalDate from, LocalDate to);

}
