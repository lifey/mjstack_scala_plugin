package com.performizeit.mjstack.scala;

import com.performizeit.mjstack.api.Plugin;
import com.performizeit.mjstack.api.JStackMapper;
import com.performizeit.mjstack.parser.JStackMetadataStack;
import com.performizeit.mjstack.parser.JStackStack;
import com.sparktale.bugtale.server.common.lang.scala.ScalaUtil;

import java.util.ArrayList;
import java.util.HashMap;

@Plugin
public class MJStackScalaPlugin implements JStackMapper{



    private static String scalafyStackFrame(String line) {
        int fnStart = line.indexOf("(") ;
        int atStart =  line.indexOf("at") ;
        String fileName = line.substring(fnStart);
        String at = line.substring(0,atStart+3);
        String pkgClsMthd = line.substring(atStart+3,fnStart);

        String method = pkgClsMthd.substring(pkgClsMthd.lastIndexOf(".")+1);
        pkgClsMthd = pkgClsMthd.substring(0,pkgClsMthd.lastIndexOf("."));
        String className = pkgClsMthd.substring(pkgClsMthd.lastIndexOf(".")+1);
        String pkg = pkgClsMthd.substring(0,pkgClsMthd.lastIndexOf("."));
        return at+pkg+"."+ ScalaUtil.createScalaFrameTitle(className, method)+fileName;
    }

    protected static String scalafyStack(String stackTrace) {
        String[] lines = stackTrace.split("\n");
        StringBuilder newStack = new StringBuilder();
        for (String line:lines) {
            newStack.append(scalafyStackFrame(line)).append("\n");
        }
        return newStack.toString();
    }

    @Override
    public JStackMetadataStack map(JStackMetadataStack stck) {
        HashMap<String,Object> mtd = stck.cloneMetaData();
        JStackStack jss = (JStackStack) mtd.get("stack");
        jss.setLinesOfStack(scalafyStack(jss.toString()));


        return      new JStackMetadataStack(mtd);

    }

    @Override
    public String getHelpLine() {
        return "scala plugin - makes the stack trace scala friendly ";
    }
}
