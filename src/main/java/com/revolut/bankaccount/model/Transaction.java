package com.revolut.bankaccount.model;

public class Transaction {
	private long transmitterId;
	private long receiverId;
	private double amount;

	public Transaction(long transmitterId, long receiverInfoId, double amount) {
		this.transmitterId = transmitterId;
		this.receiverId = receiverId;
		this.amount = amount;
	}

	public long getTransmitterId() {
		return transmitterId;
	}

	public void setTransmitterId(long transmitterId) {
		this.transmitterId = transmitterId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public double getAmount() {
		return amount;
	}
}
