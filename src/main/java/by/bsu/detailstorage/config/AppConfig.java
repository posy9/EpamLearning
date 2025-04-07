package by.bsu.detailstorage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("by.bsu.detailstorage")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

}
