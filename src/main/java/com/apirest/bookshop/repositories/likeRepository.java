package com.apirest.bookshop.repositories;

import java.util.ArrayList;

import com.apirest.bookshop.models.likeModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface likeRepository extends JpaRepository<likeModel, Long> {
    public Long countByCustomerEmailAndBookId(String customer, Long bookId);
    public ArrayList<likeModel> searchDistinctCustomerEmailByBookId(Long bookId);
    public Long countByBookId(Long bookId);
}
