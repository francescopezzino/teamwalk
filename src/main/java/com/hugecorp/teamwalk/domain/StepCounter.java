package com.hugecorp.teamwalk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hugecorp.teamwalk.enums.State;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "StepCounters")
public class StepCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer steps = 0;

    @OneToOne
    @JoinColumn(name = "team_id") // Creates a foreign key column in the 'Teams' table
    @JsonBackReference
    private Team team;

    @Version
    private Integer version;

    @Enumerated(EnumType.STRING)
    private State state;
}
