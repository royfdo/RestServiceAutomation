package com.ethor.testbed.api.domain.restaurant;


public class AcceptedPayments {

	private String paymentType;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	

	@Override
	public String toString() {
		return paymentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcceptedPayments other = (AcceptedPayments) obj;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		return true;
	}
	
	

}
