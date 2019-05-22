package com.benma.graphql.service.Impl;

import com.benma.graphql.entity.Author;
import com.benma.graphql.repository.AuthorRepository;
import com.benma.graphql.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author findById(String id) {
        return authorRepository.findById(id).orElse(null);
    }
}
