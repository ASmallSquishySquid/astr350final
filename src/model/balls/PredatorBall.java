package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import model.adapters.IModel2BallAdapter;
import model.visitors.algos.AConfigBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;

/**
 * The predator ball object.
 * 
 * @author Phoebe Scaccia
 */
public class PredatorBall extends ABall {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 2255030234231260530L;
	/**
	 * The identifying host ID for this class.
	 */
	public static final IBallHostID id = BallHostIDFactory.Singleton.makeID(PredatorBall.class.getName());

	/**
	 * Constructor for a new DefaultBall.
	 * 
	 * @param p the ABall's position.
	 * @param r the ABall's radius.
	 * @param v the ABall's velocity.
	 * @param c the ABall's color.
	 * @param container the ABall's container.
	 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
	 * @param modelAdapter The adapter to the model this ball is used in
	 */
	public PredatorBall(Point p, int r, Point v, Color c, Component container, AConfigBallAlgo installAlgo,
			IModel2BallAdapter modelAdapter) {
		super(id, p, r, v, c, container, installAlgo, modelAdapter);
	}
	

}