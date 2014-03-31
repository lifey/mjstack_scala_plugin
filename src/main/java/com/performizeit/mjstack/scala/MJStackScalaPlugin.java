package com.performizeit.mjstack.scala;

import com.performizeit.mjstack.api.Plugin;
import com.performizeit.mjstack.api.JStackMapper;
import com.performizeit.mjstack.parser.JStackMetadataStack;
import com.performizeit.mjstack.parser.JStackStack;
import com.sparktale.bugtale.server.common.lang.scala.ScalaUtil;

import java.util.HashMap;

@Plugin(name = "scalafy", paramTypes = {}, description = "scala plugin - makes the stack trace scala friendly")
public class MJStackScalaPlugin implements JStackMapper {
     static String scalafyStackFrame(String stackFrame) {
        try {
            if (stackFrame.trim().length() == 0) return stackFrame;
            int fnStart = stackFrame.indexOf("(");
            int atStart = stackFrame.indexOf("at ");
            if ( atStart < 0) return stackFrame;
            String fileName;
            String pkgClsMthd ;
            if (fnStart<0 ) {
                fileName ="";
                pkgClsMthd = stackFrame.substring(atStart + 3);
            } else {
                fileName = stackFrame.substring(fnStart);
                pkgClsMthd = stackFrame.substring(atStart + 3, fnStart);
            }
            String at = stackFrame.substring(0, atStart + 3);

            String method = pkgClsMthd.substring(pkgClsMthd.lastIndexOf(".") + 1);
            pkgClsMthd = pkgClsMthd.substring(0, pkgClsMthd.lastIndexOf("."));
            String className;
            String pkg;
            if (pkgClsMthd.contains(".")) {   // class name contains package
                className = pkgClsMthd.substring(pkgClsMthd.lastIndexOf(".") + 1);
                pkg = pkgClsMthd.substring(0, pkgClsMthd.lastIndexOf("."))+".";
            } else {
                className =   pkgClsMthd;
                pkg = "";
            }
            return at + pkg  + ScalaUtil.createScalaFrameTitle(className, method) + fileName;
        } catch (RuntimeException r ) {
            System.out.println("Exception for:"+stackFrame);
            throw r;
        }
    }

    protected static String scalafyStack(String stackTrace) {
        String[] lines = stackTrace.split("\n");
        StringBuilder newStack = new StringBuilder();
        for (String line : lines) {
            newStack.append(scalafyStackFrame(line)).append("\n");
        }
        return newStack.toString();
    }

    @Override
    public JStackMetadataStack map(JStackMetadataStack stck) {
        HashMap<String, Object> mtd = stck.cloneMetaData();
        JStackStack jss = (JStackStack) mtd.get("stack");
        jss.setLinesOfStack(scalafyStack(jss.toString()));
        return new JStackMetadataStack(mtd);
    }
}
