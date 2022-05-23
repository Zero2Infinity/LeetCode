package Design.snakegame;

import java.util.LinkedList;

class Position {
		int row, col;
		Position(int r, int c) {
			row = r; col = c;
		}
	}

public class SnakeGameUsingLinkedList implements SnakeGame {
	private final int width;
	private final int height;
	private final int[][] foods;
	private int foodPtr = 0;
	
	private LinkedList<Position> snakeBody;
	private int snakeSize = 0;
	
	public SnakeGameUsingLinkedList(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.foods = food;
		
		snakeBody = new LinkedList<>();
		snakeBody.add(new Position(0, 0));
	}
	
	public int move(String direction) {
		Position next = nextPosition(direction);
		if (outOfBound(next)) {
			System.out.println("-1");
			return -1;
		} else if (isFoodCell(next)) {
			foodPtr ++;
			snakeBody.add(next);			// append
			snakeSize ++;
		} else {
			snakeBody.add(next);			// append
			snakeBody.removeFirst();		// Head <-> Tail
		}
		
		if (headOverBody(next)) {
			System.out.println("-2");
			return -1;
		} else {
			System.out.println(snakeSize);
			return snakeSize;
		}
	}
	
	private boolean headOverBody(Position next) {
		// skip the head since we compare after moving head to next position
		for (int i = 0; i < snakeBody.size() - 1; i++) {
			if (next.row == snakeBody.get(i).row && next.col == snakeBody.get(i).col) {
				return true;
			}
		}
		return false;
	}

	private boolean isFoodCell(Position next) {
		return (foodPtr < foods.length) && (next.row == foods[foodPtr][0] && next.col == foods[foodPtr][1]);
	}

	private boolean outOfBound(Position next) {
		return (next.row < 0 || next.row >= height) || (next.col < 0 || next.col >= width);
	}

	private Position nextPosition(String direction) {
		Position next = null;
		Position curr = snakeBody.getLast();
		switch (direction) {
			case "R":
				next = new Position(curr.row, curr.col + 1);
				break;
			case "L":
				next = new Position(curr.row, curr.col - 1);
				break;
			case "U":
				next = new Position(curr.row - 1, curr.col);
				break;
			case "D":
				next = new Position(curr.row + 1, curr.col);
				break;
		}
		return next;
	}
}
