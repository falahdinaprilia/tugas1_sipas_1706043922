package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.DiagnosisNotFoundException;
import apap.tugas.sipas.model.InvalidParameterException;
import apap.tugas.sipas.model.PasienNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = PasienNotFoundException.class)
    public String pasienNotFound(PasienNotFoundException e, Model model) {
        String msg = "Pasien dengan NIK "  + e.getMessage() + " tidak ditemukan.";
        model.addAttribute("msg", msg);
        return "error";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = DiagnosisNotFoundException.class)
    public String diagnosisNotFound(DiagnosisNotFoundException e, Model model) {
        String msg = "Diagnosis penyakit dengan ID "  + e.getMessage() + " tidak ditemukan.";
        model.addAttribute("msg", msg);
        return "error";
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = InvalidParameterException.class)
    public String invalidParameter(InvalidParameterException e, Model model) {
        String msg = "Permintaan Anda tidak dapat diproses.";
        model.addAttribute("msg", msg);
        return "error";
    }

}
