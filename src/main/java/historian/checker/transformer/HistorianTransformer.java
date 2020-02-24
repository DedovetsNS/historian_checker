package historian.checker.transformer;

import historian.checker.dto.CustomerDto;
import historian.checker.model.Historian;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HistorianTransformer {


    private CustomerDto historianToCustomerDto(Historian historian) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(historian.getId());
        customerDto.setFirstName(historian.getFirstName());
        customerDto.setLastName(historian.getLastName());
        customerDto.setEmail(historian.getEmail());
        customerDto.setPhone(historian.getPhone());
        customerDto.setAddress(historian.getAddress());
        return customerDto;
    }

    public Set<CustomerDto> historianToCustomerDto(Set<Historian> historians) {
        Set<CustomerDto> customersDto = new HashSet<>();
        for (Historian historian : historians) {
            customersDto.add(historianToCustomerDto(historian));
        }
        return customersDto;
    }
}
