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

import java.util.HashSet;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.axelor.apps.message.db.EmailAddress;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "COMMUNICATION_KLINE_EMAIL_TEMPLATE", indexes = { @Index(columnList = "name") })
public class KlineEmailTemplate extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMUNICATION_KLINE_EMAIL_TEMPLATE_SEQ")
	@SequenceGenerator(name = "COMMUNICATION_KLINE_EMAIL_TEMPLATE_SEQ", sequenceName = "COMMUNICATION_KLINE_EMAIL_TEMPLATE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	@Size(max = 50000)
	private String body;

	@NotNull
	private String subject;

	private String toCCId;

	private String toBCCId;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<EmailAddress> emailCC;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<EmailAddress> emailBCC;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public KlineEmailTemplate() {
	}

	public KlineEmailTemplate(Set<EmailAddress> emailCC, Set<EmailAddress> emailBCC) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getToCCId() {
		return toCCId;
	}

	public void setToCCId(String toCCId) {
		this.toCCId = toCCId;
	}

	public String getToBCCId() {
		return toBCCId;
	}

	public void setToBCCId(String toBCCId) {
		this.toBCCId = toBCCId;
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
		if (!(obj instanceof KlineEmailTemplate)) return false;

		final KlineEmailTemplate other = (KlineEmailTemplate) obj;
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
			.add("name", getName())
			.add("body", getBody())
			.add("subject", getSubject())
			.add("toCCId", getToCCId())
			.add("toBCCId", getToBCCId())
			.omitNullValues()
			.toString();
	}
}
