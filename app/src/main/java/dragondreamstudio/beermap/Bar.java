package dragondreamstudio.beermap;

public class Bar {

    private String name;
    private String place_id;
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
