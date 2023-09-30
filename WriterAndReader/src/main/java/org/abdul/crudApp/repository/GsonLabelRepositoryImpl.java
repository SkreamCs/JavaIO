package org.abdul.crudApp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.abdul.crudApp.model.Label;
import org.abdul.crudApp.model.PostStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GsonLabelRepositoryImpl implements LabelRepository {
    private static final String FILE_PATH = "src/main/resources/labels.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Label> getAll() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            return GSON.fromJson(bufferedReader, new TypeToken<List<Label>>() {
            }.getType());
        } catch (IOException e) {
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public Label getById(Integer id) {
        return getAll().stream()
                .filter(n -> n.getId() == id && n.getState() != PostStatus.DELETED)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Label label) {
        List<Label> list = getAll();
        if (list != null) {
            int uniqueId = list.stream()
                    .mapToInt(Label::getId)
                    .max()
                    .orElse(0);
            label.setId(uniqueId + 1);
            label.setState(PostStatus.ACTIVE);
            list.add(label);
        }
        if (list == null) {
            list = new ArrayList<>();
            label.setId(1);
            list.add(label);
        }
        writerAll(list);
    }

    @Override
    public void update(Label label) {
        List<Label> list = getAll();
        int index = IntStream.range(0, list.size())
                .filter(n -> list.get(n).getId() == label.getId())
                .findFirst()
                .orElse(-1);
        if (index != -1) {
            label.setState(PostStatus.UNDER_REVIEW);
            list.set(index, label);
            writerAll(list);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Label label = getById(id);
        if (label != null) {
            label.setState(PostStatus.DELETED);
            save(label);
        }
    }

    protected void writerAll(List<Label> list) {
        String json = GSON.toJson(list);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bufferedWriter.write(json);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
