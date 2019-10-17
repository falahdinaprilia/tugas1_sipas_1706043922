package apap.tugas.sipas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="diagnosisPenyakit")
public class DiagnosisPenyakitModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="kode", nullable = false)
    private String kode;

    @OneToMany(mappedBy= "diagnosisPenyakit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public List<PasienDiagnosisPenyakitModel> getListPasienDiagnosisPenyakit() {
        return listPasienDiagnosisPenyakit;
    }

    public void setListPasienDiagnosisPenyakit(List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit) {
        this.listPasienDiagnosisPenyakit = listPasienDiagnosisPenyakit;
    }
}
