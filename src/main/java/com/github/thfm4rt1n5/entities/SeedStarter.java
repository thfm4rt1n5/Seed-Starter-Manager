package com.github.thfm4rt1n5.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "seed_starter")
public class SeedStarter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "datePlanted")
	private Date datePlanted;

	@Column(name = "covered")
	private boolean covered;

	@Column(name = "type")
	private Type type;

	@Enumerated(EnumType.STRING)
	private Feature[] features;

	@OneToMany(mappedBy = "seed_starter", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Row> rows;

	public SeedStarter() {
	}

	public SeedStarter(Integer id, Date datePlanted, boolean covered, Type type, Feature[] features, List<Row> rows) {
		this.id = id;
		this.datePlanted = datePlanted;
		this.covered = covered;
		this.type = type;
		this.features = features;
		this.rows = rows;
	}

	public Integer getId() {
		return id;
	}

	public Date getDatePlanted() {
		return datePlanted;
	}

	public void setDatePlanted(Date date) {
		this.datePlanted = date;
	}

	public boolean isCovered() {
		return covered;
	}

	public Type getType() {
		return type;
	}

	public Feature[] getFeatures() {
		return features;
	}

	public List<Row> getRows() {
		return rows;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeedStarter other = (SeedStarter) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SeedStarter [id=" + id + ", datePlanted=" + datePlanted + ", covered=" + covered + ", type=" + type
				+ ", features=" + Arrays.toString(features) + ", rows=" + rows + "]";
	}

}
