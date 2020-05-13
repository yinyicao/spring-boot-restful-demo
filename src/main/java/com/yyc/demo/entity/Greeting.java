package com.yyc.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Greeting {

    @Id
    @NotNull(message = "id不能为空")
    private Long id;
    @NotNull(message = "content不能为空")
    @Size(min = 6, max = 25, message = "content长度必须是6-25个字符")
    private  String content;
}