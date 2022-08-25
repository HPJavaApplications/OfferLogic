package com.absa.offerlogic.service.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.absa.offerlogic.request.RestClientRequest;

/**
*
*/
@ApplicationScoped
public class ServiceUtils {

	public <T> T execute(RestClientRequest<T> restClientRequest) {

		String url = restClientRequest.getUrl();

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(url);

		return buildRequest(restClientRequest, target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).headers(restClientRequest.getHeaders()));
	}

	private <T> T buildRequest(RestClientRequest<T> restClientRequest, Builder builder) {

		switch (restClientRequest.getMethod()) {
		case POST:
			return builder.post(Entity.json(restClientRequest.getBody()), restClientRequest.getResponseType());
		case GET:
			return builder.get(restClientRequest.getResponseType());
		case PUT:
			return builder.put(Entity.json(restClientRequest.getBody()), restClientRequest.getResponseType());
		default:
			return builder.delete(restClientRequest.getResponseType());
		}
	}

}
