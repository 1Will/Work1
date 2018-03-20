package com.ahctx.reward.common.factory.io;

import com.ahctx.reward.common.factory.FactoryProvider;
import com.ahctx.reward.common.util.io.DownloadHandler;


public class DownloadFileFactory implements FactoryProvider {

	@Override
	public Object produce() {
		return new DownloadHandler();
	}

}
