package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.*;
import apap.tugas.sipas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

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

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST)
    public String addPasienSubmit(@RequestParam (value = "asuransi") Long id, @ModelAttribute PasienModel pasien, Model model){
        pasienService.kodePasien(pasien);
        AsuransiModel asuransiPasien = asuransiService.getAsuransiById(id).get();
        List<AsuransiModel> listAsuransiPasien = new ArrayList<AsuransiModel>();
        listAsuransiPasien.add(asuransiPasien);
        pasien.setListAsuransi(listAsuransiPasien);
        emergencyContactService.addEmergencyContact(pasien.getEmergencyContact());
        pasienService.addPasien(pasien);
        model.addAttribute("kodePasien", pasien.getKode());
        model.addAttribute("namaPasien", pasien.getNama());

        return "tambah-pasien";
    }

    @RequestMapping(value = "/pasien", method = RequestMethod.GET, params = "nikPasien")
    public String viewPasien(@RequestParam(value = "nikPasien") String nikPasien, Model model) {
        PasienModel pasien = pasienService.getByNikPasien(nikPasien).get();
        model.addAttribute("pasien", pasien);
        model.addAttribute("listAsuransi", pasien.getListAsuransi());
        return "lihat-pasien";
    }

    @RequestMapping(value = "/pasien/ubah/{nikPasien}", method = RequestMethod.GET)
    public String ubahPasienForm(@PathVariable String nikPasien, Model model) {
        PasienModel pasien = pasienService.getByNikPasien(nikPasien).get();
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
    public String tambahDiagnosis(@PathVariable String nikPasien, Model model) {
        PasienModel pasien = pasienService.getByNikPasien(nikPasien).get();
        PasienDiagnosisPenyakitModel pasienDiagnosis = new PasienDiagnosisPenyakitModel();
        //        pasien.getListPasienDiagnosisPenyakit().add(pasienDiagnosis);
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisService.getLPenyakitList();
        model.addAttribute("pasien", pasien);
        model.addAttribute("listAsuransi", pasien.getListAsuransi());
        model.addAttribute("pasienDiagnosis", pasienDiagnosis);
        model.addAttribute("listPenyakitPasien", pasien.getListPasienDiagnosisPenyakit());
        model.addAttribute("listPenyakit", listDiagnosis);
        return "form-tambah-diagnosis-pasien";
    }

    @RequestMapping(value = "/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.POST)
    public String tambahDiagnosis(@PathVariable String nikPasien, @ModelAttribute PasienDiagnosisPenyakitModel pasienDiagnosis, Model model) {
        diagnosisPasienService.addDiagnosisPasien(pasienDiagnosis, nikPasien);
        model.addAttribute("pasienDiagnosis", pasienDiagnosis);
        return "tambah-diagnosis-pasien";
    }
}