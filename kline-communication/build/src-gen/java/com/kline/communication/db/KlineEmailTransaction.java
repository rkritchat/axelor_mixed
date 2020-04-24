/*
 * Axelor Business Solutions
 * 
 * Copyright (C) 2020 Axelor (<http://axelor.com>).
 * 
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kline.communication.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "COMMUNICATION_KLINE_EMAIL_TRANSACTION")
public class KlineEmailTransaction extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMUNICATION_KLINE_EMAIL_TRANSACTION_SEQ")
	@SequenceGenerator(name = "COMMUNICATION_KLINE_EMAIL_TRANSACTION_SEQ", sequenceName = "COMMUNICATION_KLINE_EMAIL_TRANSACTION_SEQ", allocationSize = 1)
	private Long id;

	private LocalDateTime dateTime;

	private String emailFrom;

	private String emailTo;

	private String emailCc;

	private String emailBcc;

	private String emailSubject;

	@Size(max = 50000)
	private String emailBody;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<KlineEmailAttachment> klineEmailAttachment;

	private String owner;

	private String status;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public KlineEmailTransaction() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public String getEmailBcc() {
		return emailBcc;
	}

	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public List<KlineEmailAttachment> getKlineEmailAttachment() {
		return klineEmailAttachment;
	}

	public void setKlineEmailAttachment(List<KlineEmailAttachment> klineEmailAttachment) {
		this.klineEmailAttachment = klineEmailAttachment;
	}

	/**
	 * Add the given {@link KlineEmailAttachment} item to the {@code klineEmailAttachment}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addKlineEmailAttachment(KlineEmailAttachment item) {
		if (getKlineEmailAttachment() == null) {
			setKlineEmailAttachment(new ArrayList<>());
		}
		getKlineEmailAttachment().add(item);
	}

	/**
	 * Remove the given {@link KlineEmailAttachment} item from the {@code klineEmailAttachment}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeKlineEmailAttachment(KlineEmailAttachment item) {
		if (getKlineEmailAttachment() == null) {
			return;
		}
		getKlineEmailAttachment().remove(item);
	}

	/**
	 * Clear the {@code klineEmailAttachment} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearKlineEmailAttachment() {
		if (getKlineEmailAttachment() != null) {
			getKlineEmailAttachment().clear();
		}
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof KlineEmailTransaction)) return false;

		final KlineEmailTransaction other = (KlineEmailTransaction) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("dateTime", getDateTime())
			.add("emailFrom", getEmailFrom())
			.add("emailTo", getEmailTo())
			.add("emailCc", getEmailCc())
			.add("emailBcc", getEmailBcc())
			.add("emailSubject", getEmailSubject())
			.add("emailBody", getEmailBody())
			.add("owner", getOwner())
			.add("status", getStatus())
			.omitNullValues()
			.toString();
	}
}
