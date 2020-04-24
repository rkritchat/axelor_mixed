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

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "COMMUNICATION_KLINE_TRANSACTION")
public class KlineTransaction extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMUNICATION_KLINE_TRANSACTION_SEQ")
	@SequenceGenerator(name = "COMMUNICATION_KLINE_TRANSACTION_SEQ", sequenceName = "COMMUNICATION_KLINE_TRANSACTION_SEQ", allocationSize = 1)
	private Long id;

	private String type;

	private String tranDate;

	private String tranTime;

	private String email = " - ";

	private String mobileNo = " - ";

	private String emailTranId = " - ";

	private String smsTranId = " - ";

	private String status;

	private String statusDesc;

	private String owner;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<KlineEmailAttachmentTransaction> emailAttachmentTransaction;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public KlineTransaction() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailTranId() {
		return emailTranId;
	}

	public void setEmailTranId(String emailTranId) {
		this.emailTranId = emailTranId;
	}

	public String getSmsTranId() {
		return smsTranId;
	}

	public void setSmsTranId(String smsTranId) {
		this.smsTranId = smsTranId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<KlineEmailAttachmentTransaction> getEmailAttachmentTransaction() {
		return emailAttachmentTransaction;
	}

	public void setEmailAttachmentTransaction(List<KlineEmailAttachmentTransaction> emailAttachmentTransaction) {
		this.emailAttachmentTransaction = emailAttachmentTransaction;
	}

	/**
	 * Add the given {@link KlineEmailAttachmentTransaction} item to the {@code emailAttachmentTransaction}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addEmailAttachmentTransaction(KlineEmailAttachmentTransaction item) {
		if (getEmailAttachmentTransaction() == null) {
			setEmailAttachmentTransaction(new ArrayList<>());
		}
		getEmailAttachmentTransaction().add(item);
	}

	/**
	 * Remove the given {@link KlineEmailAttachmentTransaction} item from the {@code emailAttachmentTransaction}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeEmailAttachmentTransaction(KlineEmailAttachmentTransaction item) {
		if (getEmailAttachmentTransaction() == null) {
			return;
		}
		getEmailAttachmentTransaction().remove(item);
	}

	/**
	 * Clear the {@code emailAttachmentTransaction} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearEmailAttachmentTransaction() {
		if (getEmailAttachmentTransaction() != null) {
			getEmailAttachmentTransaction().clear();
		}
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
		if (!(obj instanceof KlineTransaction)) return false;

		final KlineTransaction other = (KlineTransaction) obj;
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
			.add("type", getType())
			.add("tranDate", getTranDate())
			.add("tranTime", getTranTime())
			.add("email", getEmail())
			.add("mobileNo", getMobileNo())
			.add("emailTranId", getEmailTranId())
			.add("smsTranId", getSmsTranId())
			.add("status", getStatus())
			.add("statusDesc", getStatusDesc())
			.add("owner", getOwner())
			.omitNullValues()
			.toString();
	}
}
