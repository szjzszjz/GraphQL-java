package com.benma.graphql.dataFetcher;

import com.benma.graphql.Utils.ToEntityUtil;
import com.benma.graphql.entity.Author;
import com.benma.graphql.entity.Book;
import com.benma.graphql.service.AuthorService;
import com.benma.graphql.service.BookService;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
public class BookDataFetcher {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;


    /**
     * 添加
     */
    public DataFetcher createBook() {
        return dataFetchingEnv -> {
            LinkedHashMap bookMap = dataFetchingEnv.getArgument("book");
            Book book = (Book) ToEntityUtil.entity(new Book(), bookMap);
            bookService.create(book);
            return book;
        };
    }

    /**
     * 根据id删除
     */
    public DataFetcher deleteBook() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            bookService.delete(bookId);
            return null;
        };
    }


    /**
     * 修改
     */
    public DataFetcher modifyBook() {
        return dataFetcherEvn -> {
            String id = dataFetcherEvn.getArgument("id");
            LinkedHashMap hashMap = dataFetcherEvn.getArgument("book");
            Book book = bookService.findById(id);
            Book booked = (Book) ToEntityUtil.entity(book, hashMap);
            bookService.create(booked);
            return book;
        };
    }

    /**
     * 查询所有
     */
    public DataFetcher books() {
        return evn -> {
            List<Book> bookList = bookService.findAll();
            return bookList;
        };
    }

    /**
     * 分页查询
     */
    public DataFetcher booksPaging() {
        return dataFetchingEvn -> {
            Integer page = dataFetchingEvn.getArgument("page");
            Integer pageSize = dataFetchingEvn.getArgument("pageSize");
            Page bookPage = bookService.bookByPage(page, pageSize);
            return bookPage.getContent();
        };
    }

    /**
     * 根据条件分页查询
     */
    public DataFetcher booksPagingByPageCount() {
        return dataFetchingEnvironment -> {
            Integer pageCount = dataFetchingEnvironment.getArgument("pageCount");
            Integer page = dataFetchingEnvironment.getArgument("page");
            Integer pageSize = dataFetchingEnvironment.getArgument("pageSize");
            Page<Book> bookPage = bookService.booksPagingByPageCount(page, pageSize, pageCount);
            return bookPage.getContent();
        };
    }


    /**
     * 根据条件模糊查询
     */
    public DataFetcher booksLikeByName() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            List<Book> bookList = bookService.booksLikeByName(name);
            return bookList;
        };
    }

    /**
     * 多条件查询
     */
    public DataFetcher bookByPageCountAndId() {
        return dataFetchingEnvironment -> {
            Integer pageCount = dataFetchingEnvironment.getArgument("pageCount");
            String bookId = dataFetchingEnvironment.getArgument("id");
            Book book = bookService.findByIdAndPageCount(bookId, pageCount);
            return book;
        };
    }

    /**
     * 根据id查询
     */
    public DataFetcher bookById() {

        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            Book book = bookService.findById(bookId);
            return book;
        };
    }

    public DataFetcher author() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            String authorId = book.getAuthorId();
            Author author = authorService.findById(authorId);
            return author;
        };
    }


}
