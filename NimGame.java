
/*
 * Author name: Wai Yan WONG
 * Student ID: 892083
 * User Name: waiw7
 * Apr 28th, 2018
 */
import java.util.Scanner;

public class NimGame {
	private int numplayer = 2;

	public int startgame(int left, int BOUND, NimPlayer PlayerOne, NimPlayer PlayerTwo, Scanner keyboard) {
		int remove, round, winner; // create variables
		round = 1; // in order to calculate which player's turn

		System.out.printf("\nInitial stone count: %d", left);
		System.out.printf("\nMaximum stone removal: %d", BOUND);
		System.out.printf("\nPlayer 1: %s %s", PlayerOne.getgname(), PlayerOne.getfname());
		System.out.printf("\nPlayer 2: %s %s\n", PlayerTwo.getgname(), PlayerTwo.getfname());

		while (left != 0) {
			/*
			 * in the circulation, print left stones first, then read the number of removed
			 * stones
			 */
			PrintStones(left); // print left stones
			remove = InputRemove(round, left, BOUND, PlayerOne, PlayerTwo, keyboard);
			if (remove == 0) {
				round--;
			}
			left = left - remove;
			round++;
		}

		/*
		 * when the left stones = 0, game over. Return the winner number.
		 */
		winner = GameOver(round, PlayerOne, PlayerTwo);
		return winner;
	}

	public void PrintStones(int left) {
		System.out.printf("\n%d stones left:", left);
		for (int i = 0; i < left; i++) {
			System.out.print(" *");
		}
	}

	public int InputRemove(int round, int left, int BOUND, NimPlayer PlayerOne, NimPlayer PlayerTwo, Scanner keyboard) {
		/*
		 * calculate which player's turn first, then read the number of removed stones
		 * and calculate if it is a valid number.
		 */
		int remove = 0;
		int okay = 0;
		while (okay == 0) {
			if (round % numplayer == 1) {
				remove = PlayerOne.removeStone(keyboard);
			} else {
				remove = PlayerTwo.removeStone(keyboard);
			}
			if (remove > BOUND || remove > left || remove < 1) {
				if (left > BOUND) {
					System.out.printf("\nInvalid move. You must remove between 1 and %d stones.\n", BOUND);
				} else {
					System.out.printf("\nInvalid move. You must remove between 1 and %d stones.\n", left);
				}
			remove = 0;
			}
			okay = 1;
		}
		return remove;
	}

	public int GameOver(int round, NimPlayer PlayerOne, NimPlayer PlayerTwo) {
		/*
		 * when the left stones = 0, game over print the winner's name
		 */
		int winner;
		System.out.printf("\nGame Over\n");
		if (round % numplayer == 1) {
			System.out.printf("%s %s wins!\n", PlayerOne.getgname(), PlayerOne.getfname());
			winner = 0;
		} else {
			System.out.printf("%s %s wins!\n", PlayerTwo.getgname(), PlayerTwo.getfname());
			winner = 1;
		}
		return winner;
	}
}