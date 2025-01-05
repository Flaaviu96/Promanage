package Workflow;

import Workflow.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import Workflow.Model.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WorkflowRepository workflowRepository;
    private final StateRepository stateRepository;
    private final TransitionRepository transitionRepository;

    public DataInitializer(WorkflowRepository workflowRepository,
                           StateRepository stateRepository,
                           TransitionRepository transitionRepository) {
        this.workflowRepository = workflowRepository;
        this.stateRepository = stateRepository;
        this.transitionRepository = transitionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert default workflows
        Workflow workflow1 = new Workflow();

        workflowRepository.save(workflow1);

        Workflow workflow2 = new Workflow();

        workflowRepository.save(workflow2);

        // Insert default states for workflow1
        State startState = new State();
        startState.setName("START");
        startState.setStateType(StateType.INITIAL); // Assuming StateType enum exists
        startState.setWorkflow(workflow1);
        stateRepository.save(startState);

        State inProgressState = new State();
        inProgressState.setName("IN_PROGRESS");
        inProgressState.setStateType(StateType.INTERMEDIATE); // Assuming StateType enum exists
        inProgressState.setWorkflow(workflow1);
        stateRepository.save(inProgressState);

        State inReview = new State();
        inReview.setName("IN_REVIEW");
        inReview.setStateType(StateType.INTERMEDIATE); // Assuming StateType enum exists
        inReview.setWorkflow(workflow1);
        stateRepository.save(inReview);

        State closedState = new State();
        closedState.setName("CLOSED");
        closedState.setStateType(StateType.FINAL); // Assuming StateType enum exists
        closedState.setWorkflow(workflow1);
        stateRepository.save(closedState);

        // Insert default transitions for workflow1
        StateTransition transition1 = new StateTransition();
        transition1.setFromState(startState);
        transition1.setToState(inProgressState);
        transition1.setWorkflow(workflow1);
        transitionRepository.save(transition1);

        StateTransition transition3 = new StateTransition();
        transition3.setFromState(startState);
        transition3.setToState(inReview);
        transition3.setWorkflow(workflow1);
        transitionRepository.save(transition3);

        // Add the transition to outgoingTransitions of startState and incomingTransitions of inProgressState
        startState.getOutgoingTransitions().add(transition1);
        startState.getOutgoingTransitions().add(transition3);
        inProgressState.getIncomingTransitions().add(transition1);

        // Insert another transition
        StateTransition transition2 = new StateTransition();
        transition2.setFromState(inProgressState);
        transition2.setToState(closedState);
        transition2.setWorkflow(workflow1);
        transitionRepository.save(transition2);

        // Add the transition to outgoingTransitions of inProgressState and incomingTransitions of closedState
        inProgressState.getOutgoingTransitions().add(transition2);
        closedState.getIncomingTransitions().add(transition2);

        // Save updated states with transitions added
        stateRepository.save(startState);
        stateRepository.save(inProgressState);
        stateRepository.save(closedState);

        // Insert default states for workflow2 (if needed)
        State anotherStartState = new State();
        anotherStartState.setName("START");
        anotherStartState.setStateType(StateType.INITIAL);
        anotherStartState.setWorkflow(workflow2);
        stateRepository.save(anotherStartState);

        State anotherClosedState = new State();
        anotherClosedState.setName("CLOSED");
        anotherClosedState.setStateType(StateType.FINAL);
        anotherClosedState.setWorkflow(workflow2);
        stateRepository.save(anotherClosedState);

        // Insert transitions for workflow2 (if needed)
        StateTransition transitionForWorkflow2 = new StateTransition();
        transitionForWorkflow2.setFromState(anotherStartState);
        transitionForWorkflow2.setToState(anotherClosedState);
        transitionForWorkflow2.setWorkflow(workflow2);
        transitionRepository.save(transitionForWorkflow2);

        // Add transition to outgoingTransitions of anotherStartState and incomingTransitions of anotherClosedState
        anotherStartState.getOutgoingTransitions().add(transitionForWorkflow2);
        anotherClosedState.getIncomingTransitions().add(transitionForWorkflow2);

        // Save updated states with transitions added
        stateRepository.save(anotherStartState);
        stateRepository.save(anotherClosedState);

        System.out.println("Default data inserted successfully.");
    }
}
