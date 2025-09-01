package com.example.booking.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@Nullable
	@CreatedBy
	@Column(name = "createdUserId", updatable = false)
	protected U createdUserId;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdDate", updatable = false)
	protected Date createdDate;

	@Nullable
	@LastModifiedBy
	protected U lastModifiedUserId;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate;

	@Column(name = "created_ip", updatable = false)
	private String createdIp;

	@Column(name = "last_modified_ip")
	private String lastModifiedIp;
}
