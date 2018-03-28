package com.polarisfinder.follow.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@Table(name="Follow")
public class Follow implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  

	@Column(name="to_user_id")
	private int to_user_id;
	
	@Column(name="send_user_id")
	private int send_user_id;

	@Column(name="subject")
    private String subject;
	
	@Column(name="content")
    private String content;

	@Column(name="star")
	private int star;

	@Column(name="status")
	private int status;
	
	
	@Column (name="reg_dt", columnDefinition="datetime", insertable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date reg_dt;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getTo_user_id() {
		return to_user_id;
	}

	public final void setTo_user_id(int to_user_id) {
		this.to_user_id = to_user_id;
	}

	public final int getSend_user_id() {
		return send_user_id;
	}

	public final void setSend_user_id(int send_user_id) {
		this.send_user_id = send_user_id;
	}

	public final String getSubject() {
		return subject;
	}

	public final void setSubject(String subject) {
		this.subject = subject;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final Date getReg_dt() {
		return reg_dt;
	}

	public final void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public final int getStar() {
		return star;
	}

	public final void setStar(int star) {
		this.star = star;
	}

	public final int getStatus() {
		return status;
	}

	public final void setStatus(int status) {
		this.status = status;
	}



}
