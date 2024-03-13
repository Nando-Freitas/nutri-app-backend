package nutri.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank String email,
        @NotNull @NotBlank String password
) {

}
