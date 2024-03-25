package nutri.api.domain.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodDTO(
        @NotBlank @NotNull String name,
        @NotNull int calories,
        @NotNull @NotBlank String type
) {
}
