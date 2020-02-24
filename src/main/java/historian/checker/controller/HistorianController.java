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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static historian.checker.log.dictionary.ControllerMessages.*;

@Slf4j
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
        Boolean access = historianService.checkSpecificAccess(firstName, lastName);
        log.info(LOG_GET,"Specific Access= "+access , firstName+" "+lastName);
        return access;
    }

    @GetMapping("/AllWithAccess")
    Set<CustomerDto> findAllWithAccess() {
        log.info(LOG_GET_SET_BY,CustomerDto.class.toString(),"Customer specificAccess = true");
        return historianTransformer.historianToCustomerDto(historianService.findAllWithAccess());

    }

    @PostMapping
    public HistorianDto add(@RequestBody @Validated(Add.class) HistorianDto historianDto) {
        Historian historian = historianTransformer.toEntity(historianDto);
        historian = historianService.add(historian);
        log.info(LOG_ADD_NEW, Historian.class.toString(), historian.toString());
        return historianTransformer.toDto(historian);
    }

    @JsonView(Details.class)
    @GetMapping
    public Set<HistorianDto> findAll() {
        Set<Historian> historians = historianService.findAll();
        log.info(LOG_GET_ALL, Historian.class.toString());
        return historianTransformer.toDto(historians);
    }

    @JsonView(Details.class)
    @GetMapping("{id}")
    public HistorianDto getById(@PathVariable("id") Long id) {
        Historian historian = historianService.findById(id);
        HistorianDto historianDto = historianTransformer.toDto(historian);
        log.info(LOG_GET, Historian.class.toString(), historianDto.toString());
        return historianDto;
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        historianService.deleteById(id);
        log.info(LOG_DELETE_BY_ID, Historian.class.toString(), id);
    }

    @JsonView(Details.class)
    @PutMapping
    public HistorianDto updateById(@RequestBody @Validated({Update.class}) HistorianDto historianDto) {
        Historian historian = historianTransformer.toEntity(historianDto);
        historian = historianService.update(historian);
        historianDto = historianTransformer.toDto(historian);
        log.info(LOG_UPDATE, Historian.class.toString(), historianDto.toString());
        return historianDto;
    }
}
