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

    @Override
    public void start(Stage stage){
        int ScreenHeight=1200;
        int ScreenWidth=800;

        int rowCount=10;
        int colCount=10;
        int tileCount=rowCount*colCount;
        int mineCount=10;

        HashSet<Integer> mines=generateMines(tileCount, mineCount);
        int tileNumber = 1;
        GridPane grid = new GridPane();
        for (int row = 0; row < rowCount; row++){
            for(int col = 0; col < colCount; col++){
                Tile tile = new Tile();
                tile.setPrefSize((double) ScreenHeight /10, (double) ScreenWidth /10);

                tile.setOnAction(event -> {

                    tile.setState(TileState.REVEALED);

                });

                tile.setMine(mines.contains(tileNumber));
                tileNumber++;
                grid.add(tile, col, row);
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
}
