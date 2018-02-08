package com.yongjun.tdms.service.yongJunSequence;


public abstract interface YongJunSequenceManager
{
	/**
	 * 根据传入的类型，获取系统生成的编码
	 * @param codeType 类型
	 * @return
	 */
public String generateByCodeType(String codeType);
/**
 * 根据传入的类型，获取系统生成的编码，同时将类型替换成tocode
 * @param codeType  类型
 * @param toCode 替换后的 codeType
 * @return
 */
public String generateeCodeTypeReplacFormtter(String codeType,String toCode);

}
