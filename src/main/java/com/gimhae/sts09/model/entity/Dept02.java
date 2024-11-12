package com.gimhae.sts09.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept02 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int deptno;
	String dname;
	String loc;
}
