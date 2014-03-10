package de.hsbremen.mds.common.valueobjects.statemachine;

import java.util.List;

/**
 * @author JG, NH, JW, SE, AB, RS, OT
 */

public class MdsState {
	
	private int id;
	private String name;
	private boolean startState;
	private boolean finalsState;
	private MdsAction doAction;
	private MdsAction startAction;
	private MdsAction endAction;
	private MdsTransition[] transitions;
	
	public MdsState(int id, String name, MdsAction doAction, boolean startState, boolean finalState) {
		this.setId(id);
		this.name = name;
		this.setDoAction(doAction);
		this.setStartState(startState);
		this.setFinalState(finalState);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStartState() {
		return startState;
	}
	public void setStartState(boolean startState) {
		this.startState = startState;
	}
	public boolean isFinalsState() {
		return finalsState;
	}
	public void setFinalState(boolean finalsState) {
		this.finalsState = finalsState;
	}
	public boolean getFinalState() {
		return finalsState;
	}
	public MdsAction getDoAction() {
		return doAction;
	}
	public void setDoAction(MdsAction doAction) {
		this.doAction = doAction;
	}
	public MdsAction getStartAction() {
		return startAction;
	}
	public void setStartAction(MdsAction startAction) {
		this.startAction = startAction;
	}
	public MdsAction getEndAction() {
		return endAction;
	}
	public void setEndAction(MdsAction endAction) {
		this.endAction = endAction;
	}
	public MdsTransition[] getTransitions() {
		return transitions;
	}
	public void setTransitions(MdsTransition[] transitions) {
		this.transitions = transitions;
	}

}
