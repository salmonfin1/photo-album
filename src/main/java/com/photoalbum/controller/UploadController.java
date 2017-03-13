package com.photoalbum.controller;

import com.photoalbum.facade.UploadFacade;
import com.photoalbum.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UploadController {

	private UploadFacade uploadFacade;

	@Autowired
	public UploadController(final UploadFacade uploadFacade) {
		this.uploadFacade = uploadFacade;
	}

	@PostMapping("/upload")
	public void uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		uploadFacade.uploadFile(file, request.getParameter("tags"));
	}
}
