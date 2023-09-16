package org.example.labelObject;

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

public class GsonLabelRepository implements GenericRepository<Label, Integer> {
    protected String filePath;
    protected Gson gson;

    public GsonLabelRepository(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void save(Label label) {
        Map<Integer, Label> map = readJson();
        if (map != null) {
            map.put(label.getId(), label);
            writerJson(map);
            return;
        }
        map = new HashMap<>();
        map.put(label.getId(), label);
        writerJson(map);
    }

    @Override
    public void delete(Integer id) {
        Label label = findGsonObject(id);
        if (label != null) {
            label.setState(PostStatus.DELETED);
            save(label);
        }
    }

    @Override
    public Label findGsonObject(Integer id) {
        Map<Integer, Label> map = readJson();
        Label labelJson = null;
        if (map != null) {
            labelJson = map.get(id);
            map.remove(id);
            save(labelJson);
        }

        return labelJson;
    }

    @Override
    public Map<Integer, Label> readJson() {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<Map<Integer, Label>>() {
            }.getType());
        } catch (IOException e) {
            e.getMessage();
        }
        return new HashMap<>();
    }

    @Override
    public void writerJson(Map<Integer, Label> map) {
        String json = gson.toJson(map);
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(json);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
