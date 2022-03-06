package com.apirest.bookshop.repositories;

import com.apirest.bookshop.models.bookModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepository extends JpaRepository<bookModel, Long> {
    public Page<bookModel> searchByAvailable(Boolean available, Pageable pageable);
    public Page<bookModel> searchByTitleIgnoreCaseContaining(String String, Pageable pageable);
}
