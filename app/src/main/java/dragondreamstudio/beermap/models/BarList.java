package dragondreamstudio.beermap.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BarList implements Serializable {

    //@SerializedName("bars")
    List<Bar> bars;

    public List<Bar> getBars(){
        return bars;
    }
}
