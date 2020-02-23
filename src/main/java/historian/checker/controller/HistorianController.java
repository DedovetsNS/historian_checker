package historian.checker.controller;

import historian.checker.service.Impl.HistorianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historian")
public class HistorianController {

    private final HistorianService historianService;

    @Autowired
    public HistorianController(HistorianService historianService) {
        this.historianService = historianService;
    }

    @GetMapping("checkAccess/{firstName}/{lastName}")
    public Boolean checkAccess(@PathVariable("firstName") String firstName,
                               @PathVariable("lastName") String lastName) {
      return historianService.checkLibraryAccess(firstName,lastName);
    }

}
