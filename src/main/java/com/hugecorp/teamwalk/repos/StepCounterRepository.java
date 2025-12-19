package com.hugecorp.teamwalk.repos;

import com.hugecorp.teamwalk.domain.StepCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepCounterRepository extends JpaRepository<StepCounter, Long> {

    // SELECT * FROM step_counters ORDER BY steps DESC
    List<StepCounter> findAllByOrderByStepsDesc();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE StepCounter s SET s.steps = s.steps + :steps WHERE s.id = :id")
    void incrementSteps(@Param("id") Long id, @Param("steps") Integer steps);
}
