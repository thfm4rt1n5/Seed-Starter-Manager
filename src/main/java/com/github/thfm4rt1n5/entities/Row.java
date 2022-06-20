package com.github.thfm4rt1n5.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rows")
public class Row implements Serializable {

	private static final long serialVersionUID = 1L;

	@Embedded
	private Variety variety;

	@Column(name = "seedsd_per_cell")
	private int seedsPerCell;

	public Row() {
	}

	public Row(Variety variety, int seedsPerCell) {
		this.variety = variety;
		this.seedsPerCell = seedsPerCell;
	}

	public Variety getVariety() {
		return variety;
	}

	public int getSeedsPerCell() {
		return seedsPerCell;
	}

	@Override
	public int hashCode() {
		return Objects.hash(variety);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Row other = (Row) obj;
		return Objects.equals(variety, other.variety);
	}

	@Override
	public String toString() {
		return "Row [variety=" + variety + ", seedsPerCell=" + seedsPerCell + "]";
	}

}
