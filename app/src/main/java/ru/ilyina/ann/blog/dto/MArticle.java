package ru.ilyina.ann.blog.dto;

/**
 * Created by anjytka on 13.04.17.
 */

public class MArticle {

    public MArticle() {
    }

    private Long id;

    private String title;

    private String content;

    private String imageName;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() { return content; }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
