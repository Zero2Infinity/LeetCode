package Others.MinHoursToSendFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Input:
[[0, 1, 1, 0, 1],
 [0, 1, 0, 1, 0],
 [0, 0, 0, 0, 1],
 [0, 1, 0, 0, 0]] */

class Solution {
	
	public static List<List<Integer>> buildGrid() {
		// Build input using List<List<>> grid
		List<List<Integer>> grid = new ArrayList<>();
		grid.add(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 0, 1)));
		grid.add(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0)));
		grid.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1)));
		grid.add(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 0, 0)));		// Variation
		return grid;
	}

	public static void printGrid(List<List<Integer>> grid) {
		int row = grid.size();
		int col = grid.get(0).size();
		System.out.println("row:" + row + " col:" + col);
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(grid.get(i).get(j));
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static int minimumHours(List<List<Integer>> grid) {
		int row = grid.size();
		int col = grid.get(0).size();
		int totalServer = row * col;
		int fileCounter = 0;
		int[][] moves = { {0,-1}, {0,1}, {-1,0}, {1,0} };
		List<Cell> servers = new ArrayList<>();				// ***This could be ArrayDeque***
		
		// List of servers which has files 
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid.get(i).get(j) == 1) {
					servers.add(new Cell(i, j));
					fileCounter++;
				}
			}
		}
		
		int hours = 0;
		while (!servers.isEmpty()) {
			if (fileCounter == totalServer) return hours;
			
			int size = servers.size();
			for (int i = 0; i < size; i++) {
				Cell server = servers.remove(0);
				int currX = server.x;
				int currY = server.y;
				for (int[] m : moves) {
					int nextX = currX + m[0];
					int nextY = currY + m[1];
					Cell nextCell = new Cell(nextX, nextY);
					if (nextCell.isSafe(row, col) && grid.get(nextX).get(nextY) == 0) {
						grid.get(nextX).remove(nextY);
						grid.get(nextX).add(nextY, 1);
						servers.add(nextCell);
						fileCounter++;
					}
				}
			}
			hours++;
			printGrid(grid);
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Minimum hours to send file to  adjacent servers");
		List<List<Integer>> matrix = buildGrid();
		printGrid(matrix);
		System.out.println(minimumHours(matrix));
	}
	
	static class Cell {
		int x;
		int y;
		public Cell(int x, int y) {
			 this.x = x;
			 this.y = y;
		}

		public boolean isSafe(int rSize, int cSize) {
			return (x >= 0 && x < rSize && y >= 0 && y < cSize);
		}
	}
}
