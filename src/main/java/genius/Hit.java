package genius;

import core.GLA;
import org.json.JSONObject;

public class Hit {

    private long id;
    private String title;
    private String titleWithFeatured;
    private String url;
    private String imageUrl;
    private String thumbnailUrl;
    private Artist artist;
    private GLA gla;

    public Hit(JSONObject jRoot, GLA gla) {
        this.id = jRoot.getLong("id");
        this.title = jRoot.getString("title");
        this.titleWithFeatured = jRoot.getString("title_with_featured");
        this.url = jRoot.getString("url");
        this.imageUrl = jRoot.getString("header_image_url");
        this.thumbnailUrl = jRoot.getString("song_art_image_thumbnail_url");
        this.artist = new Artist(jRoot.getJSONObject("primary_artist"));
        this.gla = gla;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleWithFeatured() {
        return titleWithFeatured;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public String fetchLyrics() {
        return new LyricsParser(this.gla).get(this.id + "");
    }

}
