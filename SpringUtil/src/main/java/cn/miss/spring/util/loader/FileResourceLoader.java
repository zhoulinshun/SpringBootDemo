package cn.miss.spring.util.loader;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Author: zhoulinshun
 * @Description: 资源文件加载器
 * @Date: Created in 2018/10/9.
 */
public class FileResourceLoader {

    private static final List<PropertySourceLoader> propertySourceLoaders;
    private static final ResourceSearcher resourceSearcher;

    static {
        final ServiceLoader<PropertySourceLoader> load = ServiceLoader.load(PropertySourceLoader.class);
        propertySourceLoaders = StreamSupport.stream(load.spliterator(), false).collect(Collectors.toList());

        final ServiceLoader<ResourceSearcher> resourceSearcherServiceLoader = ServiceLoader.load(ResourceSearcher.class);
        final Iterator<ResourceSearcher> iterator = resourceSearcherServiceLoader.iterator();
        if (iterator.hasNext()) {
            resourceSearcher = iterator.next();
        } else {
            throw new IllegalArgumentException();
        }
    }


    public static Map<String, Object> propertyLoad(String location) {
        final Resource resource = resourceSearcher.getResource(location);
        if (Objects.isNull(resource)) {
            return Collections.emptyMap();
        }
        for (PropertySourceLoader propertySourceLoader : propertySourceLoaders) {
            final String[] fileExtensions = propertySourceLoader.getFileExtensions();
            if (matchExtension(location, fileExtensions)) {
                try {
                    final List<PropertySource<?>> propertySourceList = propertySourceLoader.load("", resource);
                    return propertySourceList.stream().map(MapPropertySource.class::cast).map(MapPropertySource::getSource).collect(HashMap::new, HashMap::putAll, HashMap::putAll);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] textLoad(String location) {
        try {
            final Resource resource = resourceSearcher.getResource(location);
            if (Objects.isNull(resource)) {
                return null;
            }
            final File file = resource.getFile();
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean matchExtension(String location, String[] fileExtensions) {
        final String extension = location.substring(location.lastIndexOf(".") + 1);
        for (String fileExtension : fileExtensions) {
            if (Objects.equals(fileExtension, extension)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(propertySourceLoaders.size());
    }

}
