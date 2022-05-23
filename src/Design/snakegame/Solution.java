package Design.snakegame;

public class Solution {

	public static void main(String[] args) {
		SnakeGame snakeGame = new SnakeGameUsingLinkedList(3, 3, new int[][] {{2,0},{0,0},{0,2},{2,2}});
		snakeGame.move("D");
		snakeGame.move("D");
		snakeGame.move("R");
		snakeGame.move("U");
		snakeGame.move("U");
		snakeGame.move("L");
		snakeGame.move("D");
		snakeGame.move("R");
		snakeGame.move("R");
		snakeGame.move("U");
		snakeGame.move("L");
		snakeGame.move("D");
	}
}
