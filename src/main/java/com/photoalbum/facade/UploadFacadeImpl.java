package com.photoalbum.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.photoalbum.model.image.Image;
import com.photoalbum.model.tag.Tag;
import com.photoalbum.service.ImageService;
import com.photoalbum.service.OcrService;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class UploadFacadeImpl implements UploadFacade {

	public static final String RECIPE = "Recipe";
	private ImageService imageService;

	private OcrService ocrService;

	private ObjectMapper objectMapper;

	private static final Logger LOG = LoggerFactory.getLogger(UploadFacadeImpl.class);

	@Autowired
	public UploadFacadeImpl(final ImageService imageService,
	                        final OcrService ocrService) {
		this.imageService = imageService;
		this.ocrService = ocrService;
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public String uploadFile(MultipartFile file, String tagString) {
		List<Tag> tags = getTags(tagString);

		byte[] resizedImage = imageService.resizeImage(file);

		String encodedImage = imageService.encodeBase64(resizedImage);

		String parsedText = getParsedText(tags, encodedImage);

		Image image = new Image.ImageBuilder()
				.encodedImage(encodedImage)
				.parsedText(parsedText)
				.tags(tags)
				.build();

		return imageService.saveImage(image);
	}

	private String getParsedText(List<Tag> tags, String base64) {
		boolean isRecipe = tags.stream().filter(this::isRecipe).count() > 0;

		if(isRecipe) {
			return ocrService.getParsedText(base64);
		}

		return null;
	}

	private Boolean isRecipe(Tag tag) {
		return tag.getText().equals(RECIPE);
	}

	private List<Tag> getTags(String tagString) {
		try {
			CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Tag.class);
			return objectMapper.readValue(tagString, collectionType);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return Collections.emptyList();
	}

}
