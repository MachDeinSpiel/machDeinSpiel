package de.hsbremen.mds.common.interfaces;

import java.util.HashMap;

public interface ServerInterface {
	
	void createGame();
	void addPlayer(GuiInterface client);
	void startGame();
	void endGame();
	public HashMap <String,Object> getWhiteboard();
	
	// F�r Servermittelungen kann die GuiInterface-Methode "update()" augerufen werden,
	// danach. Mit Update holt sich der Client dann die ben�tigten Whiteboards
	
}
