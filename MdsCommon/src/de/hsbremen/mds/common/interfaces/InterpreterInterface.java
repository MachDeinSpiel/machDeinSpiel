package de.hsbremen.mds.common.interfaces;

import de.hsbremen.mds.common.valueobjects.statemachine.MdsObjectContainer;

public interface InterpreterInterface {

	void pushParsedObjects(MdsObjectContainer objectContainer);
	
}
