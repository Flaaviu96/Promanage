package Workflow.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
