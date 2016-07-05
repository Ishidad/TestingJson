package dragondreamstudio.beermap.models;

import java.io.Serializable;
import java.util.List;

public class Places implements Serializable {

    private List<String> html_attributions;
    private Place result;

    public List<String> getAttibutions() {
        return html_attributions;
    }

    public Place getPlace() {
        return result;
    }
}
