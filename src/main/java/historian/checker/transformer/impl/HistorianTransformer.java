package historian.checker.transformer.impl;

import historian.checker.dto.CustomerDto;
import historian.checker.dto.HistorianDto;
import historian.checker.model.Historian;
import historian.checker.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HistorianTransformer implements Transformer<Historian, HistorianDto> {

    @Override
    public Historian toEntity(HistorianDto historianDto) {
        return Historian.builder()
                .id(historianDto.getId())
                .firstName(historianDto.getFirstName())
                .lastName(historianDto.getLastName())
                .email(historianDto.getEmail())
                .phone(historianDto.getPhone())
                .address(historianDto.getAddress())
                .specificLibraryAccess(historianDto.getSpecificLibraryAccess())
                .build();
    }

    @Override
    public HistorianDto toDto(Historian historian) {


        return HistorianDto.builder()
                .id(historian.getId())
                .firstName(historian.getFirstName())
                .lastName(historian.getLastName())
                .address(historian.getAddress())
                .email(historian.getEmail())
                .phone(historian.getPhone())
                .specificLibraryAccess(historian.getSpecificLibraryAccess())
                .build();
    }

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
