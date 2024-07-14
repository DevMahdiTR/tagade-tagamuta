package tagarde.core.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import tagarde.core.domain.auth.generalManager.GeneralManager;
import tagarde.core.domain.auth.generalManager.GeneralManagerDTO;
import tagarde.core.domain.hospital.Hospital;

import java.util.function.Function;
@Service
public class GeneralManagerDTOMapper implements Function<GeneralManager , GeneralManagerDTO> {
    @Override
    public GeneralManagerDTO apply(@NotNull GeneralManager generalManager) {
        return new GeneralManagerDTO(
                generalManager.getId(),
                generalManager.getFirstName(),
                generalManager.getLastName(),
                generalManager.getAddress(),
                generalManager.getPhoneNumber(),
                generalManager.getEmail(),
                generalManager.getCreatedAt(),
                generalManager.getUpdatedAt(),
                generalManager.getRole(),
                generalManager.isEnabled(),
                generalManager.getHoiptals().stream().map(
                        Hospital::getId
                ).toList()

        );
    }
}
