package com.absa.offerlogic.response;

import java.io.Serializable;

/**
*
*/
public class OfferBookingResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3031706856648702162L;

	private String message;

	private String status;

	private String statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
