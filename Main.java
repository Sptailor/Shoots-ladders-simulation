package edu.ilstu;

import java.util.Scanner;
/*
 * @author suhail Pradip Tailor
 * ulid 805254601
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner (System.in);//creates scanner object
		System.out.println("welcome to the Shoots and Ladders Game"+"\n" + "select number of players:");//prompts user
		int players=scan.nextInt();//reads input
		
       Game g1=new Game(players);//passes input through to the game object
       System.out.println("Start point-->"+g1.list);//informs user about start point
       g1.play();//runs the game until a player wins
       for(int i=0;i<players;i++)//loops through all players
       {
    	if(g1.list.get(i)==100)//checks if player has reached last square
        System.out.println("The winner was player " +(i+1));//displays winner
	
       }
       
       System.out.println("__________end___________");
	   System.out.println("Thank you for playing");

	}

}
