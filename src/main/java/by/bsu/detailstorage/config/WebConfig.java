package by.bsu.detailstorage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.bsu.detailstorage.controller")
public class WebConfig
{
    //Here, you configure beans related to web application context
}
