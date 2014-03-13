package de.hsbremen.mds.interpreter;

import de.hsbremen.mds.common.valueobjects.statemachine.MdsEvent;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsState;
/**
 * @author JG, NH, JW
 */
public interface StateChangedListener {
	public void onStateChanged(MdsState oldState, MdsState newState, MdsEvent triggerEvent);
}
