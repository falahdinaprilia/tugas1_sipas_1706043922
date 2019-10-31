package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasienDiagnosisPenyakitDb extends JpaRepository<PasienDiagnosisPenyakitModel, Long> {
    List<PasienDiagnosisPenyakitModel> findByPasienNik(String nikPasien);
    List<PasienDiagnosisPenyakitModel> findByDiagnosisPenyakitId(Long idPenyakit);
//    List<PasienDiagnosisPenyakitModel> findByPasienListAsuransiIdAndDiagnosisPenyakitId(Long idAsuransi, Long idDiagnosis);

}
