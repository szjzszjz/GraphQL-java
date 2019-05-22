package com.benma.graphql.repository;

import com.benma.graphql.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author ,String > {

}
