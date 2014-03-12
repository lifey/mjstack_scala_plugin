package com.performizeit.mjstack.scala;

import com.sparktale.bugtale.server.common.lang.scala.ScalaUtil;

public class MJStackScalaPlugin {
    static String st="    at com.company.IdentityVerifier$$anonfun$go$2$$anonfun$apply$2.apply$mcII$sp(IdentityVerifier.scala:19)\n" +
            "    at com.company.IdentityVerifier$$anonfun$go$2$$anonfun$apply$2.apply(IdentityVerifier.scala:17)\n" +
            "    at com.company.IdentityVerifier$$anonfun$go$2$$anonfun$apply$2.apply(IdentityVerifier.scala:17)\n" +
            "    at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:244)\n" +
            "    at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:244)\n" +
            "    at scala.collection.immutable.List.foreach(List.scala:318)\n" +
            "    at scala.collection.TraversableLike$class.map(TraversableLike.scala:244)\n" +
            "    at scala.collection.AbstractTraversable.map(Traversable.scala:105)\n" +
            "    at com.company.IdentityVerifier$$anonfun$go$2.apply(IdentityVerifier.scala:17)\n" +
            "    at com.company.IdentityVerifier$$anonfun$go$2.apply(IdentityVerifier.scala:16)\n" +
            "    at scala.collection.TraversableLike$$anonfun$flatMap$1.apply(TraversableLike.scala:251)\n" +
            "    at scala.collection.TraversableLike$$anonfun$flatMap$1.apply(TraversableLike.scala:251)\n" +
            "    at scala.collection.immutable.List.foreach(List.scala:318)\n" +
            "    at scala.collection.TraversableLike$class.flatMap(TraversableLike.scala:251)\n" +
            "    at scala.collection.AbstractTraversable.flatMap(Traversable.scala:105)\n" +
            "    at com.company.IdentityVerifier$.go(IdentityVerifier.scala:16)\n" +
            "    at com.company.UserMap.setLastUserId(UserMap.scala:12)\n" +
            "    at com.company.UserConsumer.setCurrentUser(UserConsumer.java:69)\n" +
            "    at com.company.UserConsumer.consume(UserConsumer.java:64)\n" +
            "    at com.company.UserProducer.execute(UserProducer.java:19)\n" +
            "    at com.company.UserCreator.execute(UserCreator.java:18)\n" +
            "    at com.company.UserCreatorMain$1.run(UserCreatorMain.java:37)\n" +
            "    at com.company.UserCreatorMain.main(UserCreatorMain.java:51)";
    public static void main(String[] args) {
        System.out.println(st);
        String[] lines = st.split("\n");
        StringBuilder newStack = new StringBuilder();
        for (String line:lines) {
            newStack.append(scalafyStackFrame(line)).append("\n");
        }
        System.out.println(newStack.toString());
    }

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
}
