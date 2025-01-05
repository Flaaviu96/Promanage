package Workflow.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    private long id;

    private String projectName;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Task> taskSet = new HashSet<>();

    @OneToOne
    private Workflow workflow;
}
