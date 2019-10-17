package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long> {
    Optional<PasienModel> findById(Long idPasien);
    Optional<PasienModel> findByNik(String nikPasien);
}
