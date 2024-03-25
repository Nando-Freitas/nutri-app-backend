package nutri.api.domain.food;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService service;

    @GetMapping
    public ResponseEntity<Page<Food>> findAll(@PageableDefault(size = 10, sort = "name") Pageable pagination) {
        Page<Food> foods = service.listAll(pagination);

        return ResponseEntity.ok(foods);
    }

    @PostMapping("insert")
    @Transactional
    public ResponseEntity<Food> insert(@RequestBody @Valid FoodDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Food food = service.save(dto);

        URI uri = uriComponentsBuilder.path("/insert/{id}").buildAndExpand(food.getId()).toUri();

        return ResponseEntity.created(uri).body(food);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Food> findById(@PathVariable Long id) {
        Food food = service.findById(id);

        return ResponseEntity.ok(food);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Food> update(@PathVariable Long id, @RequestBody @Valid FoodDTO dto) {
        Food updated = service.update(dto, id);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Food> deleteById(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
