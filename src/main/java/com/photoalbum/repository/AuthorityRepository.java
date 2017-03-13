package com.photoalbum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.photoalbum.model.user.Authority;

public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
