package cn.miss.spring.util.loader;

import org.springframework.core.io.Resource;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/10.
 */
public interface ResourceSearcher {

    /**
     * 资源搜索
     * @param location 资源路径
     * @return
     */
    Resource getResource(String location);
}
