/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.pluto.spring.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.yongjun.pluto.spring.controller.UrlPathController;

public class JasperReportsImageController extends UrlPathController
{

    public JasperReportsImageController()
    {
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws Exception
    {
        Map imagesMap = (Map)WebUtils.getSessionAttribute(httpServletRequest, "IMAGES_MAP");
        if(imagesMap != null)
        {
            String imageName = urlPathHelper.getPathWithinServletMapping(httpServletRequest);
            if(imageName != null)
            {
                if(imageName.startsWith("/"))
                    imageName = imageName.substring(1);
                byte imageData[] = (byte[])(byte[])imagesMap.get(imageName);
                FileCopyUtils.copy(imageData, httpServletResponse.getOutputStream());
                httpServletResponse.setContentLength(imageData.length);
            }
        }
        return null;
    }

    public static final String IMAGES_MAP_KEY = "IMAGES_MAP";
}