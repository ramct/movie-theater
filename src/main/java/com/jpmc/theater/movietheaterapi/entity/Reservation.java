package com.jpmc.theater.movietheaterapi.entity;

import com.jpmc.theater.movietheaterapi.service.DiscountProvider;

public class Reservation {
	private final Customer customer;
	private final Showing showing;
	private final int audienceCount;
	private final DiscountProvider discountProvider;

	public Reservation(Customer customer, Showing showing, int audienceCount) {
		this.customer = customer;
		this.showing = showing;
		this.audienceCount = audienceCount;
		this.discountProvider = new DiscountProvider(showing);
	}

	public double totalFee() {
		return calculateTicketPrice() * audienceCount;
	}

	public double calculateTicketPrice() {
		return showing.getMovie().getTicketPrice() - discountProvider.getDiscount();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reservation [customer=").append(customer).append(", showing=").append(showing)
				.append(", audienceCount=").append(audienceCount).append(", totalFee=").append(this.totalFee()).append("]");
		return builder.toString();
	}
}