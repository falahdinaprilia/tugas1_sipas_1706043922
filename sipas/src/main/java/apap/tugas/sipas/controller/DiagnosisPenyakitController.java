package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.DiagnosisPenyakitService;
import apap.tugas.sipas.service.EmergencyContactService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String viewallDiagnosis(Model model) {
        List<DiagnosisPenyakitModel> listPenyakit = diagnosisService.getLPenyakitList();
        Collections.sort(listPenyakit);
        model.addAttribute("listPenyakit", listPenyakit);
        return "viewall-diagnosis-penyakit";
    }

    @RequestMapping(value="/diagnosis-penyakit", method = RequestMethod.GET, params = "idDiagnosis")
    public String viewPenyakit(@RequestParam(value = "idDiagnosis") Long id, Model model) {
        DiagnosisPenyakitModel penyakit = diagnosisService.getPenyakitById(id).get();
        model.addAttribute("penyakit", penyakit);
        model.addAttribute("listPasienDiagnosis", penyakit.getListPasienDiagnosisPenyakit());
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
        diagnosisService.addPenyakit(penyakit);
        model.addAttribute("penyakit", penyakit);
        return "tambah-penyakit";
    }



}
