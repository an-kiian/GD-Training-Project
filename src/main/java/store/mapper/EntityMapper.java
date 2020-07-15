package store.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityMapper<Entity, DTO> {
    @Autowired
    private ModelMapper modelMapper;

    public EntityMapper() {
    }

    public DTO toDTO(Entity from, Class toClass) {
        return (DTO) modelMapper.map(from, toClass);
    }

    public Entity toEntity(DTO from, Class toClass) {
        return (Entity) modelMapper.map(from, toClass);
    }
}
