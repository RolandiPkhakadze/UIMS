package com.example.uims.repository;

import com.example.uims.entity.HealthCare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthCareRepository extends CrudRepository<HealthCare, Long> {
    List<HealthCare> findHealthCaresByUserPersonalNo(String personalNo);
}
