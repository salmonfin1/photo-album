package com.photoalbum.repository;

import com.photoalbum.model.tag.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
}
