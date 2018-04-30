import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
public class first extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;
	public void run() 
	{
		//Realización del escenario.
		setSize(1000, 500);
		GRect e1 = new GRect(0, 0, 100, 500);         GRect e2 = new GRect(500, 0, 100, 500);
		add(e1);                                      add(e2);
		e1.setFilled(true);                           e2.setFilled(true);
		e1.setFillColor(Color.GREEN);                 e2.setFillColor(Color.GREEN);
		
		GLine c1 = new GLine(233.3, 0, 233.3, 500);   GLine c2 = new GLine(366.6, 0, 366.6, 500);
		add(c1); 									  add(c2);	 
		
		//Añadiendo los listeners y botón.
		add(new JButton ("RUSHMATH!"), 750, 20);
		addActionListeners();
		addKeyListeners();
		addMouseListeners();
		
		GPolygon car = createCar();
		add(car);
		car.setFilled(true);  
		for(int j = 1; j < 400; j++)
		{
			car.setFillColor(Color.RED);
			pause(600);
			car.setFillColor(Color.BLUE);
			pause(600);
			car.setFillColor(Color.GREEN);
			pause(600);
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
	//Inicio del juego.
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("RUSHMATH!")) 
		{
			GLabel Inst = new GLabel("Oprime encima de la nave para empezar el juego", 675, 80);
			add(Inst);
			GLabel Q = new GLabel("¿Cuánto es 3 x 3?", 750, 100);
			add(Q);
			GRect Opc_9 = new GRect(150, 10, 30, 15);			add(Opc_9);			 
			GRect Opc_6 = new GRect(283.3, 10, 30, 15); 		add(Opc_6);
			GRect Opc_3 = new GRect(416.6, 10, 30, 15);			add(Opc_3);
			pause(2000);
			for(int i = 1; i <= 10; i++) 
			{
				Opc_9.move(0, i);	Opc_6.move(0, i);	Opc_3.move(0, i);
				pause(50);
			}
		}
	}
	//Movimiento del ca con las flechas después de un click encima de este.
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
}