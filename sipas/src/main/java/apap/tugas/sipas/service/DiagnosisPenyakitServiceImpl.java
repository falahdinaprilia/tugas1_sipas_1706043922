package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.repository.DiagnosisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisPenyakitServiceImpl implements DiagnosisPenyakitService {
    @Autowired
    private DiagnosisPenyakitDb diagnosisPenyakitDb;
    @Override
    public List<DiagnosisPenyakitModel> getPenyakitList() {
        return diagnosisPenyakitDb.findAll();
    }

    @Override
    public Optional<DiagnosisPenyakitModel> getPenyakitById(Long idDiagnosis) {
        return diagnosisPenyakitDb.findById(idDiagnosis);
    }

    @Override
    public void addPenyakit(DiagnosisPenyakitModel penyakit) {
        diagnosisPenyakitDb.save(penyakit);
    }

    @Override
    public void deletePenyakit(DiagnosisPenyakitModel penyakit) {
        diagnosisPenyakitDb.delete(penyakit);
    }
}
