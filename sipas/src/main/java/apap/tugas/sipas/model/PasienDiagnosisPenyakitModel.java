package apap.tugas.sipas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="pasien_diagnosis_penyakit")
public class PasienDiagnosisPenyakitModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasienDiagnosisPenyakit;

    @NotNull
    @Column(name="tanggal_diagnosis", nullable = false)
    private Date tanggalDiagnosis;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pasienId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PasienModel pasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "diagnosisPenyakitId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DiagnosisPenyakitModel diagnosisPenyakit;

    public Long getIdPasienDiagnosisPenyakit() {
        return idPasienDiagnosisPenyakit;
    }

    public void setIdPasienDiagnosisPenyakit(Long idPasienDiagnosisPenyakit) {
        this.idPasienDiagnosisPenyakit = idPasienDiagnosisPenyakit;
    }

    public Date getTanggalDiagnosis() {
        return tanggalDiagnosis;
    }

    public void setTanggalDiagnosis(Date tanggalDiagnosis) {
        this.tanggalDiagnosis = tanggalDiagnosis;
    }

    public PasienModel getPasien() {
        return pasien;
    }

    public void setPasien(PasienModel pasien) {
        this.pasien = pasien;
    }

    public DiagnosisPenyakitModel getDiagnosisPenyakit() {
        return diagnosisPenyakit;
    }

    public void setDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit) {
        this.diagnosisPenyakit = diagnosisPenyakit;
    }
}
