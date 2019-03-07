package game;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.Pair;

public class Game extends BasicGameState {
	
	private final static int BALL_DIAMETER = 50;
	
	private static ArrayList<Pair<Integer, Integer>> renderblocks = new ArrayList<Pair<Integer, Integer>>();
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		for(int i = 0; i < 10; i++) {
			renderblocks.add(new Pair<Integer, Integer>((int) (Math.random() * gc.getWidth()), (int) (Math.random() * gc.getHeight())));
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.setBackground(Color.white);
		
		
		
		
		g.setAntiAlias(true);
		GL11.glEnable(SGL.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_ONE);
		
		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
		g.setColor(Color.black);
		
		g.fill(new Circle(gc.getWidth() / 3, gc.getHeight() / 2, BALL_DIAMETER / 2));
		
		for (Pair pair : renderblocks) {
			g.fill(new Rectangle((int) pair.getL(), (int) pair.getR(), BALL_DIAMETER, BALL_DIAMETER));
		}
		
		g.setDrawMode(Graphics.MODE_NORMAL);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
