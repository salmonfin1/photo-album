package com.photoalbum.model.ocrspace;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParsedResultDTO {

	@JsonProperty("ParsedText")
	private String parsedText;

	@JsonProperty("ErrorMessage")
	private String errorMessage;

	@JsonProperty("ErrorDetails")
	private String errorDetails;

	public String getParsedText() {
		return parsedText;
	}

	public void setParsedText(String parsedText) {
		this.parsedText = parsedText;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
}
