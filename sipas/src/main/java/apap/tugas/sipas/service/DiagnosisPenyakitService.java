package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;

import java.util.List;
import java.util.Optional;

public interface DiagnosisPenyakitService {
    List<DiagnosisPenyakitModel> getPenyakitList();
    Optional<DiagnosisPenyakitModel> getPenyakitById(Long idDiagnosis);
    void addPenyakit(DiagnosisPenyakitModel penyakit);
    void deletePenyakit(DiagnosisPenyakitModel penyakit);
}
