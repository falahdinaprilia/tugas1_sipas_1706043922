package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.EmergencyContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmergencyContactDb extends JpaRepository<EmergencyContactModel, Long> {
    Optional<EmergencyContactModel> findById(Long idEmergencyContact);
    Optional<EmergencyContactModel> findByPasienId(Long pasienId);

}
