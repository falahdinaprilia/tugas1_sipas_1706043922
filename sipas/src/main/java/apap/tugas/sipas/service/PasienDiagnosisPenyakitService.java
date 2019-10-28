package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;

import java.util.List;

public interface PasienDiagnosisPenyakitService {
    List<PasienModel> getAllPasienByIdPenyakit(Long idPenyakit);
    void addDiagnosisPasien(PasienDiagnosisPenyakitModel diagnosisPasien, String nikPasien);
}
