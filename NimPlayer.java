/*
 * Author name: Wai Yan WONG
 * Student ID: 892083
 * User Name: waiw7
 * Apr 28th, 2018
 */
import java.util.Scanner;

public class NimPlayer {
	private String username, gname,fname;
	private int played,won;
	
	private int remove;

	//read the number of removed stones
	public int removeStone(Scanner keyboard) {			
		System.out.printf("\n%s's turn - remove how many?\n", gname);
		try {
			remove = keyboard.nextInt();}
		catch(NegetiveRemovehException e){
			System.out.println("Please enter a positive integer.");
		}
		return remove;
	}
	
	//the constructor
	public NimPlayer() {
		username = null;
		gname = null;
		fname = null;
		played = 0;
		won = 0;
	}
	
	public void setusername(String uname) {
		username = uname;
	}
	
	public void setgname(String gn) {
		gname = gn;
	}
	
	public void setfname(String fn) {
		fname = fn;
	}
	
	public void setplayed(int p) {
		played = p;
	}
	
	public void setwon(int w) {
		won = w;
	}
	
	public String getusername() {
		return username;
	}
	
	public String getgname() {
		return gname;
	}
	
	public String getfname() {
		return fname;
	}
	
	public int getplayed() {
		return played;
	}
	
	public int getwon() {
		return won;
	}
	
	/*
	 * copy a plyer's information to the other player's
	 */
	public void copy(NimPlayer player) {
		this.username = player.getusername();
		this.fname = player.getfname();
		this.gname = player.getgname();
		this.played = player.getplayed();
		this.won = player.getwon();
	}
	
	/*
	 * remove a player,
	 * clear out his information
	 */
	public void removeplayer() {
		username = null;
		fname = null;
		gname = null;
		played = 0;
		won = 0;
	}
}