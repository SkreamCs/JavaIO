package org.abdul.crudApp.model;

import java.util.List;

public class Post {
    private int id;
    private String content;
    private String created;
    private String updated;
    private List<Label> labels;
    private PostStatus state;


    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public void setState(PostStatus state) {
        this.state = state;
    }

    public PostStatus getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public Post(String content, String created, String updated, List<Label> labels) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", labels=" + labels +
                ", state=" + state +
                '}';
    }
}
