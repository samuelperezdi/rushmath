import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
public class RushMath extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;
	public void run() 
	{
		//Realización del escenario.
		setSize(1000, 500);
		setBackground(Color.BLACK);
		
		GRect e1 = new GRect(0, 0, 100, 500);         GRect e2 = new GRect(500, 0, 100, 500);
		add(e1);                                      add(e2);
		e1.setFilled(true);                           e2.setFilled(true);
		e1.setFillColor(Color.GREEN);                 e2.setFillColor(Color.GREEN);
		//----------------------------------------------------------------------------------------
		GRect c1 = new GRect(233, 0, 2, 500);   	  GRect c2 = new GRect(366.3, 0, 2, 500);
		add(c1); 									  add(c2);	 
		c1.setFilled(true);						      c2.setFilled(true);
		c1.setFillColor(Color.GREEN);                 c2.setFillColor(Color.GREEN);
		//--------------Añadiendo los listeners y botón.
		add(new JButton ("RUSHMATH!"), 750, 20);
		addActionListeners();
		addKeyListeners();
		addMouseListeners();
		//---------------------------Añadiendo el carro.
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
			GRect Qu = new GRect(700, 80, 220, 35);		add(Qu); 	Qu.setFilled(true);		Qu.setFillColor(Color.GREEN);
			GLabel Q = new GLabel("¿Cuánto es 3 x 3?");		add(Q, 750, 100);
			GRect Opc_9 = new GRect(150, 10, 30, 15);			add(Opc_9);		Opc_9.setFilled(true);		Opc_9.setFillColor(Color.GREEN);	 
			GRect Opc_6 = new GRect(283.33, 10, 30, 15);		add(Opc_6);		Opc_6.setFilled(true);		Opc_6.setFillColor(Color.GREEN);
			GRect Opc_3 = new GRect(416.33, 10, 30, 15);		add(Opc_3);		Opc_3.setFilled(true);		Opc_3.setFillColor(Color.GREEN);
			//mov_opc(Opc_9);		mov_opc(Opc_6);		mov_opc(Opc_3);
		}
	}
	/*GPolygon car = createCar();
	public void keyPressed(KeyEvent f)
	{
		switch(f.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				car.move(5, 0);
				break;
			case KeyEvent.VK_LEFT:
				car.move(-5, 0);
				break;
		}
	}*/
	/*public void mov_opc(GObject opc)
	{
		pause(300);
		for(int i = 1; i <= 50; i++)
		{
			opc.move(0, 3);
		}
	}*/
	//Movimiento del carro con las flechas después de un click encima de este.
	public void mouseEnterd(MouseEvent e) 
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