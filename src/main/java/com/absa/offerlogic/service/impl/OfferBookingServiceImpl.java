package com.absa.offerlogic.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.Config;

import com.absa.offerlogic.constants.ConfigConstants;
import com.absa.offerlogic.payloads.OfferBookingPayload;
import com.absa.offerlogic.request.HttpMethod;
import com.absa.offerlogic.request.RestClientRequest;
import com.absa.offerlogic.response.OfferBookingResponse;
import com.absa.offerlogic.service.OfferBookingService;
import com.absa.offerlogic.service.utils.ServiceUtils;

/**
*
*/
@ApplicationScoped
public class OfferBookingServiceImpl implements OfferBookingService {

	@Inject
	Config config;

	@Inject
	ServiceUtils serviceUtils;

	@Override
	public OfferBookingResponse bookOffer(OfferBookingPayload payload) {

		RestClientRequest<String> request = new RestClientRequest<>(
				config.getValue(ConfigConstants.AMOL_URL, String.class), HttpMethod.GET, String.class);

		String response = serviceUtils.execute(request);

		OfferBookingResponse re = new OfferBookingResponse();

		re.setMessage(response);

		return re;
	}

}
