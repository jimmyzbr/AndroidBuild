/**s
 *  Created By swaggyz
 */

package com.swaggyz.genbuildconfig;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

/**
 * 生成BuildConfig文件
 */
public class GenBuildConfig {
	public static final String ABDIR = "abdir";
	public static final String PACKAGE = "pakage";
	public static final String DEBUG = "debug";
	public static final String FILENAME = "BuildConfig.java";

	public static void main(String[] args) {
		int paramsLength = args.length;
		if (paramsLength % 2 == 0) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			for (int i = 0; i < paramsLength; i = i + 2) {
				params.put(args[i], args[i + 1]);
			}
			String content = getBuildConfigContent(params);
			//创建文件夹
			File dir = new File(params.get(ABDIR).toString());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			createFile(content, dir, params.get(PACKAGE).toString());
		}
	}

	/**
	 * 创建文件
	 * @param content
	 * @param dir
	 * @param packageStr
	 */
	public static void createFile(String content, File dir, String packageStr) {
		File javaFileDir = new File(dir, packageStr.replace(".", “/“));
		if (!javaFileDir.exists()) {
			javaFileDir.mkdirs();
		}
		File javaFile = new File(javaFileDir, FILENAME);
		javaFile.setWritable(true);
		try {
			FileWriter writer = new FileWriter(javaFile, false);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成文件内容
	 * @param params
	 * @return
	 */
	public static String getBuildConfigContent(HashMap<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("/**\r\n");
		sb.append(" * Create By com.swaggyz.genbuildconfig\r\n");
		sb.append(" */\r\n");
		sb.append("\r\n");
		sb.append("package " + params.get(PACKAGE) + ";\r\n");
		sb.append("\r\n");
		sb.append("public final class BuildConfig {\r\n");
		sb.append("  public final static boolean DEBUG = " + params.get(DEBUG) + ";\r\n");
		sb.append("}");
		return sb.toString();
	}

}
