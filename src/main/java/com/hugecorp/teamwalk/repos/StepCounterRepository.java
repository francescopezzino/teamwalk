package com.hugecorp.teamwalk.repos;

import com.hugecorp.teamwalk.domain.StepCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepCounterRepository extends JpaRepository<StepCounter, Long> {

    // SELECT * FROM step_counters ORDER BY steps DESC
    List<StepCounter> findAllByOrderByStepsDesc();
}
