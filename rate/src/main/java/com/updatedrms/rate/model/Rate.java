package com.updatedrms.rate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rate {
    @Id
    private int rateId;
    private String rateDescription;
    private LocalDate rateEffectiveDate ;
    private LocalDate rateExpirationDate;
    private int amount;
}
