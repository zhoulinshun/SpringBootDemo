package cn.miss.spring.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/20.
 */
@Data
public class Customer implements Serializable {

    private Date date;

    private LocalDate localDate;

    private LocalDateTime localDateTime;



}
