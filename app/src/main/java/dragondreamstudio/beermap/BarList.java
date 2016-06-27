package dragondreamstudio.beermap;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BarList {

    @SerializedName("bars")
    List<Bar> bars;

    public List<Bar> getBars(){
        return bars;
    }
}
