import java.util.Random;

public class AgainstComputer { //version from "Battleship"
	static final int SIZE = 5;
	
	public static void main(String[] args){
		System.out.println("hello");
		char[][] compGrid = {{'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}};
		//clearArray(compGrid);
		printArray(compGrid);
		for(int shipNum = SIZE - 1; shipNum >= 1; shipNum++){
			placeShip(shipNum, compGrid);
		}
		System.out.println();
		printArray(compGrid);
	}
	
	public static void placeShip(int height, char[][] compGrid){
		Random rand = new Random();
		boolean shipPlaced = false;
		while(shipPlaced == false){
			int col = rand.nextInt(SIZE), row = rand.nextInt(SIZE);
			int direction = -1;
			if(compGrid[row][col] == 'O'){ //no ship exists on the starting spot
				if(height == 1){
					compGrid[row][col] = 'X';
					shipPlaced = true;
					break;
				}
				
				if(row < height - 1){ //cannot go up
					if(col < height - 1){
						direction = rand.nextInt(2) + 1; //1 or 2
					} else if(col >= height - 1 && col <= SIZE - height){
						direction = rand.nextInt(3) + 1; //1, 2, or 3
					} else {
						direction = rand.nextInt(2) + 2; //2 or 3
					}
				} else if(row > SIZE - height){ //cannot go down
					if(col < height - 1){
						direction = rand.nextInt(2); //0 or 1
					} else if(col >= height - 1 && col <= SIZE - height){
						while(direction == -1 || direction == 2){
							direction = rand.nextInt(4); //0, 1, or 3 (if its 2, it will try again)
						}
					} else {
						direction = rand.nextInt(2) * 3; //0 or 3
					}
				} else { //in the middle section where you can go up or down, height - 1 <= row <= SIZE - height
					if(col < height - 1){
						direction = rand.nextInt(3); //0, 1, or 2
					} else if(col >= height - 1 && col <= SIZE - height){
						direction = rand.nextInt(4); //any direction
					} else {
						while(direction == -1 || direction == 1){
							direction = rand.nextInt(4); //0, 2, or 3 (if its 1, it will try again)
						}
					}
				}
				
				int vertical = 0, horizontal = 0;
				if(direction == 0){
					vertical = 1; //up = 0
				} else if(direction == 1){
					horizontal = 1; //right = 1
				} else if(direction == 2){
					vertical = -1; //down = 2
				} else /*direction == 3*/{
					horizontal = -1; //left = 3
				}
				
				boolean canPlace = true;
				for(int i = 1; i < height; i++){ //checking if you can place the ship
					if(compGrid[row + i*vertical][col + i*horizontal] == 'X'){ //there is a different ship where you want to put yours
						canPlace = false;
						break;
					}
				}
				if(canPlace == true){
					for(int i = 1; i < height; i++){
						compGrid[row + i*vertical][col + i*horizontal] = 'X';
					}
					shipPlaced = true;
				}
			}
		}
	}

	public static void clearArray(char[][] array){
		for(int r = 0; r < array.length; r++){
			for(int c = 0; c < array[0].length; c++){
				array[r][c] = 'O';
			}
		}
	}
	
	public static void printArray(char[][]array){
		for(int r = 0; r < array.length; r++){
			System.out.print("[" + array[r][0]);
			for(int c = 1; c < array[r].length; c++){
				System.out.print(" " + array[r][c]);
			}
			System.out.println("]");
		}
	}
}