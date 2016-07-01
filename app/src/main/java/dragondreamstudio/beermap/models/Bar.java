package dragondreamstudio.beermap.models;

import java.io.Serializable;

public class Bar implements Serializable {

    private String name;
    private String place_id;
    private String logo_src;
    private double lat;
    private double lng;

    public Bar(String name, String place_id, String logo_src, double lat, double lng) {
        this.name = name;
        this.place_id = place_id;
        this.logo_src = logo_src;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getLogo_src() {
        return logo_src;
    }

    public void setLogo_src(String logo_src) {
        this.logo_src = logo_src;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
