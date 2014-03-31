package com.performizeit.mjstack.scala;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MJStackScalaPluginTest {
    private static String st="    at com.company.IdentityVerifier$$anonfun$go$2$$anonfun$apply$2.apply$mcII$sp(IdentityVerifier.scala:19)\n" +
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
    private static String scalaStack = "    at com.company.IdentityVerifier go λ-2 λ-2 (specialized)(IdentityVerifier.scala:19)\n" +
            "    at com.company.IdentityVerifier go λ-2 λ-2(IdentityVerifier.scala:17)\n" +
            "    at com.company.IdentityVerifier go λ-2 λ-2(IdentityVerifier.scala:17)\n" +
            "    at scala.collection.TraversableLike map λ-1(TraversableLike.scala:244)\n" +
            "    at scala.collection.TraversableLike map λ-1(TraversableLike.scala:244)\n" +
            "    at scala.collection.immutable.List foreach(List.scala:318)\n" +
            "    at scala.collection.TraversableLike map(TraversableLike.scala:244)\n" +
            "    at scala.collection.AbstractTraversable map(Traversable.scala:105)\n" +
            "    at com.company.IdentityVerifier go λ-2(IdentityVerifier.scala:17)\n" +
            "    at com.company.IdentityVerifier go λ-2(IdentityVerifier.scala:16)\n" +
            "    at scala.collection.TraversableLike flatMap λ-1(TraversableLike.scala:251)\n" +
            "    at scala.collection.TraversableLike flatMap λ-1(TraversableLike.scala:251)\n" +
            "    at scala.collection.immutable.List foreach(List.scala:318)\n" +
            "    at scala.collection.TraversableLike flatMap(TraversableLike.scala:251)\n" +
            "    at scala.collection.AbstractTraversable flatMap(Traversable.scala:105)\n" +
            "    at com.company.IdentityVerifier go(IdentityVerifier.scala:16)\n" +
            "    at com.company.UserMap setLastUserId(UserMap.scala:12)\n" +
            "    at com.company.UserConsumer setCurrentUser(UserConsumer.java:69)\n" +
            "    at com.company.UserConsumer consume(UserConsumer.java:64)\n" +
            "    at com.company.UserProducer execute(UserProducer.java:19)\n" +
            "    at com.company.UserCreator execute(UserCreator.java:18)\n" +
            "    at com.company.UserCreatorMain$1 run(UserCreatorMain.java:37)\n" +
            "    at com.company.UserCreatorMain main(UserCreatorMain.java:51)\n";
    @Test
    public void scalafyMyStack() throws Exception {
        MJStackScalaPlugin pl = new MJStackScalaPlugin();
       // System.out.println(pl.scalafyStack(st));
      //  assertEquals(scalaStack,pl.scalafyStack(st));
    }
    @Test
    public void tryLine() throws Exception {
        String line = "    at HelloMe$.main(HelloMe.scala:4)";
        MJStackScalaPlugin.scalafyStackFrame(line);
    }
}
