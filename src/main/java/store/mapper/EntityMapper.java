package store.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Objects;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class EntityMapper<Entity, DTO> {
    private ModelMapper modelMapper;

    private EntityMapper() {
        this.modelMapper = getModelMapperInstance();
    }

    public static EntityMapper<?, ?> getInstance() {
        return EntityMapperHolder.HOLDER_INSTANCE;
    }

    public DTO toDTO(Entity from, Class toClass) {
        return Objects.isNull(from) ? null : (DTO) modelMapper.map(from, toClass);
    }

    public Entity toEntity(DTO from, Class toClass) {
        return Objects.isNull(from) ? null : (Entity) modelMapper.map(from, toClass);
    }

    private ModelMapper getModelMapperInstance() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    public static class EntityMapperHolder {
        public static final EntityMapper<?, ?> HOLDER_INSTANCE = new EntityMapper<>();
    }
}
