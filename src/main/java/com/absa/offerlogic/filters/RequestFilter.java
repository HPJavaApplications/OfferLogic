package com.absa.offerlogic.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.config.Config;

import com.absa.offerlogic.constants.ConfigConstants;
import com.absa.offerlogic.constants.GenericConstants;

/**
*
*/
@Provider
public class RequestFilter implements ContainerRequestFilter {

	@Inject
	Config config;

	List<String> skipRequestPaths;

	@PostConstruct
	public void init() {

		String[] requests;

		Optional<String> skipRequests = config.getOptionalValue(ConfigConstants.SKIP_REQUEST_PATHS, String.class);

		if (skipRequests.isPresent()) {

			requests = skipRequests.get().split(",");

			skipRequestPaths = Arrays.asList(requests);

			return;

		}

		skipRequestPaths = Collections.emptyList();
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		if (isAuthDisabledForThisRequest(requestContext.getUriInfo())) {
			return;
		}

		if (!checkRequestIsAuthorized(requestContext)) {

			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity(prepareUnAuthorizedResponse()).build());

		}

	}

	private boolean isAuthDisabledForThisRequest(UriInfo uriInfo) {

		String requestPath = uriInfo.getPath();

		return skipRequestPaths.stream().anyMatch(requestPath::equalsIgnoreCase);

	}

	private boolean checkRequestIsAuthorized(ContainerRequestContext requestContext) {

		MultivaluedMap<String, String> map = requestContext.getHeaders();

		return map.get(GenericConstants.API_KEY) != null && !map.get(GenericConstants.API_KEY).isEmpty()
				&& map.get(GenericConstants.API_KEY).get(0)
						.equalsIgnoreCase(config.getValue(ConfigConstants.X_API_KEY, String.class));

	}

	private Object prepareUnAuthorizedResponse() {

		Map<String, Object> response = new HashMap<>();

		response.put("message", "UnAuthorized");

		response.put("status", "FAILED");

		response.put("statusCode", 401);

		response.put("timestamp", new Date().getTime());

		return response;

	}

}
