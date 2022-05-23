package Design.snakegame;

// Using My Single LL with Head and Tail Pointers 
// Got a lot confused with how to grew snake body and associate start and end
public class MySnakeGame implements SnakeGame{

	static class List {
		Cell cell;
		List next;
		List(Cell c) {
			this.cell = c; this.next = null;
		}
		List(Cell c, List next) {
			this.cell = c; this.next = next;
		}
	}
	
	static class Cell {
		int r;
		int c;
		Cell(int x, int y) {
			r = x; c = y;
		}
	}
	private int width;
	private int height;
	private int[][] food;
	private int fPtr; 	// Current Food Index

	private List snakeBody;
	private List head;
	private List tail;
	private int bodySize = 0;
	
 public MySnakeGame(int width, int height, int[][] food) {
     this.width = width;
     this.height = height;
     this.food = food;
     this.fPtr = 0;
     
     // Starting position of Snake's head
     snakeBody = new List(new Cell(0,0));
     head = snakeBody;
     tail = snakeBody;
 }
 
 public int move(String direction) {
		Cell nextCell = nextCell(direction);
		// TODO: Out-of-bound check || Head going to occupy any of body's cell
		if (nextCell != null) {
			if (outOfBound(nextCell)) {
				// game violations
				gameOver(); return -1;
			} else if (fPtr < food.length && nextCell.r == food[fPtr][0] && nextCell.c == food[fPtr][1]) {
				// grow
				fPtr ++;
				head.next = new List(nextCell);
				head = head.next;
				bodySize ++;
			} else {
				// move 
				head.next = new List(nextCell);
				head = head.next;
				tail = tail.next;
			}
		}
		if (headOverBody(head.cell)) {
			System.out.println(-1);
			return -1;
		} else {
			System.out.println(snakeBodySize());
			return snakeBodySize();
		}
	}
 
 private Cell nextCell(String direction) {
 	Cell nextCell = null;
 	switch (direction) {
			case "R": 
				nextCell = new Cell(head.cell.r, head.cell.c + 1);
				break;
			case "D":
				nextCell = new Cell(head.cell.r + 1, head.cell.c);
				break;
			case "U":
				nextCell = new Cell(head.cell.r - 1, head.cell.c);
				break;
			case "L":
				nextCell = new Cell(head.cell.r, head.cell.c - 1);
 	}
 	
 	return nextCell;
 }

 private boolean outOfBound(Cell nextCell) {
 	return (nextCell.r < 0 || nextCell.r >= height) || (nextCell.c < 0 || nextCell.c >= width);
 }
 
 private boolean headOverBody(Cell nextCell) {
 	List curr = tail;
 	while (curr != head) {
 		if (nextCell.r == curr.cell.r && nextCell.c == curr.cell.c) {
 			return true;
 		}
 		curr = curr.next;
 	}
 	return false;
 }
 
 private int snakeBodySize() {
 	return bodySize;
 }

 private void gameOver() {
		System.out.println(-1);
 }
 
}