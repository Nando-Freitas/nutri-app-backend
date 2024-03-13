package nutri.api.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

}
