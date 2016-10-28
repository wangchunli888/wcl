package test;

import org.apache.log4j.Logger;

public class Log4jText {
	
	private static Logger log=Logger.getLogger(Log4jText.class);
	
	public static void testDebug()
	{
		log.debug("这是一个debug日志输出");
	}
	public static void testInfo()
	{
		log.info("这是一个信息日志输出");
	}
	public static void testWarn()
	{
		log.warn("这是一个警告日志输出");
	}
	public static void testError()
	{
		log.error("这是一个错误日志输出");
	}
	public static void testFatal()
	{
		log.fatal("这是一个严重错误输出");
	}

	public static void main(String[] args) {
		testDebug();
		testInfo();
		testWarn();
		testError();
		testFatal();

		
		
	}

}
