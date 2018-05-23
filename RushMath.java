import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;


public class RushMath extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;
	public void run()
	{
		createStage();

		while(Wbreaker == true)
		{
			MoveBlocks();
			pause(30);
			car.setFillColor(Color.RED);
			pause(30);
			car.setFillColor(Color.GREEN);
			pause(30);
			car.setFillColor(Color.BLUE);
			ByeObstacle(car, block1, block2, block3);
			NewObstacle(block1, block2, block3);
		}

		pause(3000);
		System.exit(1);

	}


	//Making of the stage.
	public void createStage()
	{
		setSize(CW, CH);
		WALLPAPER.sendToBack();
		add(WALLPAPER);

		//-------------------------------------------------------------------------------------------------------------------------------------------------
		add(e1);		e1.setFilled(true);		e1.setFillColor(Color.BLACK);	e1.setColor(Color.WHITE);
		add(e2);		e2.setFilled(true);		e2.setFillColor(Color.BLACK);	e2.setColor(Color.WHITE);
		add(c1);		c1.setColor(Color.WHITE);
		add(c2);	 	c2.setColor(Color.WHITE);
		//Adding score field.
		add(PUNT);		PUNT.setFilled(true);	PUNT.setFillColor(Color.WHITE);
		SCORE.setFont(SFONT);
		add(SCORE);
		//Adding questions field.
		add(Qu); 	Qu.setFilled(true);		Qu.setFillColor(Color.WHITE);
		//Adding car.

		car = createCar(3*CW / 10);		add(car);		car.setFilled(true);		car.setFillColor(Color.BLUE);
		//------------------------------------------------------------------------------------------------------------------------------------------------
		question.setFont(QUFONT);
		AuR.setFont(QUFONT);
		count3.setFont(QUFONT);
		count2.setFont(QUFONT);
		count1.setFont(QUFONT);
		add(AuR);
		pause(1000);
		remove(AuR);
		add(count3);
		pause(1000);
		remove(count3);
		add(count2);
		pause(1000);
		remove(count2);
		add(count1);
		pause(1000);
		remove(count1);
		pause(1000);

		//Adding listeners.
		addKeyListeners();
		add(block1);		block1.setFilled(true);		block1.setFillColor(Color.WHITE);
		add(block2);		block2.setFilled(true);		block2.setFillColor(Color.WHITE);
		add(block3);		block3.setFilled(true);		block3.setFillColor(Color.WHITE);

		RandomOP();
		question.setFont(QUFONT);
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


	//
	public void keyPressed(KeyEvent f)
	{
		if(f.getKeyCode() == KeyEvent.VK_RIGHT && car.getX() < 17*CW / 100)
		{
			car.move(CW / 100, 0);
		}
		if(f.getKeyCode() == KeyEvent.VK_LEFT && car.getX() > -17*CW / 100)
		{
			car.move(-CW / 100, 0);
		}
		if(f.getKeyCode() == KeyEvent.VK_UP && car.getY() > -4*CH / 5)
		{
			car.move(0, -CH / 50);
		}
		if(f.getKeyCode() == KeyEvent.VK_DOWN && car.getY() < 4*CH / 50)
		{
			car.move(0, CH / 50);
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
				WhileBreaker(1);
			}
			else if(c == 2)
			{
				ansM.setVisible(false);
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				GAME_OVER.setSize(CW, CH);
				add(GAME_OVER);
				WhileBreaker(0);
			}
			else if(c == 3)
			{
				ansN.setVisible(false);
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				GAME_OVER.setSize(CW, CH);
				add(GAME_OVER);
				WhileBreaker(0);
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
				GAME_OVER.setSize(CW, CH);
				add(GAME_OVER);
				WhileBreaker(0);
			}
			else if(c == 2)
			{
				ScorePlus();
				ansL.setVisible(false);
				WhileBreaker(1);
			}
			else if(c == 3)
			{
				ansM.setVisible(false);
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				GAME_OVER.setSize(CW, CH);
				add(GAME_OVER);
				WhileBreaker(0);
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
				GAME_OVER.setSize(CW, CH);
				add(GAME_OVER);
				WhileBreaker(0);
			}
			else if(c == 2)
			{
				ansN.setVisible(false);
				setBackground(Color.RED);
				pause(1500);
				removeAll();
				GAME_OVER.setSize(CW, CH);
				add(GAME_OVER);
				WhileBreaker(0);
			}
			else if(c == 3)
			{
				ScorePlus();
				ansL.setVisible(false);
				WhileBreaker(1);
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
			add(block1, CW / 6.66, 0); 	add(block2, CW / 3.52, 0);		add(block3, CW / 2.40, 0);
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
		question = new GLabel("How much is " + a + " + " + b + "?");
		question.setFont(QUFONT);
		add(question, 73*CW / 100, 12*CH / 50);
		ansL = new GLabel(Integer.toString(a + b));
		ansL.setFont(NUMFONT);
		ansM = new GLabel(Integer.toString((a + b) - d));
		ansM.setFont(NUMFONT);
		ansN = new GLabel(Integer.toString((a + b) + e));
		ansN.setFont(NUMFONT);
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
		question = new GLabel("How much is " + a + " - " + b + "?");
		question.setFont(QUFONT);
		add(question, 73*CW / 100, 12*CH / 50);
		ansL = new GLabel(Integer.toString(a - b));
		ansL.setFont(NUMFONT);
		ansM = new GLabel(Integer.toString((a - b) + d));
		ansM.setFont(NUMFONT);
		ansN = new GLabel(Integer.toString((a - b) - e));
		ansN.setFont(NUMFONT);
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


	//Increase the score by choosing the correct answer.
	public void ScorePlus()
	{
		if(ansL.isVisible())
		{
			remove(SCORE);
			score++;
			Score = Integer.toString(score);
			SCORE = new GLabel("SCORE: " + score, 23*CW / 25, 42*CH / 50);
			SCORE.setFont(SFONT);
			add(SCORE);
			PAUSE_BLOCKS -= 15;
		}
	}


	public void WhileBreaker(int x)
	{
		if(x == 1)
		{
			Wbreaker = true;
		}

		if(x == 0)
		{
			Wbreaker = false;
		}
	}


	public void MoveBlocks()
	{
		if(PAUSE_BLOCKS >= 0)
		{
			pause(PAUSE_BLOCKS);
		}

		block1.move(0, CH / VELO_BLOCKS);		block2.move(0, CH / VELO_BLOCKS);		block3.move(0, CH / VELO_BLOCKS);
		ansL.move(0, CH / VELO_BLOCKS);		ansN.move(0, CH / VELO_BLOCKS);		ansM.move(0, CH / VELO_BLOCKS);
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------




	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	public static GImage WALLPAPER = new GImage("wall.jpg");
	public static GImage GAME_OVER = new GImage("go.jpg");
	public static int VELO_BLOCKS = 30;
	public static Font QUFONT = new Font("SANS_SERIF", Font.BOLD, 25);
	public static Font NUMFONT = new Font("SANS_SERIF", Font.BOLD, 20);
	public static Font SFONT = new Font("SANS_SERIF", Font.BOLD, 18);
	public int PAUSE_BLOCKS = 50;
	public static boolean Wbreaker = true;
	public static final int CW = 1700;
	public static final int CH = CW / 2;
	private static RandomGenerator rgen = RandomGenerator.getInstance();

	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	public static GRect e1 = new GRect(0, 0, CW / 10, CH);         		public static GRect e2 = new GRect(CH, 0, CW / 10, CH);

	public static GLine c1 = new GLine(CW / 4.29, 0, CW / 4.29, CH);		public static GLine c2 = new GLine(CW / 2.73, 0, CW / 2.73, CH);

	public static GRect block1 = new GRect(CW / 6.66, 0, CW / 33.33, CH / 33.33);	public static GRect block2 = new GRect(CW / 3.52, 0, CW / 33.33, CH / 33.33);
	public static GRect block3 = new GRect(CW / 2.40, 0, CW / 33.33, CH / 33.33);

	public static GRect PUNT = new GRect(9*CW / 10, 4*CH / 5, 9*CW / 100, 4*CH / 50);
	public static GRect Qu = new GRect(7*CW / 10, CH / 5, 22*CW / 100, 7*CH / 100);

	public static int A = rgen.nextInt(0, 20);		public static int B = rgen.nextInt(0, 20);

	public static GLabel question = new GLabel("How much is " + A + " + " + B + "?");
	public static GLabel ansL = new GLabel("");		public static GLabel ansM = new GLabel("");		public static GLabel ansN = new GLabel("");

	public static int c = 0;
	public static int score = 0;

	public static String Score = Integer.toString(score);
	public static GLabel SCORE = new GLabel("SCORE: " + score, 23*CW / 25, 42*CH / 50);
	public static GPolygon car;

	public static GLabel AuR = new GLabel("Are u ready?", 73*CW / 100, 12*CH / 50);
	public static GLabel count1 = new GLabel("...1", 73*CW / 100, 12*CH / 50);
	public static GLabel count2= new GLabel("...2...", 73*CW / 100, 12*CH / 50);
	public static GLabel count3 = new GLabel("3...", 73*CW / 100, 12*CH / 50);
}
