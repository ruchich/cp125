package com.scg.domain;

import com.scg.util.Address;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public final class InvoiceHeader {

	String businessName;
	Address businessAddress;
	ClientAccount client;
	java.time.LocalDate invoiceDate;
	java.time.LocalDate invoiceForMonth;

	public InvoiceHeader(String businessName, Address businessAddress, ClientAccount client,
			java.time.LocalDate invoiceDate, java.time.LocalDate invoiceForMonth) {
		this.businessName = businessName;
		this.client = client;
		this.businessAddress = businessAddress;
		this.invoiceDate = invoiceDate;
		this.invoiceForMonth = invoiceForMonth;
	}
}