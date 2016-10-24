import junit.framework.TestCase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by kirill on 24/10/16.
 */
public class tetsfile  extends TestCase{

    public void testwrite() throws IOException{
        String s="dsadsa";
        String s1="dsaddsads";
        String s3="dasdasdasdsa";
        String s0=s+"\n"+s1+"\n"+s3;
        OutputStream outputStream = new FileOutputStream("/home/kirill/IdeaProjects/TestTask/src/main/webapp/dateBaseConfig");
        outputStream.write(s0.getBytes());

        outputStream.close();
    }
}
