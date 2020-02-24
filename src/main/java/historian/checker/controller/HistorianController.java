package historian.checker.controller;

import historian.checker.dto.CustomerDto;
import historian.checker.service.Impl.HistorianService;
import historian.checker.transformer.HistorianTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
