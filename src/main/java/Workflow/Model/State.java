package Workflow.Model;
import Workflow.StateType;
import Workflow.Trigger.Trigger;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;

    @OneToMany(mappedBy = "fromState", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StateTransition> outgoingTransitions = new HashSet<StateTransition>();

    @OneToMany(mappedBy = "toState", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StateTransition> incomingTransitions = new HashSet<StateTransition>();

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;

    private String name;

    private StateType stateType;

    private Trigger trigger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return id == state.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
