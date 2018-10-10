package cn.miss.spring.dubbo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@Data
public class HelloBean implements Serializable {
    private String name;

    private String context;

}
