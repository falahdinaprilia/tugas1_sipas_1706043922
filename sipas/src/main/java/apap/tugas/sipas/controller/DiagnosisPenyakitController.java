package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class DiagnosisPenyakitController {
    @Autowired
    private EmergencyContactService emergencyContactService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;

    @Autowired
    private DiagnosisPenyakitService diagnosisService;

    @Autowired
    private PasienDiagnosisPenyakitService pasienDiagnosisService;

    @RequestMapping(value = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String viewallDiagnosis(Model model) {
        List<DiagnosisPenyakitModel> listPenyakit = diagnosisService.getPenyakitList();
        List<Integer> listPasien = new ArrayList<>();
        for (DiagnosisPenyakitModel penyakit : listPenyakit) {
            Long id = penyakit.getId();
            listPasien.add(pasienDiagnosisService.getAllPasienByIdPenyakit(id).size());
        }
        Collections.sort(listPenyakit);
        model.addAttribute("listPenyakit", listPenyakit);
        model.addAttribute("listPasien", listPasien);
        return "viewall-diagnosis-penyakit";
    }

    @RequestMapping(value="/diagnosis-penyakit", method = RequestMethod.GET, params = "idDiagnosis")
    public String viewPenyakit(@RequestParam(value = "idDiagnosis") Long id, Model model) {
        DiagnosisPenyakitModel penyakit = diagnosisService.getPenyakitById(id).get();
        List<PasienModel> listPasien = pasienDiagnosisService.getAllPasienByIdPenyakit(id);
        model.addAttribute("penyakit", penyakit);
        model.addAttribute("listPasien", listPasien);
        return "lihat-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.GET)
    public String formtambahpenyakit(Model model) {
        DiagnosisPenyakitModel penyakit = new DiagnosisPenyakitModel();
        model.addAttribute("penyakit", penyakit);
        return "form-tambah-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.POST)
    public String tambahpenyakitsubmit(@ModelAttribute DiagnosisPenyakitModel penyakit, Model model) {
        for (DiagnosisPenyakitModel d : diagnosisService.getPenyakitList()) {
            if ( d.getKode().equals(penyakit.getKode()) || d.getNama().equals(penyakit.getNama())) {
                model.addAttribute("penyakit", penyakit);
                return "duplikat-penyakit";
            }
        }
        diagnosisService.addPenyakit(penyakit);
        model.addAttribute("penyakit", penyakit);
        return "tambah-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/hapus/{id}")
    public String hapusPenyakit(@PathVariable Long id, Model model) {
        DiagnosisPenyakitModel penyakit = diagnosisService.getPenyakitById(id).get();
        if(penyakit.getListPasienDiagnosisPenyakit().isEmpty()) {
            model.addAttribute("penyakit", penyakit);
            diagnosisService.deletePenyakit(penyakit);
            return "penyakit-berhasil-dihapus";
            }
        else {
            model.addAttribute("penyakit", penyakit);
            return "penyakit-gagal-dihapus";
        }
    }


}
