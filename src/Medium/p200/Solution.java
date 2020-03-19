package Medium.p200;

import java.util.Deque;
import java.util.ArrayDeque;
/**
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and
is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/
public class Solution {
	static class Coordinate {
		public int x; public int y;
		Coordinate(int x, int y) { this.x = x; this.y = y;}
		public boolean isSafe(int rows, int cols) { 
			return (x >= 0 && x < rows && y >= 0 && y < cols) ? true : false;
		}
	}
	
	private static int numberOfIslands(int[][] grid) {
		int count = 0;										// count islands
		Deque<Coordinate> bfs = new ArrayDeque<>();
		int rows = grid.length;
		int cols = grid[0].length;
		System.out.println("row:" + rows + " col: " + cols);
		
		int[][] directions = { {1,0}, {-1,0}, {0,1}, {0,-1} };	// Right, Left, Up, Down
		boolean[][] visited = new boolean[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 1 && !visited[r][c]) {
					System.out.println("starting pos: [" + r + "][" + c +"]");
					bfs.add(new Coordinate(r,c));
					visited[r][c] = true;
					while(!bfs.isEmpty()) {
						Coordinate currLand = bfs.pop();
						for (int d = 0; d < directions.length; d++) {
							int newX = currLand.x + directions[d][0];
							int newY = currLand.y + directions[d][1];
							Coordinate newXY = new Coordinate(newX, newY);
							if (newXY.isSafe(rows, cols)
								&& !visited[newX][newY]
								&& grid[newX][newY] == 1) {
								// Part of same land criteria
								bfs.add(newXY);
								visited[newX][newY] = true;
							}
						}
					}
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] grid = {{1,1,0,0,1},
						{1,1,0,0,0},
						{0,0,1,0,0},
						{0,0,0,1,1}};


		int result = numberOfIslands(grid);
		System.out.println(result);
	}
}
