package com.xhjk.core.interfaces.cardmanagement.vcard;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.factory.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 入口类
 */
@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class VcardApplication {


    public static void main(String[] args) {
        SpringApplication.run(VcardApplication.class, args);
    }

    // 加载YML格式自定义配置文件
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();

        Resource[] tmpConfigFileResourceArray = new Resource[]{new ClassPathResource("config/exceptionCustomConfig.yml"),
                new ClassPathResource("config/commonSpecification.yml"),
                new ClassPathResource("config/dueBeforeBusinessWorkConfig.yml"),
                new ClassPathResource("config/WSXHJKConfig.yml")};

        if (tmpConfigFileResourceArray != null && tmpConfigFileResourceArray.length > 0) {
            yaml.setResources(tmpConfigFileResourceArray);//File引入
        }

        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
