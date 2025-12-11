package com.hugecorp.teamwalk.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();

    @OneToOne // This side owns the relationship/join column
    @JoinColumn(name = "step_counter_id") // Creates a foreign key column in the 'Teams' table
    @JsonManagedReference
    private StepCounter stepcounter;

    @Version
    private Integer version;

}

