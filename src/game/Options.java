package game;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Options extends BasicGameState implements KeyListener, InputProviderListener{

	// ID we return to class 'Application'
	public static final int ID = 4;
	
	Image backgroundimage;
	
	protected static HashMap<Integer, String> keybindings = new HashMap<Integer, String>();

	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundimage = new Image("res/options/optionsbackground.jpg");
		
		Options.keybindings.put(Input.KEY_W, "up");
		Options.keybindings.put(Input.KEY_S, "down");
		Options.keybindings.put(Input.KEY_A, "left");
		Options.keybindings.put(Input.KEY_D, "right");
	}
	
	// enter-method for starting things when state entered
		@Override
		public void enter(GameContainer gc, StateBasedGame sbg) {
			InputProvider provider = new InputProvider(gc.getInput());
			provider.addListener(this);
			
			for (Integer key : Options.keybindings.keySet()) {
				provider.bindCommand(new KeyControl(key), new BasicCommand(Options.keybindings.get(key)));
			}
		}
	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// scale and draw the menu background
		backgroundimage.getScaledCopy(gc.getWidth(), gc.getHeight()).draw(0,0);
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		
	}

	// Returning 'ID' from class 'Options'
	@Override
	public int getID() {
		return Options.ID;
	}
	
	@Override
	public void controlPressed(Command arg0) {
		// TODO Auto-generated method stub
		String commandstring = arg0.toString();
		switch (commandstring) {
		}
	}

	@Override
	public void controlReleased(Command arg0) {
		// TODO Auto-generated method stub
		String commandstring = arg0.toString();
		switch (commandstring) {
		}
	}
}
