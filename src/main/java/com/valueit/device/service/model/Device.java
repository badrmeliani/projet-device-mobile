package com.valueit.device.service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numSrie;
    private String marque;
    private String modele;
    @ManyToOne //(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "entreprise")
    @JsonBackReference
    private Entreprise entreprise;

    @ManyToOne //(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    @JsonBackReference
    private Emp emp;

    public Device(Long id, String numSrie, String marque, String modele, Entreprise entreprise, Emp emp) {
        this.id = id;
        this.numSrie = numSrie;
        this.marque = marque;
        this.modele = modele;
        this.entreprise = entreprise;
        this.emp = emp;
    }
}
