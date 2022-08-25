package com.absa.offerlogic.request;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

/**
*
*/
public class RestClientRequest<T> {

	public RestClientRequest(String url, HttpMethod method, Class<T> responseType) {
		this.url = url;
		this.method = method;
		this.responseType = responseType;
	}

	private Object body;

	private String url;

	private HttpMethod method;

	private MultivaluedMap<String, Object> headers;

	private Map<String, String> requestParams;

	private Class<T> responseType;

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public Class<T> getResponseType() {
		return responseType;
	}

	public void setResponseType(Class<T> responseType) {
		this.responseType = responseType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public MultivaluedMap<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(MultivaluedMap<String, Object> headers) {
		this.headers = headers;
	}

	public Map<String, String> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, String> requestParams) {
		this.requestParams = requestParams;
	}

	public void setHeader(String key, List<Object> value) {
		this.headers.put(key, value);
	}

	public void setRequestParam(String key, String value) {
		this.requestParams.put(key, value);
	}

}
