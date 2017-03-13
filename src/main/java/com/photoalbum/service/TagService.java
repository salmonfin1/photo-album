package com.photoalbum.service;

import com.photoalbum.model.tag.Tag;
import com.photoalbum.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagService {

	private TagRepository tagRepository;

	@Autowired
	public TagService(final TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public List<Tag> getTags() {
		return tagRepository.findAll();
	}

	public Tag saveTag(Tag tag) {
		tagRepository.save(tag);
		return tag;
	}
}
