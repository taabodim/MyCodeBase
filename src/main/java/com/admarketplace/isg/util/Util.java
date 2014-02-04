package com.admarketplace.isg.util;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {
	public static void main(String args[]) {
		dumpAllStackTraces() ;	
	}

	public static Date getDateMonthFromNow(int monthFromNow) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthFromNow);
		Date result = cal.getTime();
		return result;
	}

	public static void dumpAllStackTraces() {
		for (Map.Entry<Thread, StackTraceElement[]> entry : Thread
				.getAllStackTraces().entrySet()) {
			System.out.println(entry.getKey().getName() + ":");
			for (StackTraceElement element : entry.getValue())
				System.out.println("\t" + element);
		}
	}

	public static void runMethodOfAClass(String className, String methodName)
			throws Exception {
		Class<?> classObj = Class.forName(className);
		Method m = classObj.getDeclaredMethod(methodName, null);
		Object instance = classObj.newInstance();
		m.invoke(instance, null);
	}

	public static void echo(String str) {
		System.out.println(str);
	}

	public static void printProcessId() {
		Util.echo(ManagementFactory.getRuntimeMXBean().getName());
	}

	public static Map<String, String> StringToMap(String str) {
		String tokens[] = str.split(" |=");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < tokens.length - 1;) {
			map.put(tokens[i++], tokens[i++]);
		}
		return map;
	}

	public static String mapToString(Map<? extends Object, ? extends Object> map) {
		if (map == null)
			return "null";
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<? extends Object, ? extends Object> entry : map
				.entrySet()) {
			Object key = entry.getKey();
			Object val = entry.getValue();
			sb.append(key).append("=");
			if (val == null)
				sb.append("null");
			else
				sb.append(val);
			sb.append("\n");
		}
		return sb.toString();
	}

	public static boolean interruptAndWaitToDie(Thread t, long timeout) {
		if (t == null)
			throw new IllegalArgumentException("Thread can not be null");
		t.interrupt(); // interrupts the sleep()
		try {
			t.join(timeout);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // set interrupt flag again
		}
		return t.isAlive();
	}

	public static String dumpThreads() {
		StringBuilder sb = new StringBuilder();
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		long[] ids = bean.getAllThreadIds();
		_printThreads(bean, ids, sb);
		long[] deadlocks = bean.findDeadlockedThreads();
		if (deadlocks != null && deadlocks.length > 0) {
			sb.append("deadlocked threads:\n");
			_printThreads(bean, deadlocks, sb);
		}

		deadlocks = bean.findMonitorDeadlockedThreads();
		if (deadlocks != null && deadlocks.length > 0) {
			sb.append("monitor deadlocked threads:\n");
			_printThreads(bean, deadlocks, sb);
		}
		return sb.toString();
	}

	protected static void _printThreads(ThreadMXBean bean, long[] ids,
			StringBuilder sb) {
		ThreadInfo[] threads = bean.getThreadInfo(ids, 20);
		for (int i = 0; i < threads.length; i++) {
			ThreadInfo info = threads[i];
			if (info == null)
				continue;
			sb.append(info.getThreadName()).append(":\n");
			StackTraceElement[] stack_trace = info.getStackTrace();
			for (int j = 0; j < stack_trace.length; j++) {
				StackTraceElement el = stack_trace[j];
				sb.append("    at ").append(el.getClassName()).append(".")
						.append(el.getMethodName());
				sb.append("(").append(el.getFileName()).append(":")
						.append(el.getLineNumber()).append(")");
				sb.append("\n");
			}
			sb.append("\n\n");
		}
	}

	/**
	 * Returns a random value in the range [1 - range]
	 * 
	 * @return
	 */
	public static <T> double random(int range) {
		Random rn = new Random();
		return rn.nextInt(range);

		// return ((rn.nextInt(range) * Long.parseLong(range.toString())) %
		// Long.parseLong(range.toString())) + 1;
	}

	/**
	 * Sleep for timeout msecs. Returns when timeout has elapsed or thread was
	 * interrupted
	 */
	public static void sleep(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static <T> boolean match(T obj1, T obj2) {
		if (obj1 == null && obj2 == null)
			return true;
		if (obj1 != null)
			return obj1.equals(obj2);
		else
			return obj2.equals(obj1);
	}

	public static byte[] readFileContents(InputStream input) throws IOException {
		byte contents[] = new byte[10000], buf[] = new byte[1024];
		InputStream in = new BufferedInputStream(input);
		int bytes_read = 0;

		for (;;) {
			int tmp = in.read(buf, 0, buf.length);
			if (tmp == -1)
				break;
			System.arraycopy(buf, 0, contents, bytes_read, tmp);
			bytes_read += tmp;
		}

		byte[] retval = new byte[bytes_read];
		System.arraycopy(contents, 0, retval, 0, bytes_read);
		return retval;
	}

	public static byte[] readFileContents(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists())
			throw new FileNotFoundException(filename);
		long length = file.length();
		byte contents[] = new byte[(int) length];
		InputStream in = new BufferedInputStream(new FileInputStream(filename));
		int bytes_read = 0;

		for (;;) {
			int tmp = in
					.read(contents, bytes_read, (int) (length - bytes_read));
			if (tmp == -1)
				break;
			bytes_read += tmp;
			if (bytes_read == length)
				break;
		}
		return contents;
	}

	public static String readFile(String filename) throws FileNotFoundException {
		FileInputStream in = new FileInputStream(filename);
		return readContents(in);
	}

	public static String readFileEveryNLine(String filename, int nthLine)
			throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		String lines = "";
		int lineNumber = 0;
		while ((line = br.readLine()) != null) {
			lineNumber++;
			if (lineNumber % nthLine == 0)
				lines += line + "\n";
		}
		br.close();
		return lines;

	}

	public static ArrayList<String> readFileLineByLine(String filename)
			throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		br.close();
		return lines;

	}

	public static void writeFile(String content, String filename) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filename));
			writer.write(content);

		} catch (IOException e) {
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

		}
	}

	public static String readContents(InputStream input) {
		StringBuilder sb = new StringBuilder();
		int ch;
		while (true) {
			try {
				ch = input.read();
				if (ch != -1)
					sb.append((char) ch);
				else
					break;
			} catch (IOException e) {
				break;
			}
		}
		return sb.toString();
	}

	public static void printList(List<Object> objs) {
		for (Object obj : objs) {
			Util.echo("obj in list is " + obj.toString());
		}
	}

	public static String formatDate(String currentFormat,String newFormat,String date) throws ParseException
	{
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date myDate = dt.parse(date); 
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");
		System.out.println(dt1.format(date));
		return myDate.toString();
		
	}
}
