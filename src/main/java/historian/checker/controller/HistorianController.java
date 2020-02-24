package historian.checker.controller;

import com.fasterxml.jackson.annotation.JsonView;
import historian.checker.dto.CustomerDto;
import historian.checker.dto.HistorianDto;
import historian.checker.dto.groups.Add;
import historian.checker.dto.groups.Details;
import historian.checker.dto.groups.Update;
import historian.checker.model.Historian;
import historian.checker.service.HistorianService;
import historian.checker.transformer.impl.HistorianTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/historian")
public class HistorianController {

    private final HistorianTransformer historianTransformer;
    private final HistorianService historianService;

    @Autowired
    public HistorianController(HistorianTransformer historianTransformer, HistorianService historianService) {
        this.historianTransformer = historianTransformer;
        this.historianService = historianService;
    }

    @GetMapping("checkAccess/{firstName}/{lastName}")
    public Boolean checkAccess(@PathVariable("firstName") String firstName,
                               @PathVariable("lastName") String lastName) {
        return historianService.checkSpecificAccess(firstName, lastName);
    }

    @GetMapping("/AllWithAccess")
    Set<CustomerDto> findAllWithAccess() {
        return historianTransformer.historianToCustomerDto(historianService.findAllWithAccess());
    }

    @PostMapping
    public HistorianDto add(@RequestBody @Validated(Add.class) HistorianDto historianDto) {
        Historian historian = historianTransformer.toEntity(historianDto);
        historian = historianService.add(historian);
        return historianTransformer.toDto(historian);
    }

    @JsonView(Details.class)
    @GetMapping
    public Set<HistorianDto> findAll() {
        Set<Historian> historians = historianService.findAll();
        return historianTransformer.toDto(historians);
    }

    @JsonView(Details.class)
    @GetMapping("{id}")
    public HistorianDto getById(@PathVariable("id") Long id) {
        Historian historian = historianService.findById(id);
        return historianTransformer.toDto(historian);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        historianService.deleteById(id);
    }

    @JsonView(Details.class)
    @PutMapping
    public HistorianDto updateById(@RequestBody @Validated({Update.class}) HistorianDto historianDto) {
        Historian historian = historianTransformer.toEntity(historianDto);
        historian = historianService.update(historian);
        return historianTransformer.toDto(historian);
    }
}
