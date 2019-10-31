package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.*;
import apap.tugas.sipas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PasienController {
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
    private PasienDiagnosisPenyakitService diagnosisPasienService;


    @RequestMapping(value= "/", method = RequestMethod.GET)
    public String beranda(Model model) {
        List<PasienModel> listPasien = pasienService.getPasienList();
        for (PasienModel pasien : listPasien) {
            pasien.getEmergencyContact().getNoHp();
        }
        Collections.sort(listPasien);
        model.addAttribute("pasienList", listPasien);

        return "beranda";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String addPasienFormPage(Model model) {

        PasienModel pasien = new PasienModel();
        AsuransiModel asuransi = new AsuransiModel();
        EmergencyContactModel emergencyContact = new EmergencyContactModel();
        List<AsuransiModel> listAsuransi = new ArrayList<AsuransiModel>();
        listAsuransi.add(asuransi);
        pasien.setEmergencyContact(emergencyContact);
        pasien.setListAsuransi(listAsuransi);

        List<AsuransiModel> listAllAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("allAsuransiList", listAllAsuransi);
        model.addAttribute("pasien", pasien);

        return "form-tambah-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST, params={"addRow"})
    public String addRow(@ModelAttribute PasienModel pasien, Model model) {
        AsuransiModel asuransi = new AsuransiModel();
        pasien.getListAsuransi().add(asuransi);
        List<AsuransiModel> listAllAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("allAsuransiList", listAllAsuransi);
        model.addAttribute("pasien", pasien);
        return "form-tambah-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST, params={"removeRow"})
    public String removeRow(@ModelAttribute PasienModel pasien, Model model, HttpServletRequest req) {
        Integer rowId =  Integer.valueOf(req.getParameter("removeRow"));
        pasien.getListAsuransi().remove(rowId.intValue());
        model.addAttribute("pasien", pasien);
        List<AsuransiModel> listAllAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("allAsuransiList", listAllAsuransi);
        return "form-tambah-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST)
    public String addPasienSubmit(@ModelAttribute PasienModel pasien, ModelMap model){
        for (PasienModel p : pasienService.getPasienList()) {
            if (p.getNik().equals(pasien.getNik())) {
                model.addAttribute("pasien", pasien);
                return "duplikat-nik";
            }
        }
        pasienService.kodePasien(pasien);
        int count = 0;
        List<AsuransiModel> tmpListAsuransi = new ArrayList<>();
        for (AsuransiModel asuransi : pasien.getListAsuransi()) {
            if (asuransi.getId() == 0) {
                count += 1;
            } else {
                tmpListAsuransi.add(asuransi);
            }
        } if (count == pasien.getListAsuransi().size()) {
            pasien.setListAsuransi(null);
        } else {
            pasien.setListAsuransi(tmpListAsuransi);
            pasienService.addPasien(pasien);
            List<AsuransiModel> asuransiPasien = pasien.getListAsuransi().stream().distinct().collect(Collectors.toList());
            pasien.setListAsuransi(asuransiPasien);
        }

        emergencyContactService.addEmergencyContact(pasien.getEmergencyContact());
        pasienService.addPasien(pasien);
        model.addAttribute("kodePasien", pasien.getKode());
        model.addAttribute("namaPasien", pasien.getNama());

        return "tambah-pasien";
    }

    @RequestMapping(value = "/pasien", method = RequestMethod.GET, params = "nikPasien")
    public String viewPasien(@RequestParam(value = "nikPasien") String nikPasien, Model model) throws PasienNotFoundException {
        Optional<PasienModel> tmp = pasienService.getByNikPasien(nikPasien);
        if (tmp.isEmpty()) {
            throw new PasienNotFoundException(nikPasien);
        }
        PasienModel pasien = tmp.get();
        model.addAttribute("pasien", pasien);
        model.addAttribute("listAsuransi", pasien.getListAsuransi());
        return "lihat-pasien";
    }

    @RequestMapping(value = "/pasien/ubah/{nikPasien}", method = RequestMethod.GET)
    public String ubahPasienForm(@PathVariable String nikPasien, Model model) throws PasienNotFoundException {
        Optional<PasienModel> tmp = pasienService.getByNikPasien(nikPasien);
        if (tmp.isEmpty()) {
            throw new PasienNotFoundException(nikPasien);
        }
        PasienModel pasien = tmp.get();
        model.addAttribute("pasien", pasien);
        return "form-ubah-pasien";
    }

    @RequestMapping(value = "/pasien/ubah/{nikPasien}", method = RequestMethod.POST)
    public String ubahPasienSubmit(@ModelAttribute PasienModel pasien, Model model) {
        EmergencyContactModel emergency = emergencyContactService.changeEmergency(pasien.getEmergencyContact());
        pasien.setEmergencyContact(emergency);
        PasienModel pasienModel = pasienService.changePasien(pasien);
        model.addAttribute("pasien", pasienModel);
        model.addAttribute("emergency", emergency);
        return "ubah-pasien";
    }

    @RequestMapping(value = "/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.GET)
    public String tambahDiagnosis(@PathVariable String nikPasien, Model model) throws PasienNotFoundException {
        Optional<PasienModel> tmp = pasienService.getByNikPasien(nikPasien);
        if (tmp.isEmpty()) {
            throw new PasienNotFoundException(nikPasien);
        }
        PasienModel pasien = tmp.get();
        PasienDiagnosisPenyakitModel pasienDiagnosis = new PasienDiagnosisPenyakitModel();
        //        pasien.getListPasienDiagnosisPenyakit().add(pasienDiagnosis);
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisService.getPenyakitList();
        model.addAttribute("pasien", pasien);
        model.addAttribute("pasienDiagnosis", pasienDiagnosis);
        model.addAttribute("listPenyakitPasien", pasien.getListPasienDiagnosisPenyakit());
        model.addAttribute("listPenyakit", listDiagnosis);
        return "form-tambah-diagnosis-pasien";
    }

    @RequestMapping(value = "/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.POST)
    public String tambahDiagnosis(@PathVariable String nikPasien, @ModelAttribute PasienDiagnosisPenyakitModel pasienDiagnosis, Model model) {
        String pasienDiag = diagnosisPasienService.addDiagnosisPasien(pasienDiagnosis, nikPasien);
        if (pasienDiag.equals("udah-ada")) {
            return "diagnosis-pasien-gagal-ditambahkan";
        }
        model.addAttribute("pasienDiagnosis", pasienDiagnosis);
        return "tambah-diagnosis-pasien";
    }

    @RequestMapping(value="/pasien/cari", method = RequestMethod.GET)
    public String cariPasien(@RequestParam(value = "idAsuransi", required = false) Long idAsuransi, @RequestParam(value = "idDiagnosis", required = false) Long idDiagnosis, Model model) {
        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
        List<DiagnosisPenyakitModel> listPenyakit = diagnosisService.getPenyakitList();

        List<PasienModel> listPasien = new ArrayList<>();
        List<PasienModel> listPasienPenyakit = new ArrayList<>();
        if (idAsuransi != null && idDiagnosis != null) {
            DiagnosisPenyakitModel diagnosis = diagnosisService.getPenyakitById(idDiagnosis).get();
            AsuransiModel  asuransi = asuransiService.getAsuransiById(idAsuransi).get();
            listPasien = asuransi.getListPasien();
            for (PasienDiagnosisPenyakitModel object : diagnosis.getListPasienDiagnosisPenyakit()) {
                listPasienPenyakit.add(object.getPasien());
            }
            listPasien.retainAll(listPasienPenyakit);
        } else if ( idDiagnosis == null && idAsuransi != null) {
            AsuransiModel  asuransi = asuransiService.getAsuransiById(idAsuransi).get();
            listPasien = asuransi.getListPasien();
        } else if ( idAsuransi == null && idDiagnosis != null){
            DiagnosisPenyakitModel diagnosis = diagnosisService.getPenyakitById(idDiagnosis).get();
            for (PasienDiagnosisPenyakitModel object : diagnosis.getListPasienDiagnosisPenyakit()) {
                listPasien.add(object.getPasien());
            }
        }
        model.addAttribute("listAsuransi", listAsuransi);
        model.addAttribute("listPenyakit", listPenyakit);
        model.addAttribute("listPasien", listPasien);

        return "cari-pasien";
    }

//    @RequestMapping(value = "/pasien/cari/lakilaki-perempuan", method = RequestMethod.GET)
//    public String cariPasienGenderForm(Model model) {
//        List<DiagnosisPenyakitModel> listPenyakit = diagnosisService.getPenyakitList();
//        model.addAttribute("listPenyakit", listPenyakit);
//        return "form-cari-pasien-by-gender";
//    }

    @RequestMapping(value = "/pasien/cari/lakilaki-perempuan", method = RequestMethod.GET)
    public String cariPasienGenderSubmit(@RequestParam(value = "idDiagnosis", required = false) Long idDiagnosis, Model model) {
        if (idDiagnosis != null) {
            List<PasienModel> listPasienPenyakit = diagnosisPasienService.getAllPasienByIdPenyakit(idDiagnosis);
            int lakilaki = 0;
            int perempuan = 0;
            for (PasienModel pasien : listPasienPenyakit) {
                if (pasien.getJenisKelamin().equals("1")) {
                    lakilaki += 1;
                } else {
                    perempuan += 1;
                }
            }
            model.addAttribute("namaPenyakit", diagnosisService.getPenyakitById(idDiagnosis).get().getNama());
            model.addAttribute("lakilaki", lakilaki);
            model.addAttribute("perempuan", perempuan);
        }
        else {
            model.addAttribute("namaPenyakit", "Penyakit");
        }
        List<DiagnosisPenyakitModel> listPenyakit = diagnosisService.getPenyakitList();
        model.addAttribute("listPenyakit", listPenyakit);
        return "cari-pasien-by-gender";
    }
}