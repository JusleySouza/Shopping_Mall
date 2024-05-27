package com.shopping.mall.userapi.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.userapi.constants.Conf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private UUID id;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = true)
	private String socialName;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate dateBirth;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate created;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate changed;
	@Column(nullable = false)
	private Boolean active;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "userId")
	private List<Address> listAddress ;

}
