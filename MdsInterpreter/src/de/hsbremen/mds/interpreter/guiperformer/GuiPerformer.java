package de.hsbremen.mds.interpreter.guiperformer;

import java.util.HashMap;

import de.hsbremen.mds.common.interfaces.GuiInterface;

public abstract class GuiPerformer {

	public abstract void execute(GuiInterface gui, HashMap<String, String> params);
}
