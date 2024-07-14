package tagarde.core.service.user;

import lombok.extern.slf4j.Slf4j;
import tagarde.core.domain.auth.doctor.Doctor;
import tagarde.core.domain.auth.generalManager.GeneralManager;
import tagarde.core.domain.auth.hospitalOwner.HospitalOwner;
import tagarde.core.domain.auth.user.UserDTO;
import tagarde.core.domain.auth.user.UserEntityDTO;

import org.springframework.stereotype.Service;
import tagarde.core.mapper.DoctorDTOMapper;
import tagarde.core.mapper.GeneralManagerDTOMapper;
import tagarde.core.mapper.HospitalOwnerDTOMapper;
import tagarde.core.mapper.UserEntityDTOMapper;
import tagarde.core.exceptions.custom.DatabaseException;
import tagarde.core.exceptions.custom.ResourceNotFoundException;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.repository.UserEntityRepository;

import java.util.UUID;


@Service
@Slf4j
public class UserServiceImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityDTOMapper userEntityDTOMapper;
    private final DoctorDTOMapper doctorDTOMapper;
    private final HospitalOwnerDTOMapper hospitalOwnerDTOMapper;
    private final GeneralManagerDTOMapper generalManagerDTOMapper;
    public UserServiceImpl(UserEntityRepository userEntityRepository, UserEntityDTOMapper userEntityDTOMapper, DoctorDTOMapper doctorDTOMapper, HospitalOwnerDTOMapper hospitalOwnerDTOMapper, GeneralManagerDTOMapper generalManagerDTOMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityDTOMapper = userEntityDTOMapper;
        this.doctorDTOMapper = doctorDTOMapper;
        this.hospitalOwnerDTOMapper = hospitalOwnerDTOMapper;
        this.generalManagerDTOMapper = generalManagerDTOMapper;
    }

    @Override
    public UserDTO mapper(UserEntity userEntity) {

        return switch (userEntity.getClass().getSimpleName()) {
            case "Doctor" -> doctorDTOMapper.apply((Doctor) userEntity);
            case "HospitalOwner" -> hospitalOwnerDTOMapper.apply((HospitalOwner) userEntity);
            case "GeneralManager" -> generalManagerDTOMapper.apply((GeneralManager) userEntity);
            default -> userEntityDTOMapper.apply(userEntity);
        };
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        try {
            log.info("Database Request to save User: {}", userEntity);
            final UserEntity savedUser = userEntityRepository.save(userEntity);
            log.info("{} saved successfully", savedUser);
            return savedUser;
        } catch (Exception e) {
            log.error("Error saving User: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public UserEntity saveAndFlush(UserEntity userEntity) {
        try {
            log.info("Database Request to save User: {}", userEntity);
            final UserEntity savedUser = userEntityRepository.saveAndFlush(userEntity);
            log.info("{} saved successfully", savedUser);
            return savedUser;
        } catch (Exception e) {
            log.error("Error saving User: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    public UserEntity getUserByUUID(final UUID userId) {
        return userEntityRepository.fetchUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("The User with ID %s could not be found in our system.".formatted(userId))
        );
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userEntityRepository.fetchUserByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("The User with EMAIL %s could not be found in our system.".formatted(email))
        );
    }


    @Override
    public Boolean isEmailRegistered(final String email) {
        return userEntityRepository.isEmailRegistered(email);
    }



}
