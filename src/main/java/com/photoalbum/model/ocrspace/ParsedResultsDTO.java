package com.photoalbum.model.ocrspace;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParsedResultsDTO {

	@JsonProperty("ParsedResults")
	private List<ParsedResultDTO> parsedResults;

	@JsonProperty("ErrorMessage")
	private String errorMessage;

	@JsonProperty("ErrorDetails")
	private String errorDetails;

	public List<ParsedResultDTO> getParsedResults() {
		return parsedResults;
	}

	public void setParsedResults(List<ParsedResultDTO> parsedResults) {
		this.parsedResults = parsedResults;
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
