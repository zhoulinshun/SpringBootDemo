package cn.miss.spring.util.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
public abstract class AbstractEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final String[] activeProfiles = environment.getActiveProfiles();
        final Collection<String> profiles = getProfiles();
        if (CollectionUtils.isEmpty(profiles)) {
            return;
        }
        if (activeProfiles == null || activeProfiles.length == 0) {
            final String[] defaultProfiles = environment.getDefaultProfiles();
            if (defaultProfiles != null) {
                for (String defaultProfile : defaultProfiles) {
                    environment.addActiveProfile(defaultProfile);
                }
            }
            profiles.forEach(environment::addActiveProfile);
            return;
        }
        for (String activeProfile : activeProfiles) {
            if (profiles.contains(activeProfile)) {
                return;
            }
        }
        profiles.forEach(environment::addActiveProfile);
    }

    public abstract Set<String> getProfiles();

}
