package game;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.Pair;

public class Game extends BasicGameState implements InputProviderListener {
	
	private static ArrayList<Pair<Pair<Integer, Integer>, Boolean>> roadtiles = new ArrayList<Pair<Pair<Integer, Integer>, Boolean>>();
	
	private float tilewidth = 100;
	private float carx = 0;
	private float cary = 0;
	private float cardirection = 0;
	private float carangle = 0;
	private float carspeed = 0;
	private float caracceleration = 0;
	private Image carimage;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		carimage = new Image("/res/game/car.png");
		carimage = carimage.getScaledCopy(0.1f);
		carimage.setCenterOfRotation(carimage.getWidth() / 2.0f, carimage.getHeight() / 2.0f);
		Random rand = new Random(System.nanoTime());
		for(int x = 0; x <= 10; x++) {
			for(int y = 0; y <= 10; y++) {
				roadtiles.add(new Pair<Pair<Integer, Integer>, Boolean>(new Pair<Integer, Integer>(x, y), rand.nextBoolean()));
			}
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		InputProvider provider = new InputProvider(gc.getInput());
		provider.addListener(this);
		
		for (String key : Options.keybindings.keySet()) {
			provider.bindCommand(new KeyControl(Options.keybindings.get(key)), new BasicCommand(key));
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.setBackground(Color.white);
				
		g.setAntiAlias(true);
		GL11.glEnable(SGL.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_ONE);
		
		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
		g.setColor(Color.black);
		
		for (Pair<Pair<Integer, Integer>, Boolean> pair : roadtiles) {
			if (pair.getR()) {
				float tilex = pair.getL().getL() * tilewidth;
				float tiley = pair.getL().getR() * tilewidth;
				tilex -= carx + gc.getWidth() / 2.0f;
				tiley -= cary + gc.getWidth() / 2.0f;
				g.fill(new Rectangle(tilex, tiley, tilewidth, tilewidth));
			}
		}
		g.setDrawMode(Graphics.MODE_NORMAL);
		g.setColor(Color.transparent);
		carimage.setRotation(carangle);
		g.drawImage(carimage, gc.getWidth() / 2.0f - carimage.getWidth() / 2.0f, gc.getHeight() / 2.0f - carimage.getHeight() / 2.0f);
		
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		carx += carspeed * Math.sin(Math.toRadians(carangle));
		cary -= carspeed * Math.cos(Math.toRadians(carangle));
		carspeed += caracceleration;
		carangle -= cardirection;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	@Override
	public void controlPressed(Command arg0) {
		// TODO Auto-generated method stub
		String commandstring = arg0.toString();
		switch (commandstring) {
			case "[Command=up]":
				caracceleration += 0.1;
				break;
			case "[Command=down]":
				caracceleration -= 0.1;
				break;
			case "[Command=left]":
				cardirection += 4;
				break;
			case "[Command=right]":
				cardirection -= 4;
				break;
		}
	}

	@Override
	public void controlReleased(Command arg0) {
		// TODO Auto-generated method stub
		String commandstring = arg0.toString();
		switch (commandstring) {
			case "[Command=up]":
				caracceleration -= 0.1;
				break;
			case "[Command=down]":
				caracceleration += 0.1;
				break;
			case "[Command=left]":
				cardirection -= 4;
				break;
			case "[Command=right]":
				cardirection += 4;
				break;
		}
	}
}
