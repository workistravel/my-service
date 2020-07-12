package pl.dernovyi.myservice.services;

import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;

public interface ActiveEmployeeService {
    ActiveEmployeeDto getActEmployeeById(Long id);
}
