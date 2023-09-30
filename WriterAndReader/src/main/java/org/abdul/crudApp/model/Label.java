package org.abdul.crudApp.model;

public class Label {
    private int id;
    private String name;
    private PostStatus state;

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
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

    public Label(String name) {
        this.name = name;
    }
}
