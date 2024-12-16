package edu.just.resource_management_system.pojo;

public class Post {
    private Long id;
    private String title;
    private String excerpt;
    private String content;

    // 构造器、Getter和Setter
    public Post(Long id, String title, String excerpt) {
        this.id = id;
        this.title = title;
        this.excerpt = excerpt;
        this.content = "详细内容：" + title + "的详细内容...";
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getExcerpt() { return excerpt; }
    public String getContent() { return content; }
}
