package liwei.baway.com.girltest.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/10
 */

public class Input2Str {

    public static String input2Str(InputStream inputStream) throws IOException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        int length = 0;
//        byte[] arr = new byte[1024];
//        while ((length=inputStream.read(arr))!=-1){
//            byteArrayOutputStream.write(arr,0,length);
//
//        }
//        return byteArrayOutputStream.toString();
        ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream();
        int   i=-1;
        while((i=inputStream.read())!=-1){
            baos.write(i);
        }
        return   baos.toString();




    }





}
