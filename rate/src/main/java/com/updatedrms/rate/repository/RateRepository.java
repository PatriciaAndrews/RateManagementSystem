package com.updatedrms.rate.repository;


import com.updatedrms.rate.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository <Rate,Integer>{
}
