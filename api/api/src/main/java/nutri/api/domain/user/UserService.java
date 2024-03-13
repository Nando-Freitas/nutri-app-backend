package nutri.api.domain.user;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Page<User> getAll(@PageableDefault Pageable pagination) {
        return repository.findAll(pagination);
    }

    protected User getUserById(Long id) {
        return repository.findById(id).get();
    }

    protected User insert(@Valid UserDTO dto) {
        User user = User.builder()
                .name(dto.name())
                .password(dto.password())
                .email(dto.email())
                .build();

        return repository.save(user);
    }

    protected User update(Long id, @Valid UserDTO dto){
        User user = repository.findById(id).get();

        User updated = User.builder()
                .id(user.getId())
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();

        return repository.save(updated);
    }

    protected void delete(Long id) {
        repository.deleteById(id);
    }
}
