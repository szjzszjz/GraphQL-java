package com.benma.graphql.service;

import com.benma.graphql.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    Page bookByPage(Integer page, Integer pageSize);

    void create(Book book);

    void delete(String id);

    Book findById(String id);

    Book findByIdAndPageCount(String id,Integer pageCount);

    List<Book> findAll();

    Page<Book> booksPagingByPageCount(Integer page, Integer pageSize, Integer pageCount);

    List<Book> booksLikeByName(String name);
}
