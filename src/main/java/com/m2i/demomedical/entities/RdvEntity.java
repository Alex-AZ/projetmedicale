package com.m2i.demomedical.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "rdv", schema = "medical", catalog = "")
public class RdvEntity {
	private int id;
    private Date dateheure;
    private String type;
    private int duree;
    private String note;
    private PatientEntity patient;

    @Id
    @Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Basic
    @Column(name = "dateheure")
	public Date getDateheure() {
		return dateheure;
	}

	public void setDateheure(Date date) {
		this.dateheure = date;
	}

	@Basic
    @Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Basic
    @Column(name = "duree")
	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	@Basic
    @Column(name = "note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


    @OneToOne
    @JoinColumn(name = "patient", referencedColumnName = "id")
	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}



}