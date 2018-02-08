package com.yongjun.tdms.dao.base.document;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.document.Document;

public interface DocumentDao {
	Document loadDocument(Long documentId);
	
	List<Document> loadAllDocuments(Long[] documentIds);
	
	List<Document> loadAllDocuments();
	
	void storeDocument(Document document);
	
	void deleteDocument(Document document);
	
	void deleteAllDocuments(Collection<Document> documentIds);

}
