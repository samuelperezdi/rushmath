import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
public class RushMath extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;
	//-------------------------------------------------------------------------------------------
	//private static String mov = "";
	GRect e1 = new GRect(0, 0, 100, 500);         GRect e2 = new GRect(500, 0, 100, 500);
	GRect c1 = new GRect(233, 0, 2, 500);   	  GRect c2 = new GRect(366.3, 0, 2, 500);
	GRect Opc_9 = new GRect(150, 10, 30, 15);		GRect Opc_6 = new GRect(283.33, 10, 30, 15);		GRect Opc_3 = new GRect(416.33, 10, 30, 15);	
	GRect PUNT = new GRect(900, 400, 90, 40);
	GRect Qu = new GRect(700, 100, 220, 35);
	GLabel Q = new GLabel("¿Cuánto es 3 x 3?");	
	public void run() 
	{
		//Realización del escenario.
		setSize(1000, 500);
		setBackground(Color.BLACK);
		
		add(e1);                                      add(e2);
		e1.setFilled(true);                           e2.setFilled(true);
		e1.setFillColor(Color.GREEN);                 e2.setFillColor(Color.GREEN);
		//--------------------------------------------------------------------------------------
		add(c1); 			           				  add(c2);	 
		c1.setFilled(true);						      c2.setFilled(true);
		c1.setFillColor(Color.GREEN);                 c2.setFillColor(Color.GREEN);
		add(PUNT);
		PUNT.setFilled(true);
		PUNT.setFillColor(Color.GREEN);
		//Añadiendo los listeners y botón.
		//add(new JButton ("RUSHMATH!"), 750, 20);
		//addActionListeners();
		addMouseListeners();
		addKeyListeners();
		//---------------------------------------------------------------------------------------
		add(Qu); 	Qu.setFilled(true);		Qu.setFillColor(Color.GREEN);
		add(Q, 730, 120);
		//Añadiendo el carro.
		GPolygon car = createCar(300);
		add(car);
		car.setFilled(true);
		car.setFillColor(Color.RED);
		//---------------------------------------------------------------------------------------
		add(Opc_9);		Opc_9.setFilled(true);		Opc_9.setFillColor(Color.GREEN);	 
		add(Opc_6);		Opc_6.setFilled(true);		Opc_6.setFillColor(Color.GREEN);
		add(Opc_3);		Opc_3.setFilled(true);		Opc_3.setFillColor(Color.GREEN);
		int score = SCORE(car, Opc_9);
		add(new GLabel("SCORE: "+ score + ".", 925, 430));
		//---------------------------------------------------------------------------------------------------
		for(int i = 1; i <= 200; i++) 
		{
			Opc_9.move(0, 2);
			Opc_6.move(0, 2);
			Opc_3.move(0, 2);
			pause(30);
			car.setFillColor(Color.RED);
			pause(30);
			car.setFillColor(Color.GREEN);
			pause(30);
			car.setFillColor(Color.BLUE);
			double a = Opc_9.getY();
			double b = car.getX();
			double c = car.getY();
			GLabel coors =new GLabel("" + a + "-" + b + "-" + c);
			add(coors, 10, 50);
			pause(15);
			remove(coors);
			bye_obstacle(car,Opc_9, Opc_6, Opc_3);
			limit_car(car, Qu);
		}
		add(new GLabel("" + Opc_9.getY() + "-" + car.getX() + "-" + car.getY()), 10, 201);
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
	/*Inicio del juego.
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("RUSHMATH!")) 
		{
			GRect Qu = new GRect(700, 80, 220, 35);		add(Qu); 	Qu.setFilled(true);		Qu.setFillColor(Color.GREEN);
			GLabel Q = new GLabel("¿Cuánto es 3 x 3?");		add(Q, 750, 100);
			add(Opc_9);		Opc_9.setFilled(true);		Opc_9.setFillColor(Color.GREEN);	 
			add(Opc_6);		Opc_6.setFilled(true);		Opc_6.setFillColor(Color.GREEN);
			add(Opc_3);		Opc_3.setFilled(true);		Opc_3.setFillColor(Color.GREEN);
			movandcolor(createCar());
		}
	}*/
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
	private GObject car_;
	private GPoint P;
	/*public void keyPressed(KeyEvent f) 
	{
		if(f.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			mov = "R";
		}
		else if(f.getKeyCode() == KeyEvent.VK_LEFT)
		{
			mov = "L";
		}
	}*/
	/*public void keyReleased(KeyEvent f)

	{
		mov = "stop";
	}*/
	//Sistema de puntuación.
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
	public void bye_obstacle(GPolygon car, GRect opc1, GRect opc2, GRect opc3)
	{
		if(opc1.getY() == 386 && -160 <= car.getX() && car.getX() <= -110)	remove(opc1);
		else if(opc2.getY() == 386 && -10 <= car.getX() && car.getX() <= 10)	 remove(opc2);
		else if(opc3.getY() == 386 && 106.33 <= car.getX() && car.getX() <= 156.33)		remove(opc3);
	}
	//Impedimento al carro al intentar salir de la pista
	public void limit_car(GObject car, GRect Qu)
	{
		if(car.getX() > 170)
		{
			remove(car);
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
			add(car, car.getX(), 30);
			Qu.setFillColor(Color.RED);
			pause(100);
			Qu.setFillColor(Color.GREEN);
		}
	}
}