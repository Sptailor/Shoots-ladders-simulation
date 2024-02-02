package edu.ilstu;

/*
 * @author suhail pradip Tailor
 * UID 805254601
 * 
 */
		
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Game 
{
	Scanner scan=new Scanner (System.in);
	 boolean won=false;
	
	Random rand=new Random();
	int random=rand.nextInt(20);//creates random between 0 and 20
    int roll=rand.nextInt(6);//gets random number on dice 
	int size;
	private Square head=null;
	ArrayList<Integer>list;//creates arraylist called list that holds integers
	
	/*
	 * @param integer
	 * creates array list of size passed as parameter 
	 * 
	 */
	public Game(int num) 
	{
		list=new ArrayList<Integer>(num);//arraylist is created with size equal to number of players
		makeBoard();//creates a board of 100 squares and links them to each other,places players at the start point
		for(int i=0;i<num;i++)//loops through all players
		list.add(head.sNum);//all players placed at head square 
		
	}
	/*
	 * @param none
	 * @return void
	 * runs the game and gets a winner after code runs
	 */
	public void play()
	{
		System.out.println("Press any key to play");//prompts user
		String play=scan.next();//gets input
		int dice;
		//int count=0;
		while(!won)//loops runs while there is no winner

	 {
		for(int i=0;i<=list.size()-1;i++)//loops through all players
		{
			dice=(int)(Math.random()*6+1);//generates random number on dice
			System.out.println(" ");

			System.out.println("Dice roll result for player  "+ (i+1) +" was "+dice);//informs players on dice result
			
			move(i,dice);//calls move method and sets new positions for each player
		//count++;
		}
		
		System.out.println(" ");
		System.out.println("New positions -->"+list);//informs user on current positions
		System.out.println(" ");
	 }
	}
	
	/*
	 * @param integer player number and integer dice result
	 * @return boolean won
	 * moves the player according to the dice result, adds jump and places player on new positions until end is reached 
	 */
	
	public boolean move(int playNum,int num) 
	{
		Square curr=head;
		int forward;//final square player reaches during the turn
     
       
		while(curr.sNum!=list.get(playNum))//loops through until current player position is reached
			curr=curr.next;//moves to next square
		 forward=0;//resets forward to 0
		 int originalPos=curr.sNum;//sets current pos as original before the move
		 forward=curr.sNum+num;//gets number of steps taken forward according to dice roll
       while(curr.sNum<forward&&forward<=100)//runs while end has not been reached
    	   curr=curr.next;//moves to next position or square
       
       if(curr.jump>0) //runs if jump value is positive
       {
    	   forward=forward+curr.jump;//adds jump value to number of steps taken forward
    	   System.out.println("Player got jump of "+ curr.jump);//informs user about jump
       }
       
       
       else if(curr.jump<0) //runs if jump value is negative
       {
    	   forward=forward+curr.jump;//adds jump value to number of steps taken forward
    	   System.out.println("Player got shoot of "+ curr.jump);//informs user about jump
       }
		
       if(forward<0)//runs if player had to move backwards,forward was negative value and was taken back to the beginning square
       {
    	   forward=1;//sets forward to one so player does not fall off board
       list.set(playNum,forward);//sets player position to 1
		System.out.println(forward+"----->"+originalPos);//informs user of the change
	
       }
       
		if(forward<100) //runs if the value of forward was less than hundred
		{
		list.set(playNum,forward);//sets player position
		System.out.println(originalPos+"-------->"+forward);//informs user of the change
	
		}
		
		if (forward>100)//runs if the value of forward was more than hundred
		{
			System.out.println("overshot");//informs player that value was too large 
			
        }
		else if (forward==100) //runs if the value of forward was equal to hundred
		{
		    
			System.out.println((originalPos)+"----->"+forward);//informs user about the movement
		
			list.set(playNum,forward);//sets the player position
		
			won=true;//changes boolean to true to end loop
		}
		
       return won;
		
	}
	
	/*
	 * @param none
	 * @return void
	 * creates 100 squares and connects them
	 */
	
	private void makeBoard() 
	{
		for(int k=1;k<=100;k++)//runs 100 times to make 100 squares
		{
			if (head==null)//if first square is being made
			{
				Square start=new Square(k,0,head);//creates starting square 
		        head=start;// makes head point to start square 
		    }
			else 
			{//runs if this is not the first square being made
				Square current=head;//points to head
				while(current.next!=null) 
					current=current.next;//moves to next square
				
				if(current.sNum%6==0&&current.sNum<=90) //runs if square number is factor of 6 and less than 91
				{
					
					current.next=new Square(k,rand.nextInt(20+10)-10,current.prev);//creates square with non zero jump value
				}
				else
					current.next=new Square(k,0,current.prev);//creates square with 0 jump value
					
			}
			

			size++;		//keeps track of number of square made 
		}
		System.out.println("In order to win you must reach "+size);//informs user of the goal
	}
	
	
	private class Square
	{
		private Square next;//reference to next square
		private Square prev;//reference to previous square
		private int sNum;//reference to square number
		private int jump;//reference to jump value
		
		
		private Square(int num,int jump,Square prev) //overloaded constructor that takes in a number,the jump val and previous square
		{
			this.sNum=num;
			this.jump=jump;
			this.prev=prev;
		}
	}
}
