package Workflow.Repository;

import Workflow.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
    // Custom queries can be added if necessary
}
