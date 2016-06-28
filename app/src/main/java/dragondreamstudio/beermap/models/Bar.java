package dragondreamstudio.beermap.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bar implements Serializable {

    //@SerializedName("name")
    private String name;
    //@SerializedName("place_id")
    private String place_id;
    //@SerializedName("logo_src")
    private String logo_src;

    public Bar(String name, String id, String logo_src) {
        this.name = name;
        this.place_id = id;
        this.logo_src = logo_src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return place_id;
    }

    public void setId(String id) {
        this.place_id = id;
    }

    public String getLogo_src() {
        return logo_src;
    }

    public void setLogo_src(String logo_src) {
        this.logo_src = logo_src;
    }
}
