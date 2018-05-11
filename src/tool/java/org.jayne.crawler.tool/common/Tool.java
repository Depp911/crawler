package org.jayne.crawler.tool.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 工具类的bean注解，工具类是可以独立运行的，不依赖与web容器的程序
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Tool {
}
