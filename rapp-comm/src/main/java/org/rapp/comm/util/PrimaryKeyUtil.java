package org.rapp.comm.util;

import java.util.Date;

public class PrimaryKeyUtil {

	/**
	 * 生成主键KEY
	 * @return
	 */
	public static String createPrimaryKey() {
		String timeSeq = CalendarUtil.parseLongTime(new Date());
		int rand = (int)((Math.random()*9+1)*100000);
		return timeSeq + rand;
	}
}
