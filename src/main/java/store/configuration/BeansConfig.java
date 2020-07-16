package store.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import store.mapper.EntityMapper;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class BeansConfig {
    @Bean
    public <Entity,DTO>EntityMapper<Entity, DTO> getEntityMapperInstance() {
        return new EntityMapper<Entity,DTO>();
    }
    @Bean
    public ModelMapper getModelMapperInstance() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }
}
