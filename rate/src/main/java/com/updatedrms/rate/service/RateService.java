package com.updatedrms.rate.service;

import com.updatedrms.rate.model.Rate;
import com.updatedrms.rate.model.RateSurcharge;

import java.util.List;

public interface RateService {
    public List<Rate> getAllRates();
    public RateSurcharge getRateById(int rateId);
    public Rate saveRate(Rate rate);
    public Rate updateRate(Rate rate);
    public String deleteRate(int rateId);
}
