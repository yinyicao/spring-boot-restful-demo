package com.yyc.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Greeting {

    @Id
    @NotNull(message = "id不能为空")
    private Long id;
    @NotNull(message = "content不能为空")
    @Size(min = 6, max = 25, message = "content长度必须是6-25个字符")
    private  String content;

    public Greeting() {
    }

    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}