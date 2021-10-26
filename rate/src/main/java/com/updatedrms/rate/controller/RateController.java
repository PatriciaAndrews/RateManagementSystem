package com.updatedrms.rate.controller;

import com.updatedrms.rate.model.Rate;
import com.updatedrms.rate.model.RateSurcharge;
import com.updatedrms.rate.service.RateService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
//@RequestMapping("rates")
@RequiredArgsConstructor
@Slf4j

public class RateController {

    @Autowired
    private RateService rateService;

    //@HystrixCommand(groupKey = "rms_services", commandKey = "RMS Services", fallbackMethod = "showFallBack()")
    @CircuitBreaker(name="rmssystem", fallbackMethod = "showFallBack()")
//    @GetMapping("/")
//    public String welcome2User(Principal principal) {
//        return "Hi "+principal.getName()+" Welcome";
//    }

    @Operation(summary = "Gets All Rates from RMS", operationId = "getAllRates",tags={"Fetch All Rates"})
    @GetMapping(value = "rates/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rate> getAllRates(){
        return rateService.getAllRates();
    }

    @Operation(summary = "Gets Rate for given rateid from RMS", operationId = "getRateById",tags={"Fetch a Rate"})
    @GetMapping(value = "rates/{rateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RateSurcharge getRateById(@PathVariable int rateId){
        return rateService.getRateById(rateId);
    }

    @Operation(summary = "Create Rate from RMS", operationId = "saveRate",tags={"Create a Rate"})
    @PostMapping(value = "rates/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Rate saveRate(@RequestBody Rate rate){
        return rateService.saveRate(rate);
    }

    @Operation(summary = "Update a given rateid from RMS", operationId = "updateRate",tags={"Update a Rate"})
    @PutMapping(value = "rates/")
    public Rate updateRate(@RequestBody Rate rate){
        return rateService.updateRate(rate);
    }

    @Operation(summary = "Delete a given rateid from RMS", operationId = "deleteRate",tags={"Delete a Rate"})
    @DeleteMapping(value = "rates/{rateId}")
    public String deleteRate(@PathVariable int rateId){
        return rateService.deleteRate(rateId);
    }


    public String showFallBack() {
        return "DB /API End Points not responding";
    }

}
