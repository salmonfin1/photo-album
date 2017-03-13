package com.photoalbum.controller;

import com.photoalbum.model.tag.Tag;
import com.photoalbum.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

	private TagService tagService;

	public TagController(final TagService tagService) {
		this.tagService = tagService;
	}

	@RequestMapping
	public @ResponseBody List<Tag> getTags() {
		return tagService.getTags();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Tag saveTag(@RequestBody Tag tag) {
		return tagService.saveTag(tag);
	}
}
