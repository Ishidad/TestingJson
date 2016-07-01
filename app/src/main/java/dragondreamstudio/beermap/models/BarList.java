package dragondreamstudio.beermap.models;

import java.io.Serializable;
import java.util.List;

public class BarList implements Serializable {

    private List<Bar> bars;

    public List<Bar> getBars(){
        return bars;
    }

}
