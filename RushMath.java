import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
public class RushMath extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	public static final int CW = 1200;		public static final int CH = CW / 2;
	GRect e1 = new GRect(0, 0, CW / 10, CH);         GRect e2 = new GRect(CH, 0, CW / 10, CH);
	GLine c1 = new GLine(CW / 4.29, 0, CW / 4.29, CH);   	  GLine c2 = new GLine(CW / 2.73, 0, CW / 2.73, CH);
	GRect Opc_9 = new GRect(CW / 6.66, CH / 50, CW / 33.33, CH / 33.33);		
	GRect Opc_6 = new GRect(CW / 3.52, CH / 50, CW / 33.33, CH / 33.33);		
	GRect Opc_3 = new GRect(CW / 2.40, CH / 50, CW / 33.33, CH / 33.33);
	GRect PUNT = new GRect(9*CW / 10, 4*CH / 5, 9*CW / 100, 4*CH / 50);
	GRect Qu = new GRect(7*CW / 10, CH / 5, 22*CW / 100, 7*CH / 100);
	//Preguntas primer nivel.
	GLabel Q1 = new GLabel("¿Cuánto es 7 x 2?", 73*CW / 100, 12*CH / 50);		
	GLabel Q2 = new GLabel("¿Cuánto es 2 x 4?", 73*CW / 100, 12*CH / 50);		
	GLabel Q3 = new GLabel("¿Cuánto es 5 x 3?", 73*CW / 100, 12*CH / 50);
	GLabel NINE = new GLabel("14");		GLabel SIX = new GLabel("8");		GLabel THREE = new GLabel("15");
	GLabel begin = new GLabel("Oprime encima del botón");
	public void run() 
	{
		//Realización del escenario.
		setSize(CW, CH);		setBackground(Color.BLACK);
		add(e1);		e1.setFilled(true);		e1.setFillColor(Color.GREEN);        		  
		add(e2);		e2.setFilled(true);		e2.setFillColor(Color.GREEN);
		//-------------------------------------------------------------------------------------------------------------------------------------------------
		add(c1);		c1.setColor(Color.GREEN);                
		add(c2);	 	c2.setColor(Color.GREEN);
		add(PUNT);		PUNT.setFilled(true);	PUNT.setFillColor(Color.GREEN);
		//Añadiendo listeners necesarios.
		addMouseListeners();
		addKeyListeners();
		//Añadiendo campo de las preguntas.
		add(Qu); 	Qu.setFilled(true);		Qu.setFillColor(Color.GREEN);
		//Añadiendo el carro.
		GPolygon car = createCar(3*CW / 10);		add(car);		car.setFilled(true);		car.setFillColor(Color.RED);
		//--------------------------------------------------------------------------------------------------------------------------------------------------
		add(Opc_9);		Opc_9.setFilled(true);		Opc_9.setFillColor(Color.GREEN);	 
		add(Opc_6);		Opc_6.setFilled(true);		Opc_6.setFillColor(Color.GREEN);
		add(Opc_3);		Opc_3.setFilled(true);		Opc_3.setFillColor(Color.GREEN);
		add(NINE, 16*CW / 100, 22*CH / 500);		add(SIX, CW / 3.40, 22*CH / 500);		add(THREE, CW / 2.34, 22*CH / 500);	 
		//GLine cy = new GLine(0, 400, 1000, 400);		add(cy);		cy.setColor(Color.GREEN);
		//GLine cx = new GLine(300, 0, 300, 500);		add(cx);		cx.setColor(Color.GREEN);
		//int score = SCORE(car, Opc_9);		add(new GLabel("SCORE: "+ score + ".", 37*CW / 40, 43*CH / 50));
		//--------------------------------------------------------------------------------------------------------------------------------------------------
		while(true) 
		{
			remove(begin);
			Opc_9.move(0, CH / 100);		NINE.move(0, CH / 100);
			Opc_6.move(0, CH / 100);		SIX.move(0, CH / 100);
			Opc_3.move(0, CH / 100);		THREE.move(0, CH / 100);
			pause(30);
			car.setFillColor(Color.RED);
			pause(30);
			car.setFillColor(Color.GREEN);
			pause(30);
			car.setFillColor(Color.BLUE);
			double a = Opc_9.getY();
			double b = car.getX();
			double c = car.getY();
			GLabel az = new GLabel("opc y: " + (a + 15));
			GLabel bz = new GLabel("car x: " + (b + 3*CW / 10));
			GLabel cz = new GLabel("car y: " + (c + 4*CH / 5));
			add(az, CW / 100, CH / 10);	add(bz, CW / 100, 7*CH / 50);		add(cz, CW / 100, 9*CH / 50);	
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
		car.addVertex(x - CW / 100, x + 10*CH / 50);
		car.addVertex(x + CW / 100, x + 10*CH / 50);
		car.addVertex(x + CW / 100, x + 12*CH / 50);
		car.addVertex(x + 3*CW / 100, x + 12*CH / 50);
		car.addVertex(x + 3*CW / 100, x + 16*CH / 50);
		car.addVertex(x + CW / 100, x + 16*CH / 50);
		car.addVertex(x + CW / 100, x + 14*CH / 50);
		car.addVertex(x - CW / 100, x + 14*CH / 50);
		car.addVertex(x - CW / 100, x + 16*CH / 50);
		car.addVertex(x - 3*CW / 100, x + 16*CH / 50);
		car.addVertex(x - 3*CW / 100, x + 12*CH / 50);
		car.addVertex(x - CW / 100, x + 12*CH / 50);
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
					car_.move(CW / 200, 0);
					break;
				case KeyEvent.VK_LEFT:
					car_.move(-CW / 200, 0); 
					break;
				case KeyEvent.VK_UP: 
					car_.move(0, -CH / 100); 
					break;
				case KeyEvent.VK_DOWN:
					car_.move(0, CH / 100); 
					break;
			}
		}
	}
	//Sistema de puntuación --- FALLIDO.
	/*public int SCORE(GObject car, GObject opc) 
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
	}*/
	//Eliminación del obstaculo al tener contacto con el carro.
	public void bye_obstacle(GPolygon car, GRect opc1, GRect opc2, GRect opc3, GLabel one, GLabel two, GLabel three)
	{
		double coorx1 = opc1.getX();		double coory1 = opc1.getY();
		double coorx2 = opc2.getX();		double coory2 = opc2.getY();
		double coorx3 = opc3.getX();		double coory3 = opc3.getY();
		double coorcarx = car.getX() + 3*CW / 10;		double coorcary = car.getY() + 4*CH / 5;
			
		double dx1 = coorcarx - coorx1;		double dy1 = coorcary - coory1;	
		double distance1 = Math.sqrt(Math.pow(dx1, 2) + Math.pow(dy1, 2));
			
		double dx2 = coorcarx - coorx2;		double dy2 = coorcary - coory2;	
		double distance2 = Math.sqrt(Math.pow(dx2, 2) + Math.pow(dy2, 2));
			
		double dx3 = coorcarx - coorx3;		double dy3 = coorcary - coory3;	
		double distance3 = Math.sqrt(Math.pow(dx3, 2) + Math.pow(dy3, 2));
			
		double DISTANCE_MAX = Math.sqrt(Math.pow(CH / 33.33, 2) + Math.pow(133.33*CW / 3333, 2));
		double DISTANCE_MIN = CH / 33.33;
			
		if(DISTANCE_MIN <= distance1 && distance1 <= DISTANCE_MAX && coory1 + DISTANCE_MIN + 3 >= coorcary)	
		{	
			remove(opc1);
			remove(one);
			remove(two);
			remove(three);
		}
		else if(DISTANCE_MIN / 33.33 <= distance2 && distance2 <= DISTANCE_MAX && coory2 + DISTANCE_MIN + 3 >= coorcary)	
		{
			remove(opc2);
			remove(one);
			remove(two);
			remove(three);
			setBackground(Color.RED);
		}
		else if(DISTANCE_MIN / 33.33 <= distance3 && distance3 <= DISTANCE_MAX && coory3 + DISTANCE_MIN + 3 >= coorcary)		
		{
			remove(opc3);
			remove(one);
			remove(two);
			remove(three);
			setBackground(Color.RED);
		}
	}
	//Impedimento al carro al intentar salir de la pista
	public void limit_car(GObject car, GRect Qu)


	{
		if(car.getX() > 17*CW / 100) 
		{
			add(car, 17*CW / 100, car.getY());
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
		else if(car.getX() < -17*CW / 100)
		{
			remove(car);
			add(car, -17*CW / 100, car.getY());
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
		else if(car.getY() < - 4*CH / 5) 
		{
			remove(car);
			add(car, car.getX(), -4*CH / 5);
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
		else if(car.getY() > 4*CH / 50) 
		{
			remove(car);
			add(car, car.getX(), 4*CH / 50);
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
	}
	//Método que cambia el orden de las posibles opciones y además cambia de pregunta.
	public void new_obstacle(GRect block1, GRect block2, GRect block3, GLabel one, GLabel two, GLabel three) 
	{
		while(block1.getY() == CH)
		{
			int order = rgen.nextInt(1, 6);
			remove(block1);		remove(block2);		remove(block3);
			remove(one);		remove(two);		remove(three);
			remove(Q1);		remove(Q2);		remove(Q3);	
			add(block1, CW / 6.66, CH / 50);		add(block2, CW / 3.52, CH / 50);		add(block3, CW / 2.40, CH / 50);
			switch(order) 
			{
				case 1:	
					add(one, 16*CW / 100, 22*CH / 500);
					add(two, CW / 3.40, 22*CH / 500);
					add(three, CW / 2.34, 22*CH / 500);
					add(Q1);		
					break;		
				case 2:	
					add(two, 16*CW / 100, 22*CH / 500);
					add(three, CW / 3.40, 22*CH / 500);
					add(one, CW / 2.34, 22*CH / 500);
					add(Q1);
					break;	
				case 3:	
					add(three, 16*CW / 100, 22*CH / 500);
					add(one, CW / 3.40, 22*CH / 500);
					add(two, CW / 2.34, 22*CH / 500);
					add(Q2);
					break;	
				case 4: 
					add(one, 16*CW / 100, 22*CH / 500);
					add(three, CW / 3.40, 22*CH / 500);
					add(two, CW / 2.34, 22*CH / 500);
					add(Q2);
					break;	
				case 5: 
					add(two, 16*CW / 100, 22*CH / 500);
					add(one, CW / 3.40, 22*CH / 500);
					add(three, CW / 2.34, 22*CH / 500);	
					add(Q3);
					break;
				case 6: 
					add(three, 16*CW / 100, 22*CH / 500);
					add(two, CW / 3.40, 22*CH / 500);
					add(one, CW / 2.34, 22*CH / 500);
					add(Q3);		
					break;
			}
			setBackground(Color.BLACK);
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------
	private GObject car_;
	private GPoint P;
	private static RandomGenerator rgen = RandomGenerator.getInstance();
}