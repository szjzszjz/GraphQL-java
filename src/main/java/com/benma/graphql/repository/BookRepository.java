package com.benma.graphql.repository;

import com.benma.graphql.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,String > {
    Book findByIdAndPageCount(String id,Integer pageCount);

    Page<Book> findByPageCount(Integer pageCount, Pageable pageable);

    List<Book> findByNameLike(String name);
}
