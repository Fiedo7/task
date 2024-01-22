package com.cqd.task.api.task.model;

import com.cqd.task.api.task.dto.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq_generator")
    @SequenceGenerator(name = "task_seq_generator", sequenceName = "task_seq", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    private String pattern;
    private String input;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private int progress;
    private int resultPosition;
    private int resultTypos;

}
