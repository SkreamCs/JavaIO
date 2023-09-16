package org.example.postObject;

import org.example.PostStatus;
import org.example.labelObject.Label;

import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private String content;
    private String created;
    private String updated;
    private List<Label> labels;
    private PostStatus state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(content, post.content) && Objects.equals(created, post.created) && Objects.equals(updated, post.updated) && Objects.equals(labels, post.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, created, updated, labels);
    }


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

    public Post(int id, String content, String created, String updated, List<Label> labels) {
        this.id = id;
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
