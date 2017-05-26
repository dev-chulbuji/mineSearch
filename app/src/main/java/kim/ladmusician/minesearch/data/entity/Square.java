package kim.ladmusician.minesearch.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Square {
    boolean isCheck;
    boolean isMine;
    int mineNum;

    public Square(boolean isMine) {
        this.isMine = isMine;
        this.isCheck = false;
    }
}
