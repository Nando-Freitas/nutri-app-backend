package nutri.api.domain.food;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    protected Page<Food> listAll(@PageableDefault Pageable pagination) {
        return repository.findAll(pagination);
    }

    protected Food findById(Long id) {
        return repository.findById(id).get();
    }

    protected Food save(@Valid FoodDTO dto) {
        Food food = Food.builder()
                .name(dto.name())
                .calories(dto.calories())
                .type(Enum.valueOf(FoodType.class, dto.type()))
                .build();
        return repository.save(food);
    }

    protected Food update(@Valid FoodDTO dto, Long id) {
        Food food = repository.findById(id).get();

        Food updated = Food.builder()
                .id(food.getId())
                .name(dto.name())
                .type(Enum.valueOf(FoodType.class, dto.type()))
                .calories(dto.calories()).build();

        return repository.save(updated);
    }

    protected void deleteById(Long id){
        repository.deleteById(id);
    }
}
