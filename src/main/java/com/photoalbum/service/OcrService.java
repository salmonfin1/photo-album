package com.photoalbum.service;

import com.photoalbum.configuration.OcrConfiguration;
import com.photoalbum.model.ocrspace.ParsedResultDTO;
import com.photoalbum.model.ocrspace.ParsedResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class OcrService {

	private RestTemplate restTemplate;

	private OcrConfiguration ocrConfiguration;

	@Autowired
	public OcrService(final RestTemplate restTemplate,
	                  final OcrConfiguration ocrConfiguration) {
		this.restTemplate = restTemplate;
		this.ocrConfiguration = ocrConfiguration;
	}

	public String getParsedText(String encodedImage) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("apikey", ocrConfiguration.getKey());

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("base64Image", encodedImage);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<ParsedResultsDTO> responseEntity = restTemplate.
				postForEntity(ocrConfiguration.getUrl(), request, ParsedResultsDTO.class);

		return responseEntity.getBody().getParsedResults()
				.stream().findFirst()
				.map(ParsedResultDTO::getParsedText).orElse(null);

	}
}
