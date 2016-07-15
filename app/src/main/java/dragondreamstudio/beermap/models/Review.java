package dragondreamstudio.beermap.models;

public class Review {

    private String author_name;
    private String profile_photo_url;
    private String text;
    private int rating;
    private int time;

    public Review(String author_name, String profile_photo_url, String text, int rating, int time) {
        this.author_name = author_name;
        this.profile_photo_url = profile_photo_url;
        this.text = text;
        this.rating = rating;
        this.time = time;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getProfile_photo_url() {
        return profile_photo_url;
    }

    public void setProfile_photo_url(String profile_photo_url) {
        this.profile_photo_url = profile_photo_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
