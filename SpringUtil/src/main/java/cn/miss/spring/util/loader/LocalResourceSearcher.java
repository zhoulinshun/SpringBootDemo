package cn.miss.spring.util.loader;

import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/10.
 */
public class LocalResourceSearcher implements ResourceSearcher {

    private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/,file:";

    /**
     * {@link ClassRelativeResourceLoader} 相对路径加载
     * 路径以 / 开头 默认会在classpath下查找
     * 路径不以 / 开头 默认会在传入进入的class所在的包下查找
     *
     * {@link FileSystemResourceLoader} 以当前工程所在目录下加载
     */
    private static final List<ResourceLoader> resourceLoader;

    static {
        final ServiceLoader<ResourceLoader> load = ServiceLoader.load(ResourceLoader.class);
        resourceLoader = StreamSupport.stream(load.spliterator(), false).collect(Collectors.toList());
    }


    @Override
    public Resource getResource(String location) {
        for (String searchLocation : getSearchLocation()) {
            String trueLocation = location;
            if (!location.startsWith(searchLocation)) {
                trueLocation = searchLocation + location;
            }
            for (ResourceLoader loader : resourceLoader) {
                final Resource resource = loader.getResource(trueLocation);
                if (Objects.nonNull(resource) && resource.exists()) {
                    return resource;
                }
            }
        }
        return null;
    }

    private Set<String> getSearchLocation() {
        return getSearchLocation("");
    }

    private Set<String> getSearchLocation(String locations) {
        final Set<String> searchLocations = StringUtils.commaDelimitedListToSet(DEFAULT_SEARCH_LOCATIONS);
        if (Objects.nonNull(locations)) {
            searchLocations.addAll(StringUtils.commaDelimitedListToSet(locations));
        }
        return searchLocations;
    }

    public static void main(String[] args) {
        System.out.println(resourceLoader.size());
    }

}
