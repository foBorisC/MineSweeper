package MineSweeper;

import javafx.scene.control.Button;

public class Tile extends Button {
    private TileState state;
    private boolean isMine;
    private int neighbouringMinescount;

    public Tile(){
        this.state=TileState.FOG;
        this.isMine=false;
    }

    public void updateDisplay(){
        switch (state){
            case FOG:
                this.setText("");
                this.setStyle("-fx-background-color: #a89595;");
                break;
            case REVEALED:
                if (isMine) {
                    this.setText("M");
                    this.setStyle("-fx-background-color: red;");
                } else {
                    this.setText(String.valueOf(neighbouringMinescount));
                    this.setStyle("-fx-background-color: green;");
                }
                break;
            case FLAGGED:
                this.setText("F");
                break;
        }
    }
    public TileState getState() {
        return state;
    }
    public void setState(TileState state) {
        this.state = state;
        updateDisplay();
    }
    public boolean isMine() {
        return isMine;
    }
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }
    public int getNeighbouringMinesCount(){
        return neighbouringMinescount;
    }
    public void setNeighbouringMinescount(int i){
        this.neighbouringMinescount=i;
    }

}
