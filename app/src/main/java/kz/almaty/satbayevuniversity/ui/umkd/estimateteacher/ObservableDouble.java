package kz.almaty.satbayevuniversity.ui.umkd.estimateteacher;

import java.util.Observable;

public class ObservableDouble extends Observable{
    public double val;

    public double getVal(){
        return val;
    }

    public void setVal(double val){
        this.val = val;
        this.setChanged();
        this.notifyObservers(val);
    }
}
