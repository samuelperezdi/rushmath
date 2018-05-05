import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
public class RushMath extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	GRect e1 = new GRect(0, 0, 100, 500);         GRect e2 = new GRect(500, 0, 100, 500);
	GRect c1 = new GRect(233, 0, 2, 500);   	  GRect c2 = new GRect(366.3, 0, 2, 500);
	GRect Opc_9 = new GRect(150, 10, 30, 15);		GRect Opc_6 = new GRect(283.33, 10, 30, 15);		GRect Opc_3 = new GRect(416.33, 10, 30, 15);	
	GRect PUNT = new GRect(900, 400, 90, 40);
	GRect Qu = new GRect(700, 100, 220, 35);
	GLabel Q = new GLabel("¿Cuánto es 3 x 3?");	
	GLabel NINE = new GLabel("9");
	GLabel SIX = new GLabel("6");
	GLabel THREE = new GLabel("3");
	public void run() 
	{
		//Realización del escenario.
		setSize(1000, 500);
		setBackground(Color.BLACK);
		add(e1);                                      add(e2);
		e1.setFilled(true);                           e2.setFilled(true);
		e1.setFillColor(Color.GREEN);                 e2.setFillColor(Color.GREEN);
		//-------------------------------------------------------------------------------------------------------------------------------------------------
		add(c1); 			           				  add(c2);	 
		c1.setFilled(true);						      c2.setFilled(true);
		c1.setFillColor(Color.GREEN);                 c2.setFillColor(Color.GREEN);
		add(PUNT);
		PUNT.setFilled(true);
		PUNT.setFillColor(Color.GREEN);
		//Añadiendo listeners necesarios.
		addMouseListeners();
		addKeyListeners();
		//-------------------------------------------------------------------------------------------------------------------------------------------------
		add(Qu); 	Qu.setFilled(true);		Qu.setFillColor(Color.GREEN);
		add(Q, 730, 120);
		//Añadiendo el carro.
		GPolygon car = createCar(300);
		add(car);
		car.setFilled(true);
		car.setFillColor(Color.RED);
		//--------------------------------------------------------------------------------------------------------------------------------------------------
		add(Opc_9);		Opc_9.setFilled(true);		Opc_9.setFillColor(Color.GREEN);	 
		add(Opc_6);		Opc_6.setFilled(true);		Opc_6.setFillColor(Color.GREEN);
		add(Opc_3);		Opc_3.setFilled(true);		Opc_3.setFillColor(Color.GREEN);
		add(NINE, 160, 22);		
		add(SIX, 293.33, 22);		
		add(THREE, 426.33, 22);	 
		//GLine cy = new GLine(0, 400, 1000, 400);		add(cy);		cy.setColor(Color.GREEN);
		//GLine cx = new GLine(300, 0, 300, 500);		add(cx);		cx.setColor(Color.GREEN);
		int score = SCORE(car, Opc_9);	
		add(new GLabel("SCORE: "+ score + ".", 925, 430));
		//--------------------------------------------------------------------------------------------------------------------------------------------------
		while(true) 
		{
			Opc_9.move(0, 5);		NINE.move(0, 5);
			Opc_6.move(0, 5);		SIX.move(0, 5);
			Opc_3.move(0, 5);		THREE.move(0, 5);
			pause(30);
			car.setFillColor(Color.RED);
			pause(30);
			car.setFillColor(Color.GREEN);
			pause(30);
			car.setFillColor(Color.BLUE);
			double a = Opc_9.getY();
			double b = car.getX();
			double c = car.getY();
			GLabel az = new GLabel("opc y: " + a);
			GLabel bz = new GLabel("car x: " + (b + 300));
			GLabel cz = new GLabel("car y: " + (c + 400));
			//add(az, 10, 50);	add(bz, 10, 70);		add(cz, 10, 90);	
			pause(15);
			remove(az);		remove(bz);		remove(cz);	
			bye_obstacle(car,Opc_9, Opc_6, Opc_3, NINE, SIX, THREE);
			limit_car(car, Qu);
			new_obstacle(Opc_9, Opc_6, Opc_3, NINE, SIX, THREE);
		}
	}
	//Realización del carro.
	public GPolygon createCar(int x) 
	{
		GPolygon car = new GPolygon();
		car.addVertex(x - 10, x + 100);
		car.addVertex(x + 10, x + 100);
		car.addVertex(x + 10, x + 120);
		car.addVertex(x + 30, x + 120);
		car.addVertex(x + 30, x + 160);
		car.addVertex(x + 10, x + 160);
		car.addVertex(x + 10, x + 140);
		car.addVertex(x - 10, x + 140);
		car.addVertex(x - 10, x + 160);
		car.addVertex(x - 30, x + 160);
		car.addVertex(x - 30, x + 120);
		car.addVertex(x - 10, x + 120);
		return car;
	}
	//Movimiento del carro con las flechas después de un click encima de este.
	public void mousePressed(MouseEvent e) 
	{
		P = new GPoint(e.getPoint());
		car_ = getElementAt(P);
	}
	public void keyPressed(KeyEvent f) 
	{
		if(car_ != null)
		{
			switch(f.getKeyCode())
			{
				case KeyEvent.VK_RIGHT: 
					car_.move(5, 0);
					break;
				case KeyEvent.VK_LEFT:
					car_.move(-5, 0); 
					break;
				case KeyEvent.VK_UP: 
					car_.move(0, -5); 
					break;
				case KeyEvent.VK_DOWN:
					car_.move(0, 5); 
					break;
			}
		}
	}
	//Sistema de puntuación --- FALLIDO.
	public int SCORE(GObject car, GObject opc) 
	{
		int score = 0;
		double xopc = opc.getX();		double xcar = car.getX();
		double yopc = opc.getY();		double ycar = car.getY();
		double dx = xcar - xopc;		double dy = ycar - yopc;
		double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if(distance <= 50)
		{
			score = score + 1;
		}
		return score;
	}
	//Eliminación del obstaculo al tener contacto con el carro.
	public void bye_obstacle(GPolygon car, GRect opc1, GRect opc2, GRect opc3, GLabel one, GLabel two, GLabel three)
	{
		double coorx1 = opc1.getX();		double coory1 = opc1.getY();
		double coorx2 = opc2.getX();		double coory2 = opc2.getY();
		double coorx3 = opc3.getX();		double coory3 = opc3.getY();
		double coorcarx = car.getX() + 300;		double coorcary = car.getY() + 400;
		
		double dx1 = coorcarx - coorx1;		double dy1 = coorcary - coory1;	
		double distance1 = Math.sqrt(Math.pow(dx1, 2) + Math.pow(dy1, 2));
		
		double dx2 = coorcarx - coorx2;		double dy2 = coorcary - coory2;	
		double distance2 = Math.sqrt(Math.pow(dx2, 2) + Math.pow(dy2, 2));
		
		double dx3 = coorcarx - coorx3;		double dy3 = coorcary - coory3;	
		double distance3 = Math.sqrt(Math.pow(dx3, 2) + Math.pow(dy3, 2));
		
		if(15 <= distance1 && distance1 <= 42.72 && coory1 + 15 == coorcary)	
		{	
			remove(opc1);
			remove(one);
		}
		else if(15 <= distance2 && distance2 <= 42.72 && coory2 + 15 == coorcary)	
		{
			remove(opc2);
			remove(two);
			setBackground(Color.RED);
		}
		else if(15 <= distance3 && distance3 <= 42.72 && coory3 + 15 == coorcary)		
		{
			remove(opc3);
			remove(three);
			setBackground(Color.RED);
		}
	}
	//Impedimento al carro al intentar salir de la pista
	public void limit_car(GObject car, GRect Qu)


	{
		if(car.getX() > 170)
		{
			add(car, 170, car.getY());
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
		else if(car.getX() < -170)
		{
			remove(car);
			add(car, -170, car.getY());
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
		else if(car.getY() < -400)
		{
			remove(car);
			add(car, car.getX(), -400);
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
		else if(car.getY() > 40)
		{
			remove(car);
			add(car, car.getX(), 40);
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
	}
	public void new_obstacle(GRect block1, GRect block2, GRect block3, GLabel one, GLabel two, GLabel three) 
	{
		if(block1.getY() == 500)
		{
			remove(block1);		remove(block2);		remove(block3);
			remove(one);		remove(two);		remove(three);
			add(block1, 150, 10);		add(block2, 283.33, 10);		add(block3, 416.33, 10);
			add(one, 160, 22);		add(two, 293.33, 22);		add(three, 426.33, 22);
			setBackground(Color.BLACK);
		}
	}
	public void ramdom_question()
	{
		int order = rgen.nextInt(1, 3);
		switch(order) 
		{
			case 1:	
			case 2:
			case 3:
		}
	}
	private GObject car_;
	private GPoint P;
	private static RandomGenerator rgen = RandomGenerator.getInstance();
}