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
package com.kline.knowledge.db;

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
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import com.kline.config.db.km.Category;

@Entity
@Table(name = "KNOWLEDGE_KNOWLEDGE_MANAGEMENT", indexes = { @Index(columnList = "knowledge_category") })
public class KnowledgeManagement extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KNOWLEDGE_KNOWLEDGE_MANAGEMENT_SEQ")
	@SequenceGenerator(name = "KNOWLEDGE_KNOWLEDGE_MANAGEMENT_SEQ", sequenceName = "KNOWLEDGE_KNOWLEDGE_MANAGEMENT_SEQ", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Category knowledgeCategory;

	@Widget(title = "Title")
	private String knowledgeTitle;

	@Widget(title = "Content")
	@Size(max = 50000)
	private String knowledgeContent;

	@Widget(title = "Owner")
	private String knowledgeOwner;

	@Widget(title = "Publish Date")
	private LocalDateTime knowledgePublishDate;

	private Boolean knowledgeEditable = Boolean.TRUE;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<KnowledgeDocument> knowledgeDocument;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public KnowledgeManagement() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Category getKnowledgeCategory() {
		return knowledgeCategory;
	}

	public void setKnowledgeCategory(Category knowledgeCategory) {
		this.knowledgeCategory = knowledgeCategory;
	}

	public String getKnowledgeTitle() {
		return knowledgeTitle;
	}

	public void setKnowledgeTitle(String knowledgeTitle) {
		this.knowledgeTitle = knowledgeTitle;
	}

	public String getKnowledgeContent() {
		return knowledgeContent;
	}

	public void setKnowledgeContent(String knowledgeContent) {
		this.knowledgeContent = knowledgeContent;
	}

	public String getKnowledgeOwner() {
		return knowledgeOwner;
	}

	public void setKnowledgeOwner(String knowledgeOwner) {
		this.knowledgeOwner = knowledgeOwner;
	}

	public LocalDateTime getKnowledgePublishDate() {
		return knowledgePublishDate;
	}

	public void setKnowledgePublishDate(LocalDateTime knowledgePublishDate) {
		this.knowledgePublishDate = knowledgePublishDate;
	}

	public Boolean getKnowledgeEditable() {
		return knowledgeEditable == null ? Boolean.FALSE : knowledgeEditable;
	}

	public void setKnowledgeEditable(Boolean knowledgeEditable) {
		this.knowledgeEditable = knowledgeEditable;
	}

	public List<KnowledgeDocument> getKnowledgeDocument() {
		return knowledgeDocument;
	}

	public void setKnowledgeDocument(List<KnowledgeDocument> knowledgeDocument) {
		this.knowledgeDocument = knowledgeDocument;
	}

	/**
	 * Add the given {@link KnowledgeDocument} item to the {@code knowledgeDocument}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addKnowledgeDocument(KnowledgeDocument item) {
		if (getKnowledgeDocument() == null) {
			setKnowledgeDocument(new ArrayList<>());
		}
		getKnowledgeDocument().add(item);
	}

	/**
	 * Remove the given {@link KnowledgeDocument} item from the {@code knowledgeDocument}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeKnowledgeDocument(KnowledgeDocument item) {
		if (getKnowledgeDocument() == null) {
			return;
		}
		getKnowledgeDocument().remove(item);
	}

	/**
	 * Clear the {@code knowledgeDocument} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearKnowledgeDocument() {
		if (getKnowledgeDocument() != null) {
			getKnowledgeDocument().clear();
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
		if (!(obj instanceof KnowledgeManagement)) return false;

		final KnowledgeManagement other = (KnowledgeManagement) obj;
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
			.add("knowledgeTitle", getKnowledgeTitle())
			.add("knowledgeContent", getKnowledgeContent())
			.add("knowledgeOwner", getKnowledgeOwner())
			.add("knowledgePublishDate", getKnowledgePublishDate())
			.add("knowledgeEditable", getKnowledgeEditable())
			.omitNullValues()
			.toString();
	}
}
