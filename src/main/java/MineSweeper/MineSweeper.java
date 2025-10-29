package MineSweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;

//Main class for the MineSweeper game
//Start with mvn javafx:run in cmd

public class MineSweeper extends Application {

    private Tile[][] tiles;

    @Override
    public void start(Stage stage){
        int ScreenHeight=1200;
        int ScreenWidth=800;

        int rowCount=10;
        int colCount=10;
        int tileCount=rowCount*colCount;
        int mineCount=10;
        tiles = new Tile[rowCount][colCount];

        HashSet<Integer> mines=generateMines(tileCount, mineCount);
        int tileNumber = 0;
        GridPane grid = new GridPane();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Tile tile = new Tile();
                tile.setPrefSize((double) ScreenHeight / 10, (double) ScreenWidth / 10);
                tile.setMine(mines.contains(tileNumber));
                tileNumber++;
                grid.add(tile, col, row);
                tiles[row][col] = tile;
            }
        }

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Tile tile = tiles[row][col];
                Tile[] neighbours = getNeighbours(row, col);
                int neighbouringMinesCount = 0;
                for (Tile neighbour : neighbours) {
                    if (neighbour != null && neighbour.isMine()) {
                        neighbouringMinesCount++;
                    }
                }
                tile.setNeighbouringMinescount(neighbouringMinesCount);

                tile.setOnAction(event -> {
                    tile.setState(TileState.REVEALED);
                });
            }
        }


        Scene scene = new Scene(grid, ScreenHeight, ScreenWidth);
        stage.setTitle("MineSweeper");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args){
        launch(args);
    }

    public static HashSet<Integer> generateMines(int tileCount, int mineCount){
        HashSet<Integer> mines = new HashSet<>();
        Random random = new Random();

        while(mines.size() < mineCount){
            int minePosition = random.nextInt(tileCount);
            mines.add(minePosition);
        }
        return mines;
    }

    public Tile[] getNeighbours (int row, int col){

        /* *
        Direction in the form of [row] [column]
        * */

        int[][] directions ={
                {-1,-1}, {-1,0}, {-1,1},
                {0,-1},          {0,1},
                {1,-1}, {1,0},   {1,1}
        };
        Tile[] neighbours = new Tile[8];
        int index = 0;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0 && newRow < tiles.length && newCol >= 0 && newCol < tiles[0].length) {
                neighbours[index] = tiles[newRow][newCol];
            } else {
                neighbours[index] = null;
            }
            index++;
        }
        return neighbours;
    }
}
