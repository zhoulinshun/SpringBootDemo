package cn.miss.spring.util.loader;

import org.springframework.core.io.ClassRelativeResourceLoader;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/10.
 */
public class ClassRelativeResourceLoaderWrap extends ClassRelativeResourceLoader {

    public ClassRelativeResourceLoaderWrap() {
        this(ClassRelativeResourceLoaderWrap.class);
    }

    /**
     * Create a new ClassRelativeResourceLoader for the given class.
     *
     * @param clazz the class to load resources through
     */
    public ClassRelativeResourceLoaderWrap(Class<?> clazz) {
        super(clazz);
    }
}
