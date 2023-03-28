package main.java.storyArch.model;

import java.util.Date;

/**
 * Invoice class stores the user's invoice information
 * It extends the User class
 */
public class Invoice {
    private int invoiceID;
    private String currency;

    private float total;

    private Date  sentDate;

    private Date dueDate;

    private Date paidDate;

}
