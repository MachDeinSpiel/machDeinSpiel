package de.hsbremen.mds.common.valueobjects.statemachine;

import java.util.List;

public class MdsObjectContainer {

	private List<MdsAction> actions;
	private MdsPlayer player;
	private List<MdsExhibit> exhibits;
	private List<MdsItem> items;
	private List<MdsState> states;
	
	public MdsObjectContainer(List<MdsAction> actions, MdsPlayer player, List<MdsExhibit> exhibits, List<MdsItem> items, List<MdsState> states) {
		this.actions = actions;
		this.player = player;
		this.exhibits = exhibits;
		this.items = items;
		this.states = states;
	}
	
	public List<MdsAction> getActions() {
		return actions;
	}
	public void setActions(List<MdsAction> actions) {
		this.actions = actions;
	}
	public MdsPlayer getPlayer() {
		return player;
	}
	public void setPlayer(MdsPlayer player) {
		this.player = player;
	}
	public List<MdsExhibit> getExhibits() {
		return exhibits;
	}
	public void setExhibits(List<MdsExhibit> exhibits) {
		this.exhibits = exhibits;
	}
	public List<MdsItem> getItems() {
		return items;
	}
	public void setItems(List<MdsItem> items) {
		this.items = items;
	}
	public List<MdsState> getStates() {
		return states;
	}
	public void setStates(List<MdsState> states) {
		this.states = states;
	}
	
	
}
