package kim.ladmusician.minesearch.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kim.ladmusician.minesearch.data.entity.Square;
import lombok.Getter;

public class SquareManager {
    private final int MINE_NUM = 10;
    private final int ROW = 10;
    private final int COL = 10;

    @Getter
    private List<Square> squareList = null;
    @Getter
    private boolean isSuccess = false;
    private int checkCount = 0;
    private Square[][] squareArr;
    private static SquareManager instance = null;

    public static SquareManager getInstance() {
        if (instance == null)
            instance = new SquareManager();
        return instance;
    }

    public void setCheck(int position) {
        if (!squareList.get(position).isCheck()) {
            this.squareList.get(position).setCheck(true);
            checkCount++;

            if (checkCount == 90)
                isSuccess = true;
        }
    }

    public SquareManager() {
        setInitState();
    }

    public void setAllSqureOpen() {
        for(Square each : squareList) {
            each.setCheck(true);
        }
    }

    public void setInitState() {
        isSuccess = false;
        checkCount = 0;
        this.squareArr = new Square[ROW][ROW];
        this.squareList = new ArrayList<>();

        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                squareArr[row][col] = new Square(false);
            }
        }

        setRandomMine();
        setNotMineArea();

        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                squareList.add(squareArr[row][col]);
            }
        }
    }

    public void setRandomMine() {
        Random xData = new Random();
        Random yData = new Random();

        for(int i=0; i< MINE_NUM; i++) {
            int xAxis = xData.nextInt(ROW);
            int yAxis = yData.nextInt(COL);

            squareArr[xAxis][yAxis].setMine(true);
        }
    }

    public void setNotMineArea() {
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                if(!squareArr[row][col].isMine()) {
                    squareArr[row][col].setMineNum(getMineCount(row, col));
                }
            }
        }
    }

    private int getMineCount(int row, int col) {
        int minCount = 0;

        if(checkMine(row - 1, col - 1))
            minCount++;
        if(checkMine(row - 1, col))
            minCount++;
        if(checkMine(row - 1, col + 1))
            minCount++;
        if(checkMine(row, col + 1))
            minCount++;
        if(checkMine(row + 1, col + 1))
            minCount++;
        if(checkMine(row + 1, col))
            minCount++;
        if(checkMine(row + 1, col - 1))
            minCount++;
        if(checkMine(row, col - 1))
            minCount++;

        return minCount;
    }

    public boolean checkMine(int row, int col) {
        if(row < 0 || row >= ROW || col < 0 || col >= COL)
            return false;
        else
            return squareArr[row][col].isMine();
    }
}
