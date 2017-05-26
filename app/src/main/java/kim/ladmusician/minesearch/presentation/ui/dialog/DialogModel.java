package kim.ladmusician.minesearch.presentation.ui.dialog;

public class DialogModel {
    public String title;
    public String message;
    public String btnTxt;

    public DialogModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBtnTxt() {
        return btnTxt;
    }

    public void setBtnTxt(String btnTxt) {
        this.btnTxt = btnTxt;
    }
}
