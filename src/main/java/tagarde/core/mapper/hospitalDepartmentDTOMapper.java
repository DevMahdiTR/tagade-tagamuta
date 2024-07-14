package tagarde.core.mapper;

import org.springframework.stereotype.Service;
import tagarde.core.domain.department.Department;
import tagarde.core.domain.department.DepartmentDTO;

import java.util.function.Function;

@Service
public class hospitalDepartmentDTOMapper implements Function<Department , DepartmentDTO> {
    @Override
    public DepartmentDTO apply(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName()
        );
    }
}
