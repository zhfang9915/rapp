/**
 * 创建于 2016年8月28日 下午9:01:07
 *
 */

package org.rapp.controller.base;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.rapp.comm.util.RandCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @ClassName: RandCodeController 
 * @Description: TODO
 * @author zhfang
 *  
 */
@Controller
@RequestMapping("/base")
public class RandCodeController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value="/randcode",method=RequestMethod.GET)
	public void createRandCode(HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = RandCodeUtil.generateVerifyCode(4); 
        logger.info("生成的随机验证码为 {}", verifyCode);
        
        //存入会话session  
        getSession().setAttribute("randCode", verifyCode.toLowerCase()); 
        //生成图片  
        int w = 200, h = 80;  
        RandCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);  
	}
}
