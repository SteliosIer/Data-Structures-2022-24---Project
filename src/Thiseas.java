import java.io.BufferedReader;
import java.io.FileReader;
import java.util.NoSuchElementException;

public class Thiseas {
    // Contains coordinates of traversed nodes.
    static StringStackImpl<Point> pathToExit = new StringStackImpl<>();
    
    public static Point solveLabyrinth(String[][] labyrinth, int x, int y, String[][] visitedPoints) {
        //System.out.println("Current position: " + x + ", " + y);
        
        if (goUp(labyrinth,x,y))
        {
        	//System.out.println("We can go up");
            if (!visitedPoints [x-1][y].equals("V"))
            {
                 //System.out.println("GO UP!");
                 pathToExit.push(new Point(x-1,y));
                 visitedPoints [x-1][y] = "V";
                 Point newPoint = new Point(x-1, y);
                 return newPoint;
            }
        }
        
        if (goDown(labyrinth,x,y))
        {
        	//System.out.println("We can go down");
            if (!visitedPoints[x+1][y].equals("V"))
            {
            	 //System.out.println("GO DOWN!");
                 pathToExit.push(new Point(x+1,y));
                 visitedPoints[x+1][y] = "V";
                 Point newPoint = new Point(x+1, y);
                 return newPoint;
            }
        }
        
        if (goLeft(labyrinth,x,y))
        {
        	//System.out.println("We can go left");
            if (!visitedPoints[x][y-1].equals("V"))
            {
            	//System.out.println("GO LEFT!");
                pathToExit.push(new Point(x,y-1));
              	visitedPoints[x][y-1] = "V";
              	Point newPoint = new Point(x, y-1);
              	return newPoint;
            }
        }

        if (goRight(labyrinth,x,y))
        {
        	//System.out.println("We can go right");
            if (!visitedPoints[x][y+1].equals("V"))
            {
            	//System.out.println("GO RIGHT!");
                pathToExit.push(new Point(x,y+1));
               	visitedPoints[x][y+1] = "V";
                Point newPoint = new Point(x, y+1);
                return newPoint;
            }
        }

        Point newPoint = new Point(-1, -1);
        return newPoint;
    }

    static boolean goUp(String[][] labyrinth, int x, int y)
    {
        try {
            return labyrinth[x-1][y].equals("0");
        } catch (ArrayIndexOutOfBoundsException e) {
        	//System.out.println("if you go up you are out of bounds");
            return false;
        }
    }
    
    static boolean goDown(String[][] labyrinth, int x, int y)
    {
        try {
            return labyrinth[x+1][y].equals("0");
        } catch (ArrayIndexOutOfBoundsException e) {
        	//System.out.println("if you go down you are out of bounds");
            return false;
        }
    }
    
    static boolean goRight(String[][] labyrinth, int x, int y)
    {
        try {
            return labyrinth[x][y+1].equals("0");
        } catch (ArrayIndexOutOfBoundsException e) {
        	//System.out.println("if you go right you are out of bounds");
            return false;
        }
    }
    
    static boolean goLeft(String[][] labyrinth, int x, int y)
    {
        try {
            return labyrinth[x][y-1].equals("0");
        } catch (ArrayIndexOutOfBoundsException e) {
        	//System.out.println("if you go left you are out of bounds");
            return false;
        }
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();

            // the labyrinth's dimensions are on the first line
            int rows = Integer.parseInt(String.valueOf(firstLine.charAt(0)));
            int columns = Integer.parseInt(String.valueOf(firstLine.charAt(1)));
            String Labyrinth[][] = new String[rows][columns];

            // the starting point's coordinates are on the second line
            int startX = Integer.parseInt(String.valueOf(secondLine.charAt(0)));
            int startY = Integer.parseInt(String.valueOf(secondLine.charAt(1)));

            // initialize labyrinth
            for (int i = 0; i < rows; i++) {
                String row = reader.readLine();
                for (int j = 0; j < columns; j++) {
                    Labyrinth[i][j] = String.valueOf(row.charAt(j));
                }
            }
            
            String[][] visitedPoints = new String[rows][columns];
            // initialize visitedPoints
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    visitedPoints[i][j] = "U";
                }
            }
            
            if (!Labyrinth[startX][startY].equals("E")) {
                System.out.println("Cannot start from position (" + startX + ", " + startY + ")");
            } else {
                //System.out.println("Starting at position: " + startX + ", " + startY);
                //System.out.println("Push Point: " + startX + ", " + startY);
                pathToExit.push(new Point(startX, startY));
                int x = startX;
                int y = startY;
                try {
	                while (true) {
	                    Point currentPoint = solveLabyrinth(Labyrinth, x, y,visitedPoints);
	                    if (currentPoint.x == -1 && currentPoint.y == -1)
	                    {
	                    	//System.out.println("Start Backtracking");
	                    	while (true)
	                    	{
	                    		pathToExit.pop();
	                    		currentPoint = solveLabyrinth(Labyrinth, pathToExit.peek().x, pathToExit.peek().y,visitedPoints);
		                    	if (currentPoint.x != -1 && currentPoint.y != -1)
		                    	{
		                    		//System.out.println("Stop Backtracking");
		                    		break;
		                    	}
	                    	}
	                    }
	                   
	                    x = currentPoint.x;
	                    y = currentPoint.y;
	                    if ((x == rows - 1 || y == columns - 1 || x == 0 || y == 0) && !Labyrinth[x][y].equals("E"))
	                    {
	                       System.out.println("We reached final node");
	                       break;
	                    }
	                }
	                System.out.println("Exit found on point " + pathToExit.peek().toString());
                } catch (NoSuchElementException e) {
                    System.out.println("ERROR: No Exit found..");
                }
            }


            reader.close();
        } catch (Exception e) {
            System.out.println("ERROR: Exiting...");
            e.printStackTrace();
        }
    }
}


