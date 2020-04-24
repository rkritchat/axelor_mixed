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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.axelor.meta.db.MetaFile;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "KNOWLEDGE_KNOWLEDGE_DOCUMENT", indexes = { @Index(columnList = "document") })
public class KnowledgeDocument extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KNOWLEDGE_KNOWLEDGE_DOCUMENT_SEQ")
	@SequenceGenerator(name = "KNOWLEDGE_KNOWLEDGE_DOCUMENT_SEQ", sequenceName = "KNOWLEDGE_KNOWLEDGE_DOCUMENT_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Document")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private MetaFile document;

	private Long metaFileId = 0L;

	private Long knowledgeId = 0L;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public KnowledgeDocument() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public MetaFile getDocument() {
		return document;
	}

	public void setDocument(MetaFile document) {
		this.document = document;
	}

	public Long getMetaFileId() {
		return metaFileId == null ? 0L : metaFileId;
	}

	public void setMetaFileId(Long metaFileId) {
		this.metaFileId = metaFileId;
	}

	public Long getKnowledgeId() {
		return knowledgeId == null ? 0L : knowledgeId;
	}

	public void setKnowledgeId(Long knowledgeId) {
		this.knowledgeId = knowledgeId;
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
		if (!(obj instanceof KnowledgeDocument)) return false;

		final KnowledgeDocument other = (KnowledgeDocument) obj;
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
			.add("metaFileId", getMetaFileId())
			.add("knowledgeId", getKnowledgeId())
			.omitNullValues()
			.toString();
	}
}
