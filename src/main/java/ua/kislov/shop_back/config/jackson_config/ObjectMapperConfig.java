package ua.kislov.shop_back.config.jackson_config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import ua.kislov.shop_back.model.ShopOrder;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Регистрация кастомного сериализатора для ShopOrder
        SimpleModule module = new SimpleModule();
        module.addSerializer(ShopOrder.class, shopOrderSerializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }

    @Bean
    public ShopOrderSerializer shopOrderSerializer() {
        return new ShopOrderSerializer();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}