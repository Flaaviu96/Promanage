package Workflow.Model;
import Workflow.Trigger.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class StateTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transition_seq")
    @SequenceGenerator(name = "transition_seq", sequenceName = "transition_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "from_state_id")
    private State fromState;

    @ManyToOne
    @JoinColumn(name = "to_state_id")
    private State toState;

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTransition that = (StateTransition) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
   // private Trigger trigger;
}
