package main.java.storyArch.model;

import java.util.Date;


/**
 * This class stores the information of a subscription such as start date, end date, and the renewal date.
 */

public class Subscription extends Invoice {

    private Date startDate;
    private Date endDate;

    private Date renewalDate;


    public Subscription(String fullName, String email, String password, String TOTP_TOKEN, String AUTH_TOKEN, String marketBiography, byte[] profilePicture, int invoiceID, String currency, float total, Date sentDate, Date dueDate, Date paidDate, Date startDate, Date endDate, Date renewalDate) {
        super(fullName, email, password, TOTP_TOKEN, AUTH_TOKEN, marketBiography, profilePicture, invoiceID, currency, total, sentDate, dueDate, paidDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.renewalDate = renewalDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }
}
