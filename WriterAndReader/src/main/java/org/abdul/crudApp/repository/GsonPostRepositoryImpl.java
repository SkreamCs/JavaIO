package org.abdul.crudApp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.abdul.crudApp.model.Post;
import org.abdul.crudApp.model.PostStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GsonPostRepositoryImpl implements PostRepository {
    private static final String FILE_PATH = "src/main/resources/posts.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Post> getAll() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            return GSON.fromJson(bufferedReader, new TypeToken<List<Post>>() {
            }.getType());
        } catch (IOException e) {
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public Post getById(Integer id) {
        return getAll().stream()
                .filter(n -> n.getId() == id && n.getState() != PostStatus.DELETED)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Post post) {
        List<Post> list = getAll();
        int index = IntStream.range(0, list.size())
                .filter(n -> list.get(n).getId() == post.getId() && post.getState() != PostStatus.DELETED)
                .findFirst()
                .orElse(-1);
        if (index != -1) {
            post.setState(PostStatus.UNDER_REVIEW);
            list.set(index, post);
            writerAll(list);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Post post = getById(id);
        if (post != null) {
            post.setState(PostStatus.DELETED);
            save(post);
        }
    }

    @Override
    public void save(Post post) {
        List<Post> list = getAll();
        if (list != null) {
            int uniqueId = list.stream()
                    .mapToInt(Post::getId)
                    .max()
                    .orElse(0);
            post.setId(uniqueId + 1);
            post.setState(PostStatus.ACTIVE);
            list.add(post);
        }
        if (list == null) {
            list = new ArrayList<>();
            post.setId(1);
            list.add(post);
        }
        writerAll(list);
    }

    public void writerAll(List<Post> list) {
        String json = GSON.toJson(list);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bufferedWriter.write(json);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
