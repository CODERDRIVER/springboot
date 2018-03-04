package com.example.springbootdemo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "blog")
@Data
@NoArgsConstructor
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String theme;   //主题

    private String content;     //内容

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;  //博客类型

    @OneToOne(cascade = CascadeType.ALL,optional = false)       //optional 关联的实体是否为null值，false表示不可以为null
    @JoinColumn(name = "blogger_id")    //关联的表为blogger，其主键为id
    private Blogger  blogger;       //一个博课对应一个博主
    public Blog(Long id)
    {
        this.id = id;
    }

}
