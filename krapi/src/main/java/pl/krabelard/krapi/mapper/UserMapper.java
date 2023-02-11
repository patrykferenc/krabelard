package pl.krabelard.krapi.mapper;

import org.mapstruct.Mapper;
import pl.krabelard.krapi.dto.RegisterRequest;
import pl.krabelard.krapi.dto.RegisterResponse;
import pl.krabelard.krapi.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registrationRequestToEntity(RegisterRequest request);

    RegisterResponse entityToRegistrationResponse(User user);

}
