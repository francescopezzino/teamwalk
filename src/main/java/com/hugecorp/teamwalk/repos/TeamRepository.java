package com.hugecorp.teamwalk.repos;

import com.hugecorp.teamwalk.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    <Optional>Team findByName(String name);
}
