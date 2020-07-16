package store.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class EntityMapper<Entity, DTO> {
    private ModelMapper modelMapper;

    public EntityMapper() {
        this.modelMapper = getModelMapperInstance();
    }

    public DTO toDTO(Entity from, Class toClass) {
        return Objects.isNull(from) ? null : (DTO) modelMapper.map(from, toClass);
    }

    public Entity toEntity(DTO from, Class toClass) {
        return Objects.isNull(from) ? null : (Entity) modelMapper.map(from, toClass);
    }

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
