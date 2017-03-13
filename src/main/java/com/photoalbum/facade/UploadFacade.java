package com.photoalbum.facade;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFacade {

	String uploadFile(MultipartFile file, String tags);
}
