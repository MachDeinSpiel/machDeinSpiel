package de.hsbremen.mds.interpreter.guiperformer;

import java.util.HashMap;

import android.util.Log;

import de.hsbremen.mds.common.interfaces.GuiInterface;
import de.hsbremen.mds.common.valueobjects.MdsText;

public class InfoPerformer extends GuiPerformer {

	@Override
	public void execute(GuiInterface gui, HashMap<String, String> params) {
		MdsText text = new MdsText(params.get("name"), params.get("url"), params.get("text"));
		Log.d("Interpreter", "Text: ["+params.get("text")+"]");
		gui.nextFragment(text);

	}

}
