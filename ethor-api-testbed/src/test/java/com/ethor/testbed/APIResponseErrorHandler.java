package com.ethor.testbed;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.ethor.testbed.api.test.exception.ResponseException;

/**
 * API response error handler.
 *
 * @author Roy Fernando.
 */
public class APIResponseErrorHandler implements ResponseErrorHandler {

	private static final Logger LOGGER = Logger.getLogger(APIResponseErrorHandler.class);

	private static List<String> supportedMediaTypes = new ArrayList<String>();

	static {
		supportedMediaTypes.add(MediaType.APPLICATION_JSON.getSubtype());
		supportedMediaTypes.add(MediaType.APPLICATION_XML.getSubtype());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		//checkResponse(response); this is only for debugging purpose
		
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new ResponseException("Invalid request, returned with http status " + response.getStatusCode());
		} else if (!supportedMediaTypes.contains(response.getHeaders().getContentType().getSubtype())) {
			throw new ResponseException("Invalid response, contents must be in either " + MediaType.APPLICATION_JSON.getSubtype() + " or " + MediaType.APPLICATION_XML.getSubtype()
					+ ", but was " + response.getHeaders().getContentType().getSubtype());
		}
		return false;
	}

	private void checkResponse(ClientHttpResponse response) throws IOException {
		System.out.println("Response length :: " + response.getHeaders().getContentLength());
		
		InputStream inputStream = new BufferedInputStream(response.getBody());
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		StringBuilder sb = new StringBuilder();

		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println("Response :: " + sb.toString());
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

	}

	private void validateResponse(final String responseBody, final MediaType mediaType) {
		if (responseBody != null && !responseBody.isEmpty()) {
			if (mediaType.getSubtype().equals(MediaType.APPLICATION_JSON.getSubtype())) {
				if (responseBody.contains("description") && responseBody.contains("code")) {
					String errorCode = extractCodeFromJson(responseBody);
					String errorDiscription = extractDescriptionFromJson(responseBody);
					LOGGER.info("errorCode : " + errorCode);
					LOGGER.info("errorDiscription : " + errorDiscription);
					throw new ResponseException(errorDiscription, errorCode);
				}
			} else {
				// TODO
				return;
			}
		}
	}

	private String extractDescriptionFromJson(String jsonString) {

		int desStartIndex = jsonString.indexOf("description");
		int commaIndex = jsonString.indexOf(",");

		String jsonDescription = jsonString.substring(desStartIndex - 1, commaIndex);
		int desColanIndex = jsonDescription.indexOf(":");
		String description = jsonDescription.substring(desColanIndex + 2, jsonDescription.length() - 1);

		return description;
	}

	private String extractCodeFromJson(final String jsonString) {
		int codeStartIndex = jsonString.indexOf("code");
		String codeJsonString = jsonString.substring(codeStartIndex - 1, jsonString.length() - 2);

		int codeColanIndex = codeJsonString.indexOf(":");
		String code = codeJsonString.substring(codeColanIndex + 2, codeJsonString.length() - 1);
		if (code.charAt(code.length() - 1) == '"') {
			code = code.substring(0, code.length() - 1);
		}

		return code;
	}

	public static void main(String[] args) {

		String string = "[{\"description\":\"Not allowed ip address.\",\"code\":\"401\"}]";

		int codeStartIndex = string.indexOf("code");
		String codeJsonString = string.substring(codeStartIndex - 1, string.length() - 2);

		int codeColanIndex = codeJsonString.indexOf(":");
		String code = codeJsonString.substring(codeColanIndex + 2, codeJsonString.length() - 1);

		System.out.println(code);

		// int codeStartIndex = string.indexOf("code");

	}

}
