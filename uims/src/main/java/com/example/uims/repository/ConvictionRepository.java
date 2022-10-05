package com.example.uims.repository;

import com.example.uims.entity.Conviction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConvictionRepository extends CrudRepository<Conviction, Long> {
    List<Conviction> findConvictionsByUserId(long userId);
}
