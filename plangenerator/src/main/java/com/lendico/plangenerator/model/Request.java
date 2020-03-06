package com.lendico.plangenerator.model;


import com.lendico.plangenerator.exception.ApiException;
import org.springframework.http.HttpStatus;

public class Request {
    private double loanAmount;
    private double nominalRate;
    private int duration;
    private String startDate;

    public Request() {
    }

    public Request(final double loanAmount, final double nominalRate, final int duration, final String startDate) {
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.startDate = startDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getNominalRate() {
        return nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void validate() {
        if (this.loanAmount <= 0)
            throw new ApiException("The loan amount is required and cannot be a negative number.",
                    HttpStatus.BAD_REQUEST);

        if (this.nominalRate <= 0)
            throw new ApiException("The nominal rate is required and cannot be a negative number.",
                    HttpStatus.BAD_REQUEST);

        if (this.duration <= 0)
            throw new ApiException("The duration is required and cannot be a negative number.", HttpStatus.BAD_REQUEST);

        if (this.startDate == null || this.startDate.isEmpty())
            throw new ApiException("Please provide the start date.", HttpStatus.BAD_REQUEST);
    }
}
