/*    */ package com.yongjun.tdms.service.yongJunSequence.pojo;

import com.yongjun.pluto.sequence.model.sequence.SequenceFormatter;
import com.yongjun.pluto.sequence.model.sequence.SequenceGeneratorFactory;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.sequence.service.pojo.DefaultSequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*    */ 
/*    */ 
/*    */ public class DefaultYongJunSequenceManager extends BaseManager
/*    */   implements YongJunSequenceManager

/*    */ {
	
	 private final SequenceFormatter formatter;
	   protected final SequenceGeneratorFactory sequenceGeneratorFactory;
	public DefaultYongJunSequenceManager(SequenceFormatter formatter, SequenceGeneratorFactory sequenceGeneratorFactory) {
		this.formatter = formatter;
		this.sequenceGeneratorFactory = sequenceGeneratorFactory;
	}
	public String generateByCodeType(String codeType) {
		//创建sequenceManager 实现类接口，用于创建编码
		SequenceManager sequenceManager = new DefaultSequenceManager(codeType, formatter, sequenceGeneratorFactory);
		String result = (String)sequenceManager.generate("-");
		return YongJunSequenceConstant.CODE_ROOT+"-"+result;
	}
	public String generateeCodeTypeReplacFormtter(String codeType, String toCode) {
		//创建sequenceManager 实现类接口，用于创建编码
				SequenceManager sequenceManager = new DefaultSequenceManager(codeType, formatter, sequenceGeneratorFactory);
				String result = (String)sequenceManager.generate("-");
				result=result.replace(codeType, toCode);
				return YongJunSequenceConstant.CODE_ROOT+"-"+result;
	}
	
	
 }

