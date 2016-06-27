package gson.adapter;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity {
    private static final String TAG = NewsEntity.class.getSimpleName();
    private String title;
    private String summary;
    private String articleUrl;
    private String byline;
    private String publishedDate;
    @SerializedName("multimedia")
    private List<MediaEntity> mediaEntityList;

    public NewsEntity() {
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<MediaEntity> getMediaEntity() {
        return mediaEntityList;
    }
}
