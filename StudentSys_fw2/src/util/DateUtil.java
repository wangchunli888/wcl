package util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * @author yangxuefeng 2016-9-6
 *
 */
public class DateUtil {
	/**
	 * 将字符串日期转换为date类型
	 * @param str 日期字符串
	 * @return
	 */
	public static Date toDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		
			try {
				date=sdf.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return date;
	}
	/**
	 * 将字符串日期转换为date类型
	 * @param str 日期字符串
	 * @param formatStr 日期的格式
	 * @return
	 */
	public static Date toDate(String str,String formatStr){
		
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		
			try {
				date=sdf.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return date;
	}
	/**
	 * 将日期类型格式化输出为字符串
	 * @param date 
	 * @param formatStr
	 * @return
	 */
	public static String toString(Date date,String formatStr){
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
		 return sdf.format(date);
		
	}
}
