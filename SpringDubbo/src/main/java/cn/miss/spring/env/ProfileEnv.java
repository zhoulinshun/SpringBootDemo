package cn.miss.spring.env;

import cn.miss.spring.util.profile.AbstractEnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.yaml.snakeyaml.*;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@Order(Integer.MIN_VALUE)
public class ProfileEnv extends AbstractEnvironmentPostProcessor {
    private static final String PROFILE = "dubbo";

    @Override
    public Set<String> getProfiles() {
        try {
//            final List<PropertySource<?>> load = new YamlPropertySourceLoader().load("", null);
//            final PropertiesPropertySourceLoader propertiesPropertySourceLoader = new PropertiesPropertySourceLoader();
//            propertiesPropertySourceLoader.load()
//            load.get(0).get
//            final Resource resource = new DefaultResourceLoader().getResource("");
//            final File file = resource.getFile();
//            YamlJsonParser

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.singleton(PROFILE);
    }
}
