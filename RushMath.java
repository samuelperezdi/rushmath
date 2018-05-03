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
	GRect Opc_9 = new GRect(150, 10, 30, 15);			 
	GRect Opc_6 = new GRect(283.33, 10, 30, 15);	
	GRect Opc_3 = new GRect(416.33, 10, 30, 15);	
	GRect PUNT = new GRect(900, 400, 90, 40);
	GRect Qu = new GRect(700, 80, 220, 35);
	GLabel Q = new GLabel("¿Cuánto es 3 x 3?");		
	
	public void run() 
	{
		//Realización del escenario.
		setSize(1000, 500);
		setBackground(Color.BLACK);
		
		add(e1);                                      add(e2);
		e1.setFilled(true);                           e2.setFilled(true);
		e1.setFillColor(Color.GREEN);                 e2.setFillColor(Color.GREEN);
		//----------------------------------------------------------------------------------------
		add(c1); 									  add(c2);	 
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
		add(Q, 750, 100);
		//---------------------------------------------------------------------------------------
		//Añadiendo el carro.
		GPolygon car = createCar();
		add(car);
		car.setFilled(true);
		car.setFillColor(Color.RED);
		//---------------------------------------------------------------------------------------
		add(Opc_9);		Opc_9.setFilled(true);		Opc_9.setFillColor(Color.GREEN);	 
		add(Opc_6);		Opc_6.setFilled(true);		Opc_6.setFillColor(Color.GREEN);
		add(Opc_3);		Opc_3.setFilled(true);		Opc_3.setFillColor(Color.GREEN);
		int score = SCORE(car, Opc_9);
		add(new GLabel("SCORE: "+ score + ".", 925, 430));
		while(true)
		{
			for(int i = 1; i <= 200; i++) 
			{
				Opc_9.move(0, 2);
				Opc_6.move(0, 2);
				Opc_3.move(0, 2);
				pause(50);
				car.setFillColor(Color.RED);
				pause(50);
				car.setFillColor(Color.GREEN);
				pause(50);
				car.setFillColor(Color.BLUE);
				double a = Opc_9.getY();
				double b = car.getY();
				add(new GLabel("" + a + "----" + b, 20, 20));
			}
		}
		
	}
	//Realización del carro.
	public GPolygon createCar() 
	{
		GPolygon car = new GPolygon();
		car.addVertex(290, 400);
		car.addVertex(310, 400);
		car.addVertex(310, 420);
		car.addVertex(330, 420);
		car.addVertex(330, 460);
		car.addVertex(310, 460);
		car.addVertex(310, 440);
		car.addVertex(290, 440);
		car.addVertex(290, 460);
		car.addVertex(270, 460);
		car.addVertex(270, 420);
		car.addVertex(290, 420);
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
			}
		}
	}
	private GObject car_;
	private GPoint P;
	/*public void mouseEnterd(MouseEvent e) 
	{
		P = new GPoint(e.getPoint());
		car_ = getElementAt(P);
	}*/
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
}