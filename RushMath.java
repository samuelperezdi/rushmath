import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
public class HelloWorld extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;
	public void run() 
	{
		createStage();
		
		while(true) 
		{
			block1.move(0, CH / 100);		block2.move(0, CH / 100);		block3.move(0, CH / 100);	
			ansL.move(0, CH / 100);		ansN.move(0, CH / 100);		ansM.move(0, CH / 100);
			pause(30);
			car.setFillColor(Color.RED);
			pause(30);
			car.setFillColor(Color.GREEN);
			pause(30);
			car.setFillColor(Color.BLUE);
			ByeObstacle(car, block1, block2, block3);
			NewObstacle(block1, block2, block3);	
			
		}
	}
	
	
	//Making of the stage.
	public void createStage()
	{
		setSize(CW, CH);		setBackground(Color.BLACK);
		//-------------------------------------------------------------------------------------------------------------------------------------------------
		add(e1);		e1.setFilled(true);		e1.setFillColor(Color.GREEN);        		  
		add(e2);		e2.setFilled(true);		e2.setFillColor(Color.GREEN);
		add(c1);		c1.setColor(Color.GREEN);                
		add(c2);	 	c2.setColor(Color.GREEN);
		//Añadiendo campo de puntuación.
		add(PUNT);		PUNT.setFilled(true);	PUNT.setFillColor(Color.GREEN);
		add(SCORE);
		//Añadiendo listeners necesarios.
		addMouseListeners();
		addKeyListeners();
		//Añadiendo campo de las preguntas.
		add(Qu); 	Qu.setFilled(true);		Qu.setFillColor(Color.GREEN);
		//Añadiendo el carro.
		car = createCar(3*CW / 10);		add(car);		car.setFilled(true);		car.setFillColor(Color.RED);
		//------------------------------------------------------------------------------------------------------------------------------------------------
		add(block1);		block1.setFilled(true);		block1.setFillColor(Color.GREEN);	 
		add(block2);		block2.setFilled(true);		block2.setFillColor(Color.GREEN);
		add(block3);		block3.setFilled(true);		block3.setFillColor(Color.GREEN);
		//Primer pregunta, es una suma.
		add(question, 73*CW / 100, 12*CH / 50);
	}
	
	
	//Making of the car.
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
	
	
	//Movement of the car with the arrows after a click on it, limits of the track.
	public void mousePressed(MouseEvent e) 
	{
		P = new GPoint(e.getPoint());
		car_ = getElementAt(P);
	}
	
	
	public void keyPressed(KeyEvent f) 
	{
		if(car_ != null)
		{
			if(f.getKeyCode() == KeyEvent.VK_RIGHT && car_.getX() < 17*CW / 100)
			{
				car_.move(CW / 200, 0);
			}
			if(f.getKeyCode() == KeyEvent.VK_LEFT && car_.getX() > -17*CW / 100)
			{
				car_.move(-CW / 200, 0);
			}
			if(f.getKeyCode() == KeyEvent.VK_UP && car_.getY() > -4*CH / 5)
			{
				car_.move(0, -CH / 100);
			}
			if(f.getKeyCode() == KeyEvent.VK_DOWN && car_.getY() < 4*CH / 50)
			{
				car_.move(0, CH / 100);
			}
			
		}
	}
	
	
	//Elimination of the obstacle after contact with the car.
	//If incorrect answer, game over.
	public void ByeObstacle(GPolygon car, GRect block1, GRect block2, GRect block3)
	{
		double coorx1 = block1.getX();		double coory1 = block1.getY();
		double coorx2 = block2.getX();		double coory2 = block2.getY();
		double coorx3 = block3.getX();		double coory3 = block3.getY();
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
			remove(block1);		
			if(c == 1)
				{
				ScorePlus();
				ansL.setVisible(false);
				}
			else if(c == 2)		
			{	
				ansM.setVisible(false);	
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				add(new GLabel("G A M E  O V E R", CW / 2, CH /2));
			}
			else if(c == 3)	
			{  
				ansN.setVisible(false);	
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				add(new GLabel("G A M E  O V E R", CW / 2, CH /2));
			}
		}
		else if(DISTANCE_MIN / 33.33 <= distance2 && distance2 <= DISTANCE_MAX && coory2 + DISTANCE_MIN + 3 >= coorcary)	
		{
			remove(block2);		
			if(c == 1)		
			{	
				ansM.setVisible(false);	
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				add(new GLabel("G A M E  O V E R", CW / 2, CH /2));
			}
			else if(c == 2)
				{
				ScorePlus();
				ansL.setVisible(false);
				}
			else if(c == 3)		
			{	
				ansM.setVisible(false);	
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				add(new GLabel("G A M E  O V E R", CW / 2, CH /2));
			}
		}
		else if(DISTANCE_MIN / 33.33 <= distance3 && distance3 <= DISTANCE_MAX && coory3 + DISTANCE_MIN + 3 >= coorcary)		
		{
			remove(block3);		
			if(c == 1)		
			{	
				ansN.setVisible(false);	
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				add(new GLabel("G A M E  O V E R", CW / 2, CH /2));
			}
			else if(c == 2)		
			{	
				ansN.setVisible(false);	
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				add(new GLabel("G A M E  O V E R", CW / 2, CH /2));
			}
			else if(c == 3)		
				{
				ScorePlus();
				ansL.setVisible(false);
				}
		}
		
	}
	
	
	//Method that changes the order of the possible options and also changes the question.
	//Generation of new obstacles, new question when a cycle ends.
	public void NewObstacle(GRect block1, GRect block2, GRect block3) 
	{
		if(block1.getY() > CH)
		{
			block1.setVisible(true);		block2.setVisible(true);		block3.setVisible(true);
			remove(ansL);		remove(ansM);		remove(ansN);
			add(block1, CW / 6.66, 0);	add(block2, CW / 3.52, 0);		add(block3, CW / 2.40, 0);	
			remove(question);
			RandomOP();
		}
	}
	
	
	//Generates random additions of two numbers between 0 and 20.
	private void GeneAD()
	{
		int a = rgen.nextInt(0,20);		int b = rgen.nextInt(0, 20);
		c = rgen.nextInt(1, 3); 	
		int d = rgen.nextInt(a, b);     int e = rgen.nextInt(a, b);
		question = new GLabel("¿Cuánto es " + a + " + " + b + "?");
		add(question, 73*CW / 100, 12*CH / 50);
		ansL = new GLabel(Integer.toString(a + b));
		ansM = new GLabel(Integer.toString((a + b) - d));
		ansN = new GLabel(Integer.toString((a + b) + e));
		if(c == 1) 
		{
			add(ansL, 16*CW / 100, 3*CH / 125);
			add(ansM, CW / 3.40, 3*CH / 125);
			add(ansN, CW / 2.34, 3*CH / 125);
		}
		else if(c == 2) 
		{
			add(ansM, 16*CW / 100, 3*CH / 125);
			add(ansL, CW / 3.40, 3*CH / 125);
			add(ansN, CW / 2.34, 3*CH / 125);
		} 
		else if(c == 3) 
		{
			add(ansN, 16*CW / 100, 3*CH / 125);
			add(ansM, CW / 3.40, 3*CH / 125);
			add(ansL, CW / 2.34, 3*CH / 125);
		} 
	}
	
	
	//Generates random subtraction of two numbers between 0 and 20.
	private void GeneSUS()
	{
		int a = rgen.nextInt(0,20);		int b = rgen.nextInt(0, 20);
		c = rgen.nextInt(1, 3); 	
		int d = rgen.nextInt(a, b);     int e = rgen.nextInt(a, b);
		question = new GLabel("¿Cuánto es " + a + " - " + b + "?");
		add(question, 73*CW / 100, 12*CH / 50);
		ansL = new GLabel(Integer.toString(a - b));
		ansM = new GLabel(Integer.toString((a - b) + d));
		ansN = new GLabel(Integer.toString((a - b) - e));
		if(c == 1) 
		{
			add(ansL, 16*CW / 100, 3*CH / 125);
			add(ansM, CW / 3.40, 3*CH / 125);
			add(ansN, CW / 2.34, 3*CH / 125);
		}
		else if(c == 2) 
		{
			add(ansM, 16*CW / 100, 3*CH / 125);
			add(ansL, CW / 3.40, 3*CH / 125);
			add(ansN, CW / 2.34, 3*CH / 125);
		} 
		else if(c == 3) 
		{
			add(ansN, 16*CW / 100, 3*CH / 125);
			add(ansM, CW / 3.40, 3*CH / 125);
			add(ansL, CW / 2.34, 3*CH / 125);
		} 
	}
	
	
	//Subtraction or addition with a random boolean.
	//With GeneAD and GeneSUS, a random operation is generated.
	private void RandomOP()
	{
		boolean DETER = rgen.nextBoolean();
				if(DETER == true)
					{
						GeneAD();
					}
				else
					{
						GeneSUS();
					}
	}
	
	
	/*private String RandomCongrats()
	{
		int VAR = rgen.nextInt(1, 4);
		String Congrats;
		switch(VAR)
		{
		case 1: VAR = 1;
				Congrats = "Congrats!";
				break;
		case 2: VAR = 2;
				Congrats = "That's the answer!";
				break;
		case 3: VAR = 3;
				Congrats = "That's right!";
				break;
		default: Congrats = "Yey!";
		}
		
		return Congrats;
	}*/
	
	
	//Increase the score by choosing the correct answer.
	public void ScorePlus()
	{
		if(ansL.isVisible())
		{
			remove(SCORE);
			score++;
			Score = Integer.toString(score);
			SCORE = new GLabel("SCORE: " + score, 23*CW / 25, 42*CH / 50);
			add(SCORE);
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	GRect e1 = new GRect(0, 0, CW / 10, CH);         		GRect e2 = new GRect(CH, 0, CW / 10, CH);
		
	GLine c1 = new GLine(CW / 4.29, 0, CW / 4.29, CH);		GLine c2 = new GLine(CW / 2.73, 0, CW / 2.73, CH);
		
	GRect block1 = new GRect(CW / 6.66, CH / 50, CW / 33.33, CH / 33.33);	GRect block2 = new GRect(CW / 3.52, CH / 50, CW / 33.33, CH / 33.33);				
	GRect block3 = new GRect(CW / 2.40, CH / 50, CW / 33.33, CH / 33.33);
		
	GRect PUNT = new GRect(9*CW / 10, 4*CH / 5, 9*CW / 100, 4*CH / 50);
	GRect Qu = new GRect(7*CW / 10, CH / 5, 22*CW / 100, 7*CH / 100);
		
	int A = rgen.nextInt(0, 20);		int B = rgen.nextInt(0, 20);
		
	GLabel question = new GLabel("¿Cuánto es " + A + " + " + B + "?");
	GLabel ansL = new GLabel("");		GLabel ansM = new GLabel("");		GLabel ansN = new GLabel("");
		
	public static int c = 0;		
	public static int score = 0;	
		
	String Score = Integer.toString(score);
	GLabel SCORE = new GLabel("SCORE: " + score, 23*CW / 25, 42*CH / 50);
	public static GPolygon car;
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	public static final int CW = 1200;	
	public static final int CH = CW / 2;
	private GObject car_;
	private GPoint P;
	private static RandomGenerator rgen = RandomGenerator.getInstance();
}