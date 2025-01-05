package Workflow.Repository;

import Workflow.Model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
    // Custom queries can be added if necessary
}
