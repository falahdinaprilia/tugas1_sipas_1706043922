package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasienDiagnosisPenyakitDb extends JpaRepository<PasienDiagnosisPenyakitModel, Long> {
    Optional<PasienDiagnosisPenyakitModel> findByIdPasienDiagnosisPenyakit(Long idPasienDiagnosisPenyakit);
}
