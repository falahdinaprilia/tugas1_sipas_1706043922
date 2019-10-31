package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;

import java.util.List;

public interface PasienDiagnosisPenyakitService {
    List<PasienModel> getAllPasienByIdPenyakit(Long idPenyakit);
    String addDiagnosisPasien(PasienDiagnosisPenyakitModel diagnosisPasien, String nikPasien);
//    List<PasienDiagnosisPenyakitModel> getAllPasienByFilter(Long idAsuransi, Long idDiagnosis);
}
