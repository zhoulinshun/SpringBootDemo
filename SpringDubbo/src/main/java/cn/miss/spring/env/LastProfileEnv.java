package cn.miss.spring.env;

import cn.miss.spring.util.profile.AbstractEnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Set;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/9.
 */
@Order
public class LastProfileEnv extends AbstractEnvironmentPostProcessor {
    @Override
    public Set<String> getProfiles() {
        return null;
    }
}
