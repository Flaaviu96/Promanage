package Workflow.Repository;


import Workflow.Model.StateTransition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransitionRepository extends JpaRepository<StateTransition, Long> {
    // Custom queries can be added if necessary
}
