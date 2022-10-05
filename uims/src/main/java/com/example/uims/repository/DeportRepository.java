package com.example.uims.repository;

import com.example.uims.entity.Deport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeportRepository extends CrudRepository<Deport, Long> {

    @Query("SELECT d FROM Deport d where d.migration.user.personalNo = :personalNo")
    List<Deport> findDeportsByUserPersonalNo(String personalNo);
}
