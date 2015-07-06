package com.ethor.testbed.api.domain.order;

/**
 * This class encapsulates payments details.
 * 
 * @author Roy Fernando.
 */
public class Payment {

	private PaymentMethod method;
	private String cardNumber;
	private String nameOnCard;
	private String expireYear;
	private String expireMonth;

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(final PaymentMethod method) {
		this.method = method;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(final String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(final String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getExpireYear() {
		return expireYear;
	}

	public void setExpireYear(final String expireYear) {
		this.expireYear = expireYear;
	}

	public String getExpireMonth() {
		return expireMonth;
	}

	public void setExpireMonth(final String expireMonth) {
		this.expireMonth = expireMonth;
	}

}
