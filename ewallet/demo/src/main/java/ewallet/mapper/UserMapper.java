package ewallet.mapper;

import ewallet.dto.UserDTO;
import ewallet.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO entityToDto(User user);
    User dtoToEntity(UserDTO userDTO);
}
