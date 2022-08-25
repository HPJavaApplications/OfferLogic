package com.absa.offerlogic.controllers;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.absa.offerlogic.payloads.OfferBookingPayload;
import com.absa.offerlogic.service.OfferBookingService;

/**
*
*/
@Path("/booking-service")
@Singleton
public class OfferBookingController {

	@Inject
	OfferBookingService bookingService;

	@POST
	@Path("/book-offer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response bookOffer(@RequestBody OfferBookingPayload payload) {

		return Response.status(Response.Status.OK).entity(bookingService.bookOffer(payload)).build();

	}

}
