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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.axelor.apps.message.db.EmailAddress;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "COMMUNICATION_COMMUNICATION", indexes = { @Index(columnList = "kline_sms_template"), @Index(columnList = "kline_email_template") })
public class Communication extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMUNICATION_COMMUNICATION_SEQ")
	@SequenceGenerator(name = "COMMUNICATION_COMMUNICATION_SEQ", sequenceName = "COMMUNICATION_COMMUNICATION_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "content")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private KlineSmsTemplate klineSmsTemplate;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<KlineEmailAttachment> klineEmailAttachment;

	@Widget(title = "Attachment")
	private Boolean enableAttachment = Boolean.FALSE;

	@Widget(title = "Email Template")
	private Boolean enableEmailTemplate = Boolean.FALSE;

	@Widget(title = "Body")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private KlineEmailTemplate klineEmailTemplate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<EmailAddress> emailTo;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<EmailAddress> emailCC;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<EmailAddress> emailBCC;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Communication() {
	}

	public Communication(Set<EmailAddress> emailTo, Set<EmailAddress> emailCC, Set<EmailAddress> emailBCC) {
		this.emailTo = emailTo;
		this.emailCC = emailCC;
		this.emailBCC = emailBCC;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public KlineSmsTemplate getKlineSmsTemplate() {
		return klineSmsTemplate;
	}

	public void setKlineSmsTemplate(KlineSmsTemplate klineSmsTemplate) {
		this.klineSmsTemplate = klineSmsTemplate;
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

	public Boolean getEnableAttachment() {
		return enableAttachment == null ? Boolean.FALSE : enableAttachment;
	}

	public void setEnableAttachment(Boolean enableAttachment) {
		this.enableAttachment = enableAttachment;
	}

	public Boolean getEnableEmailTemplate() {
		return enableEmailTemplate == null ? Boolean.FALSE : enableEmailTemplate;
	}

	public void setEnableEmailTemplate(Boolean enableEmailTemplate) {
		this.enableEmailTemplate = enableEmailTemplate;
	}

	public KlineEmailTemplate getKlineEmailTemplate() {
		return klineEmailTemplate;
	}

	public void setKlineEmailTemplate(KlineEmailTemplate klineEmailTemplate) {
		this.klineEmailTemplate = klineEmailTemplate;
	}

	public Set<EmailAddress> getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(Set<EmailAddress> emailTo) {
		this.emailTo = emailTo;
	}

	/**
	 * Add the given {@link EmailAddress} item to the {@code emailTo}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addEmailTo(EmailAddress item) {
		if (getEmailTo() == null) {
			setEmailTo(new HashSet<>());
		}
		getEmailTo().add(item);
	}

	/**
	 * Remove the given {@link EmailAddress} item from the {@code emailTo}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeEmailTo(EmailAddress item) {
		if (getEmailTo() == null) {
			return;
		}
		getEmailTo().remove(item);
	}

	/**
	 * Clear the {@code emailTo} collection.
	 *
	 */
	public void clearEmailTo() {
		if (getEmailTo() != null) {
			getEmailTo().clear();
		}
	}

	public Set<EmailAddress> getEmailCC() {
		return emailCC;
	}

	public void setEmailCC(Set<EmailAddress> emailCC) {
		this.emailCC = emailCC;
	}

	/**
	 * Add the given {@link EmailAddress} item to the {@code emailCC}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addEmailCC(EmailAddress item) {
		if (getEmailCC() == null) {
			setEmailCC(new HashSet<>());
		}
		getEmailCC().add(item);
	}

	/**
	 * Remove the given {@link EmailAddress} item from the {@code emailCC}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeEmailCC(EmailAddress item) {
		if (getEmailCC() == null) {
			return;
		}
		getEmailCC().remove(item);
	}

	/**
	 * Clear the {@code emailCC} collection.
	 *
	 */
	public void clearEmailCC() {
		if (getEmailCC() != null) {
			getEmailCC().clear();
		}
	}

	public Set<EmailAddress> getEmailBCC() {
		return emailBCC;
	}

	public void setEmailBCC(Set<EmailAddress> emailBCC) {
		this.emailBCC = emailBCC;
	}

	/**
	 * Add the given {@link EmailAddress} item to the {@code emailBCC}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addEmailBCC(EmailAddress item) {
		if (getEmailBCC() == null) {
			setEmailBCC(new HashSet<>());
		}
		getEmailBCC().add(item);
	}

	/**
	 * Remove the given {@link EmailAddress} item from the {@code emailBCC}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeEmailBCC(EmailAddress item) {
		if (getEmailBCC() == null) {
			return;
		}
		getEmailBCC().remove(item);
	}

	/**
	 * Clear the {@code emailBCC} collection.
	 *
	 */
	public void clearEmailBCC() {
		if (getEmailBCC() != null) {
			getEmailBCC().clear();
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
		if (!(obj instanceof Communication)) return false;

		final Communication other = (Communication) obj;
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
			.add("enableAttachment", getEnableAttachment())
			.add("enableEmailTemplate", getEnableEmailTemplate())
			.omitNullValues()
			.toString();
	}
}
