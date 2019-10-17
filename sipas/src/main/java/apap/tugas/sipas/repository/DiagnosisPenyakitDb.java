package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisPenyakitDb extends JpaRepository<DiagnosisPenyakitModel, Long> {
        Optional<DiagnosisPenyakitModel> findById(Long idDiagnosisPenyakit);
        }
