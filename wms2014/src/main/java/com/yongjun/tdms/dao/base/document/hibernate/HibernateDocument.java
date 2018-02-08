package com.yongjun.tdms.dao.base.document.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.document.DocumentDao;
import com.yongjun.tdms.model.base.document.Document;

public class HibernateDocument extends BaseHibernateDao implements DocumentDao {

	public Document loadDocument(Long documentId) {
		return this.load(Document.class, documentId);
	}

	public List<Document> loadAllDocuments(Long[] documentIds) {
		return this.loadAll(Document.class, documentIds);
	}

	public List<Document> loadAllDocuments() {
		return this.loadAll(Document.class);
	}

	public void storeDocument(Document document) {
		this.store(document);
	}

	public void deleteDocument(Document document) {
		this.delete(document);
	}

	public void deleteAllDocuments(Collection<Document> documentIds) {
		this.deleteAll(documentIds);
	}

}
