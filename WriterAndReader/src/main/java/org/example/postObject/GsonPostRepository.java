package org.example.postObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.GenericRepository;
import org.example.PostStatus;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GsonPostRepository implements GenericRepository<Post, Integer> {
    protected String filePath;
    protected Gson gson;

    public GsonPostRepository(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void save(Post post) {
        Map<Integer, Post> map = readJson();
        if (map != null) {
            map.put(post.getId(), post);
            writerJson(map);
            return;
        }
        map = new HashMap<>();
        map.put(post.getId(), post);
        writerJson(map);
    }

    @Override
    public void delete(Integer id) {
        Post post = findGsonObject(id);
        if (post != null) {
            post.setState(PostStatus.DELETED);
            save(post);
        }
    }

    @Override
    public Post findGsonObject(Integer id) {
        Map<Integer, Post> map = readJson();
        Post postJson = null;
        if (map != null) {
            postJson = map.get(id);
            map.remove(id);
            save(postJson);
        }
        return postJson;
    }

    @Override
    public Map<Integer, Post> readJson() {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<Map<Integer, Post>>() {
            }.getType());
        } catch (IOException e) {
            e.getMessage();
        }
        return new HashMap<>();
    }

    @Override
    public void writerJson(Map<Integer, Post> map) {
        String json = gson.toJson(map);
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(json);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
