package ru.job4j.dream.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class PropertyFactory {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyFactory.class.getName());

    /** Фабрика properties */
    public static Properties load(String properties) {
        Properties config = new Properties();
        try (InputStream in = PropertyFactory
                .class
                .getClassLoader()
                .getResourceAsStream(properties)) { //app.properties
            if (in == null) {
                LOG.error("Ошибка загрузки файла свойств, стрим пуст! - {}",
                        properties);
            }
            config.load(in);
        } catch (Exception e) {
            LOG.error("Error load properties: ", e);
        }
        return config;
    }
}
