package cn.miss.spring.util.loader;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/9.
 */
public class YmlResourceLoader {

    static final YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
    static final PropertiesPropertySourceLoader propertiesPropertySourceLoader = new PropertiesPropertySourceLoader();

    /**
     * 相对路径加载
     * 路径以 / 开头 默认会在classpath下查找
     * 路径不以 / 开头 默认会在传入进入的class所在的包下查找
     */
    private static ClassRelativeResourceLoader classRelativeResourceLoader = new ClassRelativeResourceLoader(YmlResourceLoader.class);

    /**
     * 以当前工程所在目录下加载
     */
    private static FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();

    private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/";

    public static void load(String location) {
        final Resource resource = classRelativeResourceLoader.getResource(location);

//        StandardServletEnvironment
//        yamlPropertySourceLoader.load("",);
    }

    public static String textLoader(String location) {
        try {
            for (String searchLocation : getSearchLocation()) {
                String trueLocation = location;
                if (!location.startsWith(searchLocation)) {
                    trueLocation = searchLocation + location;
                }
                final Resource resource = classRelativeResourceLoader.getResource(trueLocation);
                if (resource == null || !resource.exists()) {
                    continue;
                }
                final File file = resource.getFile();
                return new String(Files.readAllBytes(file.toPath()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Set<String> getSearchLocation() {
        return getSearchLocation("");
    }

    private static Set<String> getSearchLocation(String locations) {

        final Set<String> searchLocations = StringUtils.commaDelimitedListToSet(DEFAULT_SEARCH_LOCATIONS);
        if (Objects.nonNull(locations)) {
            searchLocations.addAll(StringUtils.commaDelimitedListToSet(locations));
        }
        return searchLocations;
    }

    public static void main(String[] args) {
        final Resource resource = fileSystemResourceLoader.getResource("tt.txt");
        System.out.println(textLoader("classpath:/tt.txt"));
    }

}
