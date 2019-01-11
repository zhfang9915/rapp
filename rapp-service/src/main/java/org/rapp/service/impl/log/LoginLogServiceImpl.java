package org.rapp.service.impl.log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.rapp.comm.util.PrimaryKeyUtil;
import org.rapp.pojo.log.LoginLog;
import org.rapp.repository.log.LoginLogDao;
import org.rapp.service.log.LoginLogService;
import org.rapp.service.util.IP;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	
	@Resource
	LoginLogDao logDao;

	@Async
	@Override
	public void saveLoginLog(HttpServletRequest request) {
		LoginLog log = new LoginLog();
		log.setId(PrimaryKeyUtil.createPrimaryKey());
		log.setUserName(request.getParameter("username"));
		log.setIp(IP.getIpAddress(request));
		logDao.insertLoginLog(log);
	}

}
