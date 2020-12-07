package lab12;

import java.io.BufferedReader;

import java.io.PrintWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;


import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	private char[][] board;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;
	private int goalx;
	private int goaly;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
		private char content;
		/** the row where this location occurs */
		private int row;
		/** the column where this location occurs */
		private int col;
		private boolean visited;
		private Node parent;

		public Node(int x, int y, char c) {
			visited = false;
			content = c;
			parent = null;
			this.row = x;
			this.col = y;
		}

		public boolean isWall() {
			return content == 'X';
		}

		public boolean isVisited() {
			return visited;
		}
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound) {
		return index < bound && index >= 0;
	}

	/**
	 * helper method to construct the solution path from S to G NOTE this path is
	 * built in reverse order, starting with the goal G.
	 */
	private void backtrack(Node end){

		Node c = end.parent; 
		
		while (c.parent != null) {
		

			maze[c.row][c.col].content = '.'; 
			if (maze[c.row][c.col].content == 'S') {
				maze[c.row][c.col].content = 'S';
			}
			c = c.parent; 
		}
		// TODO for assignment12
	}

	/** writes the solution to file */
	public void writeOutput() {

		PrintWriter output = null;

		// TODO for lab12
		try {
			output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.print(height);
			output.print(" ");
			output.println(width);
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					output.print(maze[i][j].content);
				}
				output.println();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			output.close();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}

	public void lineNext(int counter, String line) {
		if (counter >= 0 && counter <= height) {
			for (int col = 0; col < width; ++col) {
				char ch = line.charAt(col);
				this.maze[counter][col] = new Node(counter, col, ch);

				if (maze[counter][col].content == 'S') {
					this.startX = counter;
					this.startY = col;
				}
				if (maze[counter][col].content == 'G') {
					this.goalx = counter;
					this.goaly = col;
				}
			}

		}
	}

	/**
	 * helper method to construct a graph from a given input file; all member
	 * variables (e.g. maze, startX, startY) should be initialized by the end of
	 * this method
	 */
	private void buildGraph() {
		// TODO for lab12
		BufferedReader input = null;
		try {
			// don't forget to close input when you're done
			input = new BufferedReader(new FileReader(inputFileName));
			int counter = 0;
			String line = "";
			String fLine = "";
			while ((line = input.readLine()) != null) {
				if (counter == 0) {
					fLine = line;

					String[] fullLine = fLine.split(" ");
					this.height = Integer.parseInt(fullLine[0]);
					this.width = Integer.parseInt(fullLine[1]);
					this.maze = new Node[height][width];

				} else {
					lineNext(counter - 1, line);
				}
				++counter;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * obtains all neighboring nodes that have *not* been visited yet from a given
	 * node when looking at the four cardinal directions: North, South, West, East
	 * (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node start = maze[startX][startY];

		Node north, south, east, west;
		north = maze[currentNode.row - 1][currentNode.col];
		south = maze[currentNode.row + 1][currentNode.col];
		west = maze[currentNode.row][currentNode.col - 1];
		east = maze[currentNode.row][currentNode.col + 1];

		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> listNodes = new ArrayList<Node>();

		// 1. Inspect the north neighbor
		if (inMaze(currentNode.row - 1, height) && north.visited == false && north.isWall() == false) {
			// adds to a set of Nodes for each neighbor

			north.visited = true;
			north.parent = currentNode;
			listNodes.add(north);

		}
		// 2. Inspect the south neighbor

		if (inMaze(currentNode.row + 1, height) && south.visited == false && south.isWall() == false) {
			south.visited = true;
			south.parent = currentNode;
			listNodes.add(south);
		}

		// 3. Inspect the west neighbor
		if (inMaze(currentNode.col - 1, width) && west.visited == false && west.isWall() == false) {

			// adds to a set of Nodes for each neighbor
			west.visited = true;
			west.parent = currentNode;
			listNodes.add(west);
		}

		// 4. Inspect the east neighbor
		if (inMaze(currentNode.col + 1, width) && east.visited == false && east.isWall() == false) {

			east.visited = true;
			east.parent = currentNode;
			listNodes.add(east);
		}

		return listNodes;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		buildGraph();
		LinkedList<Node> list = new LinkedList<Node>();
		Node currnode, goal;

		goal = maze[goalx][goaly];
		currnode = maze[startX][startY];
		currnode.visited = true;
		
		
		
		list.add(currnode);
		Node otrocur;
		while (!list.isEmpty()) {

			otrocur = list.pop();
			if (otrocur.content == (goal.content)) {
				backtrack(otrocur);
				writeOutput();

				return;
			}
			for (Node nxt : getNeighbors(otrocur)) {
				
					list.add(nxt);

				
			}

		}

		// TODO for assignment12
	}
	public void DFS(Node curr, Node goal) {
		curr.visited = true;
		if (curr.content == (goal.content)) {
			this.backtrack(curr);
			return;
		}

		for (Node next : getNeighbors(curr)) {

			if (next.visited) {
				next.parent = curr;
				DFS(next, goal);
			}
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
	
		Stack<Node> list = new Stack<Node>();
		Node currnode, goal;

		goal = maze[goalx][goaly];
		currnode = maze[startX][startY];
		currnode.visited = true;
		
		
		
		list.push(currnode);
		Node otrocur;
		while (!list.isEmpty()) {

			otrocur = list.pop();
			if (otrocur.content == (goal.content)) {
				backtrack(otrocur);
				writeOutput();

				return;
			}
			for (Node nxt : getNeighbors(otrocur)) {
				
					list.push(nxt);

				
			}

		}

		// TODO for assignment12
	}

	/** Pacman uses DFS strategy to solve the maze */


}
