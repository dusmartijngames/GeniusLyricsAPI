package genius;

import org.json.JSONObject;

public class Artist {

    private long id;
    private String imageUrl;
    private String name;
    private String slug;
    private String url;

    public Artist(JSONObject jRoot) {
        this.id = jRoot.getLong("id");
        this.imageUrl = jRoot.getString("image_url");
        this.name = jRoot.getString("name");
        this.slug = jRoot.getString("slug");
        this.url = jRoot.getString("url");
    }

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getUrl() {
        return url;
    }

}
