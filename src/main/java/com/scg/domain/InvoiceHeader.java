package com.scg.domain;

import java.time.LocalDate;
import java.util.Formatter;
import java.util.Locale;

import com.scg.util.Address;

/**
 * Header for Small Consulting Group Invoices.
 *
 * 
 */
final class InvoiceHeader {
    /** Header format string. */
    private static final String HEADER_FORMAT =
        "%s%n%s%n%nInvoice for:%n%s%nInvoice For Month of: %4$tB %4$tY%nInvoice Date: %5$tB %5$td, %5$tY%n%n";

    /** Format string for a line header on the invoice. */
    private static final String LINE_HEADER_FORMAT =
          "Date        Consultant                   Skill                Hours  Charge%n"
        + "----------  ---------------------------  ------------------   -----  ----------%n";

    /**
     * Client for the invoice with this header.
     */
    private final ClientAccount client;

    /**
     * Date of the invoice with this header.
     */
    private final LocalDate invoiceDate;

    /**
     * Month of invoice with this header.
     */
    private final LocalDate invoiceForMonth;

    /** The business name. */
    private final String businessName;

    /** The business address. */
    private final Address businessAddress;


    /**
     * Construct an InvoiceHeader.
     *
     * @param businessName name of business issuing invoice
     * @param businessAddress address of business issuing invoice
     * @param client client for the invoice with this header.
     * @param invoiceDate date of the invoice with this header.
     * @param invoiceForMonth month of billable charges for invoice with this header.
     */
    public InvoiceHeader(final String businessName, final Address businessAddress,
                         final ClientAccount client, final LocalDate invoiceDate,
                         final LocalDate invoiceForMonth) {

        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.client = client;
        this.invoiceDate = invoiceDate;
        this.invoiceForMonth = invoiceForMonth;
    }

    /**
     * Print this InvoiceHeader.
     *
     * @return Formatted string of the information in this header.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(HEADER_FORMAT, businessName, businessAddress,
                         client, invoiceForMonth, invoiceDate)
                 .format(LINE_HEADER_FORMAT);

        final String s = formatter.toString();
        formatter.close();
        return s;
    }
}
