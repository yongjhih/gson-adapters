package gson.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GsonTest {

    @Test
    public void testResources() {
        System.out.println(getClass().getResourceAsStream("src/test/resources/news1.json"));
        System.out.println(getClass().getResourceAsStream("test/resources/news1.json"));
        System.out.println(getClass().getResourceAsStream("resources/news1.json"));
        System.out.println(getClass().getResourceAsStream("test/news1.json"));
        System.out.println(getClass().getResourceAsStream("news1.json"));

        System.out.println(getClass().getClassLoader().getResourceAsStream("src/test/resources/news1.json"));
        System.out.println(getClass().getClassLoader().getResourceAsStream("test/resources/news1.json"));
        System.out.println(getClass().getClassLoader().getResourceAsStream("resources/news1.json"));
        System.out.println(getClass().getClassLoader().getResourceAsStream("test/news1.json"));
        System.out.println(getClass().getClassLoader().getResourceAsStream("news1.json"));

        System.out.println(getClass().getClassLoader().getResource("src/test/resources/news1.json"));
        System.out.println(getClass().getClassLoader().getResource("test/resources/news1.json"));
        System.out.println(getClass().getClassLoader().getResource("resources/news1.json"));
        System.out.println(getClass().getClassLoader().getResource("news1.json"));
    }

    @Test
    public void testNews() {
        String json = toString(getClass().getClassLoader().getResourceAsStream("news1.json"));
        Gson gson = new Gson();
        List<NewsEntity> news = gson.fromJson(json, new TypeToken<List<NewsEntity>>(){}.getType());

        assertThat(news.size()).isEqualTo(1);
        assertThat(news.get(0).getTitle()).isEqualTo("Work Policies May Be Kinder, but Brutal Competition Isn’t");

        for (NewsEntity n : news) {
            System.out.println(n.getTitle());
            assertThat(n.getMediaEntity()).isNotNull();
        }

        assertThat(news.get(0).getMediaEntity().size()).isEqualTo(4);

        assertThat(news.get(0).getMediaEntity().get(0).getUrl()).isEqualTo("http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg");

        for (MediaEntity media : news.get(0).getMediaEntity()) {
            System.out.println(media.getUrl());
        }
    }

    @Test(expected=JsonSyntaxException.class)
    public void testNewsEmptyStringException() {
        String json = toString(getClass().getClassLoader().getResourceAsStream("news-empty-media-str.json"));
        new Gson().fromJson(json, new TypeToken<List<NewsEntity>>(){}.getType());
    }

    @Test public void testNewsEmptyStringForMedia() {
        String json = toString(getClass().getClassLoader().getResourceAsStream("news-empty-media-str.json"));
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new EmptyListDeserializer()).create();
        //Gson gson = emptyGson;
        List<NewsEntity> news = gson.fromJson(json, new TypeToken<List<NewsEntity>>(){}.getType());
        assertThat(news.size()).isEqualTo(3);
        for (NewsEntity n : news) {
            assertThat(n.getMediaEntity()).isEmpty();
        }
    }

    @Test public void testNewsNullForMedia() {
        String json = toString(getClass().getClassLoader().getResourceAsStream("news-empty-media-str.json"));
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new NullListDeserializer()).create();
        List<NewsEntity> news = gson.fromJson(json, new TypeToken<List<NewsEntity>>(){}.getType());
        assertThat(news.size()).isEqualTo(3);
        for (NewsEntity n : news) {
            assertThat(n.getMediaEntity()).isNull();
        }
    }

    public static String toString(File file) {
        try {
            return toString(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(InputStream stream) {
        final StringBuilder sb = new StringBuilder();
        String strLine;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            while ((strLine = reader.readLine()) != null) {
                sb.append(strLine);
            }
        } catch (final IOException ignore) {
            // ignore
        }
        return sb.toString();
    }

}
