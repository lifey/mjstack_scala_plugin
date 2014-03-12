package com.sparktale.bugtale.server.common.lang.scala;

import javax.script.ScriptEngineManager;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparktale.rhino.TakipiScalaUtil;

public class ScalaUtil
{
	private static final Logger logger = LoggerFactory.getLogger(ScalaUtil.class);
	
	private final ScriptEngineManager scm;
	private final Context mctx;
	private final Scriptable scope;
	private final TakipiScalaUtil util;
	private final Function jsCreateScalaFrameTitle;
	private final Function jsPrettifyScalaVariableName;
	
	public static String createScalaFrameTitle(String className, String methodName)
	{
		ScalaUtil helper = null;
		
		try
		{
			helper = new ScalaUtil();
			
			return helper.doCreateScalaFrameTitle(className, methodName);
		}
		catch (Exception e)
		{
			logger.error("createScalaFrameTitle error for class:" + className + " method: " + methodName, e);
		}
		finally
		{
			if (helper != null)
			{
				helper.finish();
			}
		}
		
		return prettiflyJavaClassAndMethodNames(className, methodName);
	}
	
	public static String prettiflyJavaClassAndMethodNames(String className, String methodName)
	{
		return String.format("%s.%s", toSimpleClassName(className), methodName);
	}
	
	public static String toSimpleClassName(String className)
	{
		int loc = className.replace('/', '.').lastIndexOf('.');
		
		if (loc >= 0)
		{
			return className.substring(loc + 1);
		}
		
		return className;
	}
	
	public static String prettifyScalaVariableName(String varName)
	{
		ScalaUtil helper = null;
		
		try
		{
			helper = new ScalaUtil();
			
			return helper.doPrettifyScalaVariableName(varName);
		}
		catch (Throwable e)
		{
			logger.error("doPrettifyScalaVariableName error for var: " + varName, e);
		}
		finally
		{
			if (helper != null)
			{
				helper.finish();
			}
		}
		
		return varName;
	}
	
	private ScalaUtil()
	{
		scm = new ScriptEngineManager();
		mctx = Context.enter();
		scm.getEngineByExtension("js");
		scope = mctx.initStandardObjects();
		
		util = new TakipiScalaUtil();
		util.call(mctx, scope, scope, null);
		
		jsCreateScalaFrameTitle = (Function)scope.get("createScalaFrameTitle", null);
		jsPrettifyScalaVariableName = (Function)scope.get("prettifyScalaVariableName", null);
	}
	
	private void finish()
	{
		Context.exit();
	}
	
	private String doCreateScalaFrameTitle(String className, String methodName)
	{
		Object[] objs = new Object[2];
		objs[0] = className;
		objs[1] = methodName;
		
		Object resp = jsCreateScalaFrameTitle.call(mctx, scope, null, objs);
		
		if (resp != null)
		{
			return resp.toString();
		}
		
		return prettiflyJavaClassAndMethodNames(className, methodName);
	}
	
	private String doPrettifyScalaVariableName(String varName)
	{
		Object[] objs = new Object[1];
		objs[0] = varName;
		
		Object resp = jsPrettifyScalaVariableName.call(mctx, scope, null, objs);
		
		if (resp != null)
		{
			return resp.toString();
		}
		
		return varName;
	}
}
