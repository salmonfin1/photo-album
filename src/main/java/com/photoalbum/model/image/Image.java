package com.photoalbum.model.image;

import com.photoalbum.model.tag.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Image {

	@Id
	private String id;

	private String encodedImage;

	private String parsedText;

	private List<Tag> tags;

	public Image(String encodedImage, String parsedText, List<Tag> tags) {
		this.id = id;
		this.encodedImage = encodedImage;
		this.parsedText = parsedText;
		this.tags = tags;
	}

	public static class ImageBuilder {

		private String encodedImage;
		private String parsedText;
		private List<Tag> tags;


		public ImageBuilder encodedImage(String encodedImage) {
			this.encodedImage = encodedImage;
			return this;
		}

		public ImageBuilder parsedText(String parsedText) {
			this.parsedText = parsedText;
			return this;
		}

		public ImageBuilder tags(List<Tag> tags) {
			this.tags = tags;
			return this;
		}

		public Image build() {
			return new Image(encodedImage, parsedText, tags);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}

	public String getParsedText() {
		return parsedText;
	}

	public void setParsedText(String parsedText) {
		this.parsedText = parsedText;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
