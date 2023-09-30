package org.abdul.crudApp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.abdul.crudApp.model.PostStatus;
import org.abdul.crudApp.model.Writer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private static final String FILE_PATH = "src/main/resources/writers.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Writer> getAll() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            return GSON.fromJson(bufferedReader, new TypeToken<List<Writer>>() {
            }.getType());
        } catch (IOException e) {
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public Writer getById(Integer id) {
        return getAll().stream()
                .filter(n -> n.getId() == id && n.getState() != PostStatus.DELETED)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Writer writer) {
        List<Writer> list = getAll();
        int index = IntStream.range(0, list.size())
                .filter(n -> list.get(n).getId() == writer.getId() && writer.getState() != PostStatus.DELETED)
                .findFirst()
                .orElse(-1);
        if (index != -1) {
            writer.setState(PostStatus.UNDER_REVIEW);
            list.set(index, writer);
            writerAll(list);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Writer writer = getById(id);
        if (writer != null) {
            writer.setState(PostStatus.DELETED);
            save(writer);
        }
    }

    @Override
    public void save(Writer writer) {
        List<Writer> list = getAll();
        if (list != null) {
            int uniqueId = list.stream()
                    .mapToInt(Writer::getId)
                    .max()
                    .orElse(0);
            writer.setId(uniqueId + 1);
            writer.setState(PostStatus.ACTIVE);
            list.add(writer);
        }
        if (list == null) {
            list = new ArrayList<>();
            writer.setId(1);
            list.add(writer);
        }
        writerAll(list);
    }

    public void writerAll(List<Writer> list) {
        String json = GSON.toJson(list);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bufferedWriter.write(json);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}






