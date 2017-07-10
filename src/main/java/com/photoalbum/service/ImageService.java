package com.photoalbum.service;

import com.photoalbum.model.image.Image;
import com.photoalbum.repository.ImageRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ImageService {

	private ImageRepository imageRepository;

	private static final Logger LOG = LoggerFactory.getLogger(ImageService.class);

	@Autowired
	public ImageService(final ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	public String encodeBase64(byte[] bytes) {
		return "data:image/png;base64," +
				StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false));
	}

	public String saveImage(Image image) {
		imageRepository.save(image);
		return image.getId();
	}

	public byte[] resizeImage(MultipartFile file) {
		try {
			BufferedImage read = ImageIO.read(file.getInputStream());
			BufferedImage resize = Scalr.resize(read, Scalr.Method.AUTOMATIC, 1600, Scalr.OP_ANTIALIAS);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( resize, "jpg", baos );
			baos.flush();
			baos.close();
			return baos.toByteArray();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return new byte[0];
	}

	public Image getOneImage() {
	    return imageRepository.findAll().stream()
                .findFirst().orElseThrow(() -> new RuntimeException("No Image Found"));
    }




}
