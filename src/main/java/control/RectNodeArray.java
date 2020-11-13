package control;

import globalVar.AppConstants;
import element.RectNode;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RectNodeArray {

    private List<RectNode> rectArr = new ArrayList<RectNode>();

    public RectNodeArray() {
        rectArr.clear();
    }

    public RectNodeArray(int[] values) {
        rectArr.clear();
        for (int i : values) {
            rectArr.add(new RectNode(i));
        }
    }

    public RectNodeArray( RectNodeArray other) {
        rectArr.clear();
        int n = other.size();
        for (int i = 0; i < n; i++) {
            rectArr.add(new RectNode(other.getValueAt(i)));
        }
    }

    public int size() {
        return rectArr.size();
    }

    protected void deleteAt(int index) {
        if(0 <= index && index < rectArr.size()) {
            rectArr.remove(index);
        }
    }

    protected void insertAt(RectNode rn, int index) {
        if(rn == null) {
            System.out.println("You can not add a null element");
            new AlertBox().display("Oop!", "You can not add a null element", "Close");
        } else if(0 <= index && index < rectArr.size()) {
            rectArr.add(index, rn);
            System.out.println("Added successfully");
            new AlertBox().display("Notification", "Added successfully", "Close");
        } else {
            System.out.println("Can not add: out of bound!");
            new AlertBox().display("Oop!", "You can not add at index: " + index, "Close");
        }
    }

    protected void add(int n) {
        if (n > 0) {
            rectArr.add(new RectNode(n));
        }
    }

    public RectNode getAt(int index) {
        if (index >= 0 && index < rectArr.size()) {
            return rectArr.get(index);
        }
        return null;
    }

    public int getValueAt(int index) {
        return getAt(index).getValue();
    }

    public StackPane getViewAt(int index) {
        if (index >= 0 && index < rectArr.size()) {
            return rectArr.get(index).getView();
        }
        return null;
    }

    public RectNode getLastRectNode() {
        if (rectArr.size() == 0) {
            return null;
        }
        return rectArr.get(rectArr.size() - 1);
    }

    public StackPane getLastView() {
        return getLastRectNode().getView();
    }

    protected void clear() {
        rectArr.clear();
    }

    protected void mix() {
        int n = size() / 2;
        for (int i = 0; i < n; i++) {
            randomSwap();
        }
    }

    private void randomSwap() {
        int index01 = Math.abs(new Random().nextInt() % size());
        int index02 = Math.abs(new Random().nextInt() % size());
        while (index02 == index01) {
            index02 = Math.abs(new Random().nextInt() % size());
        }
        swap(index01, index02);
    }

    public void swap(int index01, int index02) {
        Collections.swap(rectArr, index01, index02);
    }

    public int getDistance(int index01, int index02) {
        return (index02 - index01) * (AppConstants.SPACE +  AppConstants.WIDTH_UNIT);
    }

    public List<StackPane> getListView() {
        List<StackPane> paneList = new ArrayList<StackPane>();

        for (RectNode rn : rectArr) {
            paneList.add(rn.getView());
        }

        return paneList;
    }
    /*public Vector2 getNextPosition(RectNode rn) {
        if (rn == null) {
            return new Vector2(0, 0);
        }
        return new Vector2(rn.getPosition().add(new Vector2(AppConstants.SPACE + AppConstants.WIDTH_UNIT, 0)));
    }


    public Vector2 getNextPosition() {
        if (getLastRectNode() == null) {
            return new Vector2(0, 0);
        }
        return getNextPosition(getLastRectNode());
    }
    */

}
