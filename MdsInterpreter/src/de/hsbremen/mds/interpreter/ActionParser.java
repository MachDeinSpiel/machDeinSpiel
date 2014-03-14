package de.hsbremen.mds.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import de.hsbremen.mds.common.interfaces.GuiInterface;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsItem;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsAction;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsActionExecutable;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsAttribute;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsEvent;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsExhibit;
import de.hsbremen.mds.common.valueobjects.statemachine.MdsPlayer;
import de.hsbremen.mds.interpreter.guiperformer.GuiPerformer;

/**
 * @author JG, NH, JW
 */
public class ActionParser {
	
	public static final String TRIGGER = "trigger";
	
	private MdsPlayer player;
	//private List<MdsExhibit> exhibits;
	private List<MdsItem> items;
	
	private HashMap<String, MdsExhibit> exhibits;
	
	public ActionParser(MdsPlayer player, List<MdsExhibit> exhibits, List<MdsItem> items) {
		this.player = player;
		this.exhibits = new HashMap<String, MdsExhibit>();
		for(MdsExhibit exhibit: exhibits){
			this.exhibits.put(exhibit.getName(), exhibit);
		}
		this.items = items;
	}
	
	/**
	 * Parset ein MdsAction-Objekt aus String zu einem ausführebaren Aktion-Objekt
	 * @param action
	 * @param triggerEvent
	 * @return
	 */
	public MdsActionExecutable parseAction(final MdsAction action, final MdsEvent triggerEvent){
		String actionIdent = action.getIdent().substring(4);
		final String className = "de.example.interpreter.guiperformer."+actionIdent.substring(0, 1).toUpperCase() + actionIdent.substring(1) + "Performer";
		
		MdsActionExecutable realAction = new MdsActionExecutable() {
			
			@Override
			public void execute(GuiInterface guiInterface) {
				
				//Geparste Parameter
				HashMap<String,String> params = new HashMap<String, String>();
				try{
					for(String key: action.getParams().keySet()){
						params.put(key,parseParam(key, action.getParams().get(key), triggerEvent));
						//Log.d("Interpreter","key:["+key+"] param:"+params.get(key)+"]");
					}
				}catch(Exception e){
					Log.e("Interpreter", "Actionparser - can't parse params. Parsing defaults", e);
//					for(String key: action.getParams().keySet()){
//						params.put(key,parseParam(key, action.getParams().get(key), triggerEvent));
//					}
					//TODO: Defaults auslesen, wenn diese im Objekt implentiert werden
				}
				

				try {
					Log.d("Interpreter", "className:"+className);
					GuiPerformer perform = (GuiPerformer) Class.forName(className).getConstructor().newInstance();
					perform.execute(guiInterface, params);
				} catch (IllegalArgumentException e) {
					Log.e("Interpreter", "ActionParser - Executionfail", e);
				} catch (InstantiationException e) {
					Log.e("Interpreter", "ActionParser - Executionfail", e);
				} catch (IllegalAccessException e) {
					Log.e("Interpreter", "ActionParser - Executionfail", e);
				} catch (InvocationTargetException e) {
					Log.e("Interpreter", "ActionParser - Executionfail", e);
				} catch (NoSuchMethodException e) {
					Log.e("Interpreter", "ActionParser - Executionfail", e);
				} catch (ClassNotFoundException e) {
					Log.e("Interpreter", "ActionParser - Executionfail", e);
				}
				
				
				
			}
		};
		
		return realAction;
	}
	
	private String parseParam(String key, String param, MdsEvent event) {
		//"url": "trigger.object.params('url')"
		String[] parts = param.split("\\.|\\(|\\)");
		for(String s: parts){
			Log.d("Interpreter", "["+s+"]");
		}
		if(parts[0].equals(TRIGGER)){
			MdsAttribute attribute = event.getTrigger().getAttribute(parts[1]);
			//Für später:.
//			try {
//				Class attributeClass = Class.forName(attribute.getType());
//				attributeClass.cast(attribute.getObject());
//				//TODO: call params or whateever from Object (cast doesn't work :( )
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//Alternative: HashMaps überall, globales MdsObject, damit nicht gecastet werden muss
			
			//Achtung: Unschöner Code, Hauptsache, wir können morgen was zeigen
			if(attribute.getType().equals("MdsExhibit")){
				MdsExhibit triggerExhibit = (MdsExhibit) attribute.getObject();
				if(parts[2].equals("params")){
					if(parts[3].equals("'url'")){
						return triggerExhibit.getUrl();
					}else if(parts[3].equals("'text'")) {
						return triggerExhibit.getText();
					}
				}
			}
		}
		Log.d("Interpreter","can't parse, return null");
		return null;
	}



}
