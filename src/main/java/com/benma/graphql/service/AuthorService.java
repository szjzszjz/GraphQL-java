package com.benma.graphql.service;

import com.benma.graphql.entity.Author;

public interface AuthorService {

    Author findById(String id);
}
