package com.updatedrms.rate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RateSurcharge {
    private int rateId;
    private String rateDescription;
    private LocalDate rateEffectiveDate ;
    private LocalDate rateExpirationDate;
    private int amount;
    private int surcharge;
    private int tax;
}
