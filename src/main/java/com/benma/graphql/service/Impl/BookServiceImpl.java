package com.benma.graphql.service.Impl;

import com.benma.graphql.entity.Book;
import com.benma.graphql.repository.BookRepository;
import com.benma.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void create(Book book) {
        bookRepository.save(book);
    }
    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book findByIdAndPageCount(String id, Integer pageCount) {
        return bookRepository.findByIdAndPageCount(id,pageCount);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page bookByPage(Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Book> bookPage = bookRepository.findAll(pageRequest);
        return bookPage;
    }

    @Override
    public Page<Book> booksPagingByPageCount(Integer page, Integer pageSize, Integer pageCount) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Book> bookPage = bookRepository.findByPageCount(pageCount, pageRequest);
        return bookPage;
    }

    @Override
    public List<Book> booksLikeByName(String name) {
        List<Book> bookList = bookRepository.findByNameLike("%"+name+"%");
        return bookList;
    }
}
