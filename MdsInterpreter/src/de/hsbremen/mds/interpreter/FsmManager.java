package de.hsbremen.mds.interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.util.Log;

import de.hsbremen.mds.common.valueobjects.statemachine.MdsEvent;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsState;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsTransition;
import de.hsbremen.mds.interpreter.exceptions.InvalidFsmException;

/**
 * @author JG, NH, JW 
 */
public class FsmManager {

	//Lists with the Mds value objects
	private List<MdsState> MdsStates; 
	private List<MdsState> mdsStates;
	private List<StateChangedListener> stateChangedListener;
	private MdsState currentState;
	private EventParser eventParser;
	private double longitude;
	private double latitude;
	private String UiEventName;
	private String buttonName;
	
	private HashMap<String, String> whiteBoard;
	
	public FsmManager(List <MdsState> mdsStates, EventParser eventParser){
		this.eventParser = eventParser;
		this.mdsStates = mdsStates;
		this.stateChangedListener = new Vector<StateChangedListener>();
		this.whiteBoard = new HashMap<String, String>();
	}
	
	//Add listener to the StateChangedListener - List 
	public void addStateChangedListener(StateChangedListener stateChangedListener){
		this.stateChangedListener.add(stateChangedListener);
	}
	
	private MdsState getStartState()throws InvalidFsmException{
		
		for(MdsState state:this.mdsStates){
			if(state.isStartState()){
				return state;
			}
		}
		return null;
	}
	  
	private void notifyListeners(MdsState oldState, MdsState nextState, MdsEvent event){
		for(StateChangedListener listener: this.stateChangedListener){
			listener.onStateChanged(oldState, nextState, event);
		}
	}
	
	public void checkEvents() throws InvalidFsmException{
		MdsEvent event = null;
		MdsState newState = null;
		if(this.currentState != null){
			this.eventParser.setGameData(longitude, latitude);
			Log.d("Interpreter", "Aktueller Zustand:"+currentState.getName());
			for(MdsTransition trans:this.currentState.getTransitions()){
				Log.d("Interpreter", "Prüfe Events...["+trans.getEvent().getName()+"|"+trans.getEvent().getType()+"]");
				Log.d("Interpreter","Ziel:["+trans.getTarget().getName()+"]");
				// if checkEvents is true, the condition for the event is true
				if(trans.getEvent().getType().equals("gameEvent")){
					if(this.eventParser.checkEvent(trans.getEvent())){
						newState = trans.getTarget();
						Log.d("Interpreter", "event:"+trans.getEvent().toString());
						this.notifyListeners(this.currentState, newState, trans.getEvent());
						//TODO: Attributes des whiteboards setzen
						//whiteBoard.putAll(trans.getEvent().getTrigger().getAttribute("object").getObject().);
						this.currentState = newState;
						return;
					} 
				} else {
					Log.d("Interpreter", "-----Button Klick :["+trans.getEvent().getName()+"] == ["+this.buttonName+"] ?");
					if(trans.getEvent().getName().equals(this.buttonName)){
						newState = trans.getTarget();
						this.notifyListeners(this.currentState, newState, trans.getEvent());
						this.currentState = newState;
						this.buttonName = "";
						return;
					}
				}
			}
		} else {
			this.currentState = this.getStartState();
			this.notifyListeners(null, this.currentState, null);
		}
	}
	
	private boolean checkUiEvent(MdsEvent ev){
		for(MdsTransition trans:this.currentState.getTransitions()){
			// if checkEvents is true, the condition for the event is true
			if(trans.getEvent().getName().equals("proceedBack")){
				return true;
			} 
		}
		return false;
	}
	
	public void setGameData(double longitude, double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public void setGameData(String buttonName){
		this.buttonName = buttonName;
	}
	
	public void setUiData(String UiEventName){
		this.UiEventName = UiEventName;
	}
	
}
