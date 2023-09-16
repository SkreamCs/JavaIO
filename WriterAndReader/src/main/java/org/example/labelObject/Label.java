package org.example.labelObject;

import org.example.PostStatus;

import java.util.Objects;

public class Label {
    int id;
    String name;
    PostStatus state;

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id && Objects.equals(name, label.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(PostStatus state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PostStatus getState() {
        return state;
    }

    public Label(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
