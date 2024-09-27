package genius;

import core.GLA;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;

public class SongSearch {

    private final GLA gla;
    private int status;
    private int nextPage;
    private LinkedList<Hit> hits = new LinkedList<>();

    public SongSearch(GLA gla, String query) throws IOException {
        this.gla = gla;
        query = URLEncoder.encode(query, "UTF-8" /* As suggested by User DimitrisStaratzis, for consistent encoding on all devices*/);
        try {
            URI uri = new URI("https://genius.com/api/search/song?page=1&q=" + query);
            request(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public SongSearch(GLA gla, String query, int page) throws IOException {
        this.gla = gla;
        query = URLEncoder.encode(query, "UTF-8");
        try {
            URI uri = new URI("https://genius.com/api/search/song?page=" + page + "&q=" + query);
            request(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void request(URI uri) throws IOException {
        try {
            HttpURLConnection con = this.gla.getHttpManager().getConnection(uri.toURL());
            String result = this.gla.getHttpManager().executeGet(con);
            parse(new JSONObject(result));
        } catch (MalformedURLException e) {
            throw new InternalError();
        }
    }

    private void parse(JSONObject jRoot) {
        this.status = jRoot.getJSONObject("meta").getInt("status");
        JSONObject response = jRoot.getJSONObject("response");
        if (!response.isNull("next_page")) {
            this.nextPage = response.getInt("next_page");
        }
        JSONObject section = response.getJSONArray("sections").getJSONObject(0);
        JSONArray hits = section.getJSONArray("hits");
        for (int i = 0; i < hits.length(); i++) {
            JSONObject hitRoot = hits.getJSONObject(i).getJSONObject("result");
            this.hits.add(new Hit(hitRoot, this.gla));
        }
    }

    public GLA getGla() {
        return gla;
    }

    public int getStatus() {
        return status;
    }

    public int getNextPage() {
        return nextPage;
    }

    public LinkedList<Hit> getHits() {
        return hits;
    }





}
