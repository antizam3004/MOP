package com.antizam.repository;

import com.antizam.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value="SELECT AVG(response_time) FROM requests_tbl WHERE request_executed_at=:requestExecutedAt", nativeQuery = true)
    double getAverage(@Param("requestExecutedAt") Date requestExecutedAt);
}
