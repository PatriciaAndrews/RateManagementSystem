package com.updatedrms.rate.service;


import com.updatedrms.rate.exception.InternalServerException;
import com.updatedrms.rate.exception.NoDataFoundException;
import com.updatedrms.rate.exception.NotFoundException;
import com.updatedrms.rate.model.Rate;
import com.updatedrms.rate.model.RateSurcharge;
import com.updatedrms.rate.model.Surcharge;
import com.updatedrms.rate.repository.RateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepository;

    RestTemplate restTemplate = new RestTemplate();
    //String surchargeUrl="https://surcharge.free.beeceptor.com/surchargeq";
    String surchargeUrl="https://surcharge1.free1.beecepto1r.com/surchargeq";

    @Override
    public List<Rate> getAllRates() {
        List<Rate> rates=rateRepository.findAll();
        if(rates.isEmpty())
            throw new NoDataFoundException("No Data Found");
        else
            return rateRepository.findAll();
    }

    @Override
    public RateSurcharge getRateById(int rateId) {

        Rate fetchedRate=rateRepository.findById(rateId).orElse(null);

        if(fetchedRate==null)
            throw new NotFoundException("Not Found");
        else{
            Surcharge surcharge
                    = restTemplate.getForObject(surchargeUrl , Surcharge.class);
            log.info("surcharge::"+surcharge);
            RateSurcharge rateSurcharge= new RateSurcharge();
            rateSurcharge.setRateId(fetchedRate.getRateId());
            rateSurcharge.setRateDescription(fetchedRate.getRateDescription());
            rateSurcharge.setRateEffectiveDate(fetchedRate.getRateEffectiveDate());
            rateSurcharge.setRateExpirationDate(fetchedRate.getRateExpirationDate());
            rateSurcharge.setAmount(fetchedRate.getAmount());
            rateSurcharge.setSurcharge(surcharge.getSurcharge());
            rateSurcharge.setTax(surcharge.getTax());
            return  rateSurcharge;
        }

    }

    @Override
    public Rate saveRate(Rate rate) {
        try {
           return rateRepository.save(rate);
        }catch(Exception e){
            throw new InternalServerException("Error while saving");
        }
    }

    @Override
    public Rate updateRate(Rate rate) {
        Rate fetchedRate= rateRepository.findById(rate.getRateId()).orElseThrow(()-> new NotFoundException("Not Found"));
        log.info("Value in fetchedRate:: "+fetchedRate);
        if (fetchedRate!=null) {
            fetchedRate.setRateDescription(rate.getRateDescription());
            fetchedRate.setRateEffectiveDate(rate.getRateEffectiveDate());
            fetchedRate.setRateExpirationDate(rate.getRateExpirationDate());
            fetchedRate.setAmount(rate.getAmount());
            try {
                rateRepository.save(fetchedRate);
            }catch(Exception e){
                throw new InternalServerException("Error while saving");
            }
        }
        return fetchedRate;
    }

    @Override
    public String deleteRate(int rateId) {
        Rate rate=rateRepository.findById(rateId).orElseThrow(()-> new NotFoundException("Not Found"));
        rateRepository.deleteById(rate.getRateId());
        return  "Rate Id: "+ rateId + " is deleted ";
    }
}
