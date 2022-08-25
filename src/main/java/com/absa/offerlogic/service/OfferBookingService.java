package com.absa.offerlogic.service;

import com.absa.offerlogic.payloads.OfferBookingPayload;
import com.absa.offerlogic.response.OfferBookingResponse;

/**
*
*/
public interface OfferBookingService {

	public OfferBookingResponse bookOffer(OfferBookingPayload payload);

}
