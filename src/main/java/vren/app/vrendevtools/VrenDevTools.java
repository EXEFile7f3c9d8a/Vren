package vren.app.vrendevtools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Objects;

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
    public static boolean runnableEquals(Runnable run, Runnable rab) {
        SerializedLambda runLambda;
        Method runMeth;
        SerializedLambda rabLambda;
        Method rabMeth;
        try{
            runMeth = run.getClass().getDeclaredMethod("writeReplace");
            rabMeth = rab.getClass().getDeclaredMethod("writeReplace");
            runMeth.setAccessible(true);
            rabMeth.setAccessible(true);
            runLambda = (SerializedLambda) runMeth.invoke(run);
            rabLambda = (SerializedLambda) rabMeth.invoke(rab);
        }catch(Exception e){throw new RuntimeException("Error: " + e);}
        if(!Objects.equals(runLambda.getImplMethodSignature(), rabLambda.getImplMethodSignature()))return false;
        if(!Objects.equals(runLambda.getImplMethodKind(), rabLambda.getImplMethodKind()))return false;
        if(!Objects.equals(runLambda.getFunctionalInterfaceClass(), rabLambda.getFunctionalInterfaceClass()))return false;
        if(!Objects.equals(runLambda.getImplClass(), rabLambda.getImplClass()))return false;
        if(!Objects.equals(runLambda.getImplMethodName(), rabLambda.getImplMethodName()))return false;
        return true;
    }
}
