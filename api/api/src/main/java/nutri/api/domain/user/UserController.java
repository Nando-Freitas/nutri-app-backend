package nutri.api.domain.user;

import jakarta.validation.Valid;
import nutri.api.domain.token.TokenDTO;
import nutri.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Page<User>> findAll(@PageableDefault(size=10, sort = "name") Pageable pagination) {
        Page<User> users = service.getAll(pagination);

        return ResponseEntity.ok(users);
    }

    @PostMapping("login")
    @Transactional
    public ResponseEntity login(@RequestBody @Valid UserAuthDTO dto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication authenticate = manager.authenticate(usernamePasswordAuthenticationToken);
        String jwt = tokenService.generate((User) authenticate.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(jwt));
    }

    @PostMapping("sign_up")
    @Transactional
    public ResponseEntity signUp(@RequestBody @Valid UserDTO userDTO) {
        User user = service.insert(userDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        User user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<User> insert(@RequestBody @Valid UserDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        User user = service.insert(dto);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody UserDTO dto) {
        service.update(id, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<User> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
