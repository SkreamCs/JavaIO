package org.example.writerObject;

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

public class GsonWriterRepository implements GenericRepository<Writer, Integer> {
    protected String pathJson;
    protected Gson gson;

    public GsonWriterRepository(String pathJson) {
        this.pathJson = pathJson;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }


    @Override
    public void save(Writer gsonObject) {
        Map<Integer, Writer> map = readJson();
        if (map != null) {
            map.put(gsonObject.getId(), gsonObject);
            writerJson(map);
            return;
        }
        map = new HashMap<>();
        map.put(gsonObject.getId(), gsonObject);
        writerJson(map);
    }

    @Override
    public void delete(Integer id) {
        Writer writer = findGsonObject(id);
        if (writer != null) {
            writer.setState(PostStatus.DELETED);
            save(writer);
        }
    }

    @Override
    public Writer findGsonObject(Integer id) {
        Map<Integer, Writer> map = readJson();
        Writer objectJson = null;
        if (map != null) {
            objectJson = map.get(id);
            map.remove(id);
            save(objectJson);
        }

        return objectJson;
    }

    @Override
    public Map<Integer, Writer> readJson() {
        try (FileReader reader = new FileReader(pathJson);) {
            return gson.fromJson(reader, new TypeToken<Map<Integer, Writer>>() {
            }.getType());
        } catch (IOException e) {
            e.getMessage();
        }
        return new HashMap<>();
    }


    @Override
    public void writerJson(Map<Integer, Writer> writers) {
        String json = gson.toJson(writers);
        try (FileWriter writer = new FileWriter(pathJson);) {
            writer.write(json);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}



