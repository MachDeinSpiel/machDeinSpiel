package de.hsbremen.mds.interpreter;

import java.io.File;

import android.util.Log;

import de.hsbremen.mds.common.interfaces.GuiInterface;
import de.hsbremen.mds.common.interfaces.InterpreterInterface;
import de.hsbremen.mds.common.listener.AndroidListener;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsEvent;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsObjectContainer;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsState;
import de.hsbremen.mds.interpreter.exceptions.InvalidFsmException;
import de.hsbremen.mds.parser.Parser;

/**
 * @author JG, NH, JW
 */

//Interpreter
public class Interpreter implements InterpreterInterface, StateChangedListener, AndroidListener {
	
	private GuiInterface guiInterface;
	private FsmManager fsmManager;
	private ActionParser actionParser;
	private boolean gameRunning = true;
	private EventParser eventParser;
	
	public Interpreter(File jsonFile, GuiInterface guiInterface){
		Log.d("Interpreter", "Interpreter erzeugt");
		this.guiInterface = guiInterface;
		Parser parser = new Parser(this, jsonFile);
		
		
		

		
	}
	
	public void pushParsedObjects(MdsObjectContainer objectContainer){
		Log.d("Interpreter", "Geparste Objekte vo Parser bekommen");
		this.actionParser = new ActionParser(objectContainer.getPlayer(), objectContainer.getExhibits(), objectContainer.getItems());
		this.eventParser = new EventParser(objectContainer.getPlayer(), objectContainer.getExhibits(), objectContainer.getItems());
		this.fsmManager = new FsmManager(objectContainer.getStates(),eventParser);
		fsmManager.addStateChangedListener(this);
		this.guiInterface.setAndroidListener(this, 5);
		//Starten
		try {
			Log.d("Interpreter", "Let fsmManager checking Events...");
			fsmManager.checkEvents();
		} catch (InvalidFsmException e) {
			Log.e("Interpreter", "Fehler beim checken der Events", e);
		}		
	}
	
	@Override
	public void onStateChanged(MdsState oldState, MdsState newState, MdsEvent triggerEvent) {
		Log.d("Interpreter", "Neuer Zustand:"+newState.getName());
		if(oldState != null && oldState.getEndAction() != null){
			actionParser.parseAction(oldState.getEndAction(), triggerEvent).execute(guiInterface);
		}
		if(newState != null){
			if(newState.getStartAction() != null){
				actionParser.parseAction(newState.getStartAction(), triggerEvent).execute(guiInterface);
			}
			if(newState.getDoAction() != null){
				actionParser.parseAction(newState.getDoAction(), triggerEvent).execute(guiInterface);
			}
		}
		
		
	}

	



	@Override
	public void onButtonClick(String buttonName) {
		this.fsmManager.setGameData(buttonName);
		try {
			this.fsmManager.checkEvents();
		} catch (InvalidFsmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onVideoEnded(String videoName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserLeftGame(String deineMudda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPositionChanged(double longitude, double latitude) {
		Log.d("Interpreter", "new Position: long:"+longitude+", lat:"+latitude);
		this.fsmManager.setGameData(longitude, latitude);
		try {
			Log.d("Interpreter", "Let fsmManager checking Events...");
			this.fsmManager.checkEvents();
		} catch (InvalidFsmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
