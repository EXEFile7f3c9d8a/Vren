package vren.vrendevtools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class VrenDevTools {
    public static byte[] toBinary(Object var){
        if(var == null) return null;
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(var);
            oos.close();
            return baos.toByteArray();
        }catch (IOException e) {
            return null;
        }
    }
    public static String binaryToHex(byte[] var){
        if(var == null) return null;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < var.length; i++){
            sb.append(String.format("%02x", var[i]));
        }
        return sb.toString();
    }
    public static String toHex(Object var){
        return var != null ? binaryToHex(toBinary(var)) : null;
    }
    public static String getClassName(Object obj){
        return obj != null ? obj.getClass().getName() : null;
    }
}
