/*
 * Author name: Wai Yan WONG
 * Student ID: 892083
 * User Name: waiw7
 * Apr 28th, 2018
 */
import java.util.Scanner;

public class Nimsys {
	static Scanner keyboard = new Scanner(System.in);
	private static String[] name = new String[5];
	private static int LENGTH = 100;
	private static NimPlayer[] player = new NimPlayer[LENGTH];
	private static int okay;// this variable is used for measuring whether the username exists

	public static void main(String[] args) {
		String command;
		Nimsys sys = new Nimsys();
		
		//initialize player
		for (int i = 0; i < LENGTH; i++) {
			player[i] = new NimPlayer();
		}

		System.out.println("Welcome to Nim");
		
		while (true) {
			/* 
			 * in the circulation,
			 * print a blank line and $ first,
			 * then read the command and other words,
			 * execute the command at last.
			 */
			nextprint();
			command = keyboard.next();
			read();
			okay = 0;
			com(sys, command);
		}
	}

	public static void nextprint() {
		System.out.println();
		System.out.print("$");
	}

	public static void read() {
		String input;
		input = keyboard.nextLine();
		name = input.split(",");//divide the input
	}

	public static void com(Nimsys s, String command) {
		// distinguish the content of commands
		if (command.equals("addplayer")) {
			s.addplayer();
		}

		if (command.equals("removeplayer")) {
			s.removeplayer();
		}

		if (command.equals("editplayer")) {
			s.editplayer();
		}

		if (command.equals("resetstats")) {
			s.resetstats();
		}

		if (command.equals("displayplayer")) {
			s.displayplayer();
		}

		if (command.equals("rankings")) {
			s.rankings();
		}

		if (command.equals("startgame")) {
			s.startgame();
		}

		if (command.equals("exit")) {
			System.out.println();
			System.exit(0);
		}
		
	}
	
	public void addplayer() {
		name[0] = name[0].substring(1, name[0].length()); // delete the space in name[0]
		/*
		 * check if player[i]' username is null or not. if the username wasn't null,
		 * check if the added username exists
		 */
		for (int i = 0; i < LENGTH; i++) {
			if (player[i].getusername() != null) {
				if (name[0].equals(player[i].getusername())) {
					System.out.println("The player already exists.");
					okay = 1;
					break;
				}
			}
		}
		// if the added username didn't exist, add a new player
		if (okay == 0) {
			for (int j = 0; j < LENGTH; j++) {
				if (player[j].getusername() == null) {
					player[j].setusername(name[0]);
					player[j].setfname(name[1]);
					player[j].setgname(name[2]);
					player[j].setplayed(0);
					player[j].setwon(0);
					break;
				}
			}
			/*
			 * sort players by username alphabetically
			 */
			for (int i = 0; i < LENGTH - 1; i++) {
				if (player[i].getusername() != null) {

					String minuname = player[i].getusername();
					int minnum = i;
					for (int j = i; j < LENGTH; j++) {
						if (player[j].getusername() != null) {
							if (minuname.compareTo(player[j].getusername()) > 0) {
								minuname = player[j].getusername();
								minnum = j;
							}
						}
					}
					swap(i, minnum);
				}
			}
		}
	}

	public void removeplayer() {
		/*
		 * if the operator inputs command only,
		 * asks if he want to remove all players.
		 */
		if (name[0].equals("")) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String ans = keyboard.nextLine();
			if (ans.equals("y")) {
				for (int i = 0; i < LENGTH; i++) {
					player[i].removeplayer();
				}
				okay = 1;
			}
		} else {
			/*
			 * otherwise,delete the space in name[0] first,
			 * then check if the username exists.
			 * If it existed, remove the player.
			 */
			name[0] = name[0].substring(1, name[0].length());
			for (int i = 0; i < LENGTH; i++) {
				if (player[i].getusername() != null) {
					if (name[0].equals(player[i].getusername())) {
						player[i].removeplayer();
						okay = 1;
						break;
					}
				}
			}
			if (okay == 0) {
				System.out.println("The player does not exist.");
			}
		}
	}

	public void editplayer() {
		/*
		 * delete the space in name[0] first,
		 * then check if the username exists.
		 * If it existed, edit the player.
		 */
		name[0] = name[0].substring(1, name[0].length());
		for (int i = 0; i < LENGTH; i++) {
			if (player[i].getusername() != null) {
				if (name[0].equals(player[i].getusername())) {
					player[i].setfname(name[1]);
					player[i].setgname(name[2]);
					okay = 1;
					break;
				}
			}
		}
		if (okay == 0) {
			System.out.println("The player does not exist.");
		}
	}

	public void resetstats() {
		/*
		 * if the operator inputs command only,
		 * asks if he want to reset all players.
		 */
		if (name[0].equals("")) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String ans = keyboard.nextLine();
			if (ans.equals("y")) {
				for (int i = 0; i < LENGTH; i++) {
					if (player[i].getusername() != null) {
						player[i].setplayed(0);
						player[i].setwon(0);
					}
				}
				okay = 1;
			}
		} else {
			/*
			 * otherwise,delete the space in name[0] first,
			 * then check if the username exists.
			 * If it existed, reset states of the player.
			 */
			name[0] = name[0].substring(1, name[0].length());
			for (int j = 0; j < LENGTH; j++) {
				if (player[j].getusername() != null) {
					if (name[0].equals(player[j].getusername())) {
						player[j].setplayed(0);
						player[j].setwon(0);
						okay = 1;
					}
				}
			}
			if (okay == 0) {
				System.out.println("The player does not exist.");
			}
		}
	}

	public void displayplayer() {
		/*
		 * if the operator inputs command only,
		 * display players,ordered by username alphabetically
		 */
		if (name[0].equals("")) {
			for (int i = 0; i < LENGTH-1; i++) {
				if (player[i].getusername() != null) {
					System.out.printf("%s,%s,%s,%d games,%d wins", player[i].getusername(), player[i].getgname(),
							player[i].getfname(), player[i].getplayed(), player[i].getwon());
					System.out.println();
				}
			}
			okay = 1;
		} else {
			/*
			 * otherwise,delete the space in name[0] first,
			 * then check if the username exists.
			 * If it existed, display the player.
			 */
			name[0] = name[0].substring(1, name[0].length());
			for (int i = 0; i < LENGTH; i++) {
				if (player[i].getusername() != null) {
					if (name[0].equals(player[i].getusername())) {
						System.out.printf("%s,%s,%s,%d games,%d wins", player[i].getusername(), player[i].getgname(),
								player[i].getfname(), player[i].getplayed(), player[i].getwon());
						System.out.println();
						okay = 1;
						break;
					}
				}
			}
			if (okay == 0) {
				System.out.println("The player does not exist.");
			}
		}
	}

	public void rankings() {
		int actuall = 0;
		int[][] rank = new int[LENGTH][2];
		/*
		 * get the number of players,
		 * and calculate their winning ratio,
		 * along with their sequence number.
		 */
		for (int i = 0; i < LENGTH; i++) {
			if (player[i].getusername() != null) {
				rank[actuall][0] = (int)(((double)player[i].getwon() *100.0)/(double)player[i].getplayed()+0.5);
				rank[actuall][1] = i;
				actuall++;
			}
		}
		/*
		 * sort players by their winning ratio.
		 */

		
		
		/*
		 * if the operator inputs 'rankings' or 'rankings desc',
		 * display players,ordered by their winning ratio descendingly.
		 */
		if (name[0].equals("") || name[0].equals(" desc") || name[0].equals(" ")) {
			for (int j = 0; j < actuall-1; j++) {
				int max = rank[j][0];
				int maxi = j;
				for (int k = j + 1; k < actuall; k++) {
					if (rank[k][0] > max) {
						max = rank[k][0];
						maxi = k;
					}
				}
				rank[maxi][0] = rank[j][0];
				rank[maxi][1] = rank[j][1];
				rank[j][0] = max;
				rank[j][1] = maxi;
			}
			for (int a = 0; a < actuall; a++) {
				if (a >= 10 || player[rank[a][1]].getusername() == null) {
					break;
				} else {
					int winrate = rank[a][0];
					int id = rank[a][1];
					displayrank(winrate, id);
				}
			}
		}
		/*
		 * if the operator inputs 'rankings asc',
		 * display players,ordered by their winning ratio ascendingly.
		 */
		if (name[0].equals(" asc")) {
			for (int j = 0; j < actuall-1; j++) {
				int min = rank[j][0];
				int mini = j;
				for (int k = j + 1; k < actuall; k++) {
					if (rank[k][0] < max) {
						min = rank[k][0];
						mini = k;
					}
				}
				rank[mini][0] = rank[j][0];
				rank[mini][1] = rank[j][1];
				rank[j][0] = min;
				rank[j][1] = mini;
			}
			for (int a = 0; a < actuall; a++) {
				if (a >= 10 || player[rank[a][1]].getusername() == null) {
					break;
				} else {
					int winrate = rank[a][0];
					int id = rank[a][1];
					displayrank(winrate, id);
				}
			}
		}
	}

	public void startgame() {
		NimGame game = new NimGame();
		int[] p = new int[2];
		//check if the usernames all exist.
		for (int i = 0; i < LENGTH; i++) {
			if (player[i].getusername() != null) {
				if (name[2].equals(player[i].getusername())) {
					p[0] = i;
					okay++;
				}
				if (name[3].equals(player[i].getusername())) {
					p[1] = i;
					okay++;
				}
			}
		}
		if (okay != 2) {
			System.out.println("One of the player does not exist.");
		} else {
			/*
			 * if both the usernames existed, start the game.
			 * When the game overs, set the number of games played
			 * as well as the number of games won.
			 */
			name[0] = name[0].substring(1, name[0].length());
			int initial = Integer.parseInt(name[0]);
			int bound = Integer.parseInt(name[1]);
			int winner = game.startgame(initial, bound, player[p[0]], player[p[1]], keyboard);
			player[p[0]].setplayed(player[p[0]].getplayed() + 1);
			player[p[1]].setplayed(player[p[1]].getplayed() + 1);
			if (winner == 0) {
				player[p[0]].setwon(player[p[0]].getwon() + 1);
			} else {
				player[p[1]].setwon(player[p[1]].getwon() + 1);
			}
		}
	}

	public void displayrank(int winrate, int id) {
		/*
		 * display a player's winning ratio, the number of games played
		 * and the player's full name in correct format.
		 */
		if (winrate < 10) {
			System.out.printf("%d%%   |", winrate);
		} else if (winrate < 100) {
			System.out.printf("%d%%  |", winrate);
		} else {
			System.out.printf("%d%% |", winrate);
		}
		System.out.printf(" 0%d games |", player[id].getplayed());
		System.out.printf(" %s %s", player[id].getgname(), player[id].getfname());
		System.out.println();
	}
	
	private void swap(int i, int minnum) { //used for swaping two player's information
		NimPlayer playermin = new NimPlayer();
		playermin.copy(player[minnum]);
		player[minnum].copy(player[i]);
		player[i].copy(playermin);
	}
}

