package vren.VrenMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class VrenMap {
    private final HashMap<Object, Object> tagStorage = new HashMap<>();
    private static class Tags {
        public HashMap<Object, Object> Value = new HashMap<>();
        public Object LockTag = null;
        public Tags(Object tag, Object[] valueName, Object... value){
            if(tag == null)return;
            if(valueName == null && value != null){
                for(int i = 0; i < value.length; i++){
                    int temp;
                    if(value[i]!=null) temp = value[i].hashCode();
                    else temp = 0;
                    this.Value.put(new Object[]{null, temp}, value[i]);
                }
                this.LockTag = tag;
            }else if(value == null){
                this.LockTag = tag;
            }else{
                for(int i = 0; i < value.length; i++){
                    this.Value.put(new Object[]{valueName[i], value[i].hashCode()}, value[i]);
                }
                this.LockTag = tag;
            }
        }
        public void add(Object... Value){

        }
    }
    public void addTag(Object tag){
        addTag(tag, null, null);
    }
    public void addTag(Object tag, Object[] value){
        addTag(tag, null,  value);
    }
    public void addTag(Object tag, Object[] valueName, Object[] value){
        Object temp = new Tags(tag, valueName, value);
        tagStorage.put(tag, temp);
    }
    public void addValue(Object tag, Object... value){

    }
    public void setValue(){

    }
    public Object getValueOf(Object tag, String hash){
        return null;
    }
    public Object getTag(Object tag){
        return ((Tags)tagStorage.get(tag)).Value;
    }
    public Object getTagFormatted(Object tag){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<2; i++){

        }
        return sb.toString();
    }
    public String getTagReport(Object tag){
        if(tagStorage.get(tag) == null)return null;
        Map<Object, Object> ValueCopy = ((Tags)tagStorage.get(tag)).Value;
        if(ValueCopy.isEmpty()) return null;
        List<Object> keysetArray = new ArrayList<>(ValueCopy.keySet());
        List<Map.Entry<Object, Object>> entries = new ArrayList<>(ValueCopy.entrySet());
        StringBuilder sb = new StringBuilder();

        sb.append("====================Tag Report====================");
        sb.append(System.lineSeparator());
        for(int i = 0; i < ValueCopy.size(); i++){
            Map.Entry<Object, Object> entry = entries.get(i);
            Object[] currentKeyset = (Object[]) entry.getKey();
            Object currentValue = entry.getValue();
            Class<?> currentValueClass;
            String currentClassName;
            if(currentValue != null) {
                currentValueClass = currentValue.getClass();
                currentClassName = currentValueClass.getName();
            }else currentClassName = "null";
            sb
                    .append("    Current Value Name ─► ").append(currentKeyset[0])
                    .append(System.lineSeparator())
                    .append("    Current Value Hash ─► ").append(currentKeyset[1])
                    .append(System.lineSeparator())
                    .append("     Value")
                    .append(System.lineSeparator())
                    .append("       ├──Variable Class Name ─► ").append(currentClassName)
                    .append(System.lineSeparator())
                    .append("       │")
                    .append(System.lineSeparator())
                    .append("       ├──Variable in Binary Code")
                    .append(System.lineSeparator())
                    .append("       │   └► ").append(Arrays.toString(toBinaryCode(currentValue)))
                    .append(System.lineSeparator())
                    .append("       │")
                    .append(System.lineSeparator())
                    .append("       └──Variable in Hex")
                    .append(System.lineSeparator())
                    .append("           └► ").append(toHex(currentValue))
                    .append(System.lineSeparator())
                    .append("--------------------------------------------------");
        }
        sb.delete(sb.lastIndexOf(System.lineSeparator()), sb.length());
        return sb.toString();
    }
    public String getReport(){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
    public int tagCount(){
        return tagStorage.size();
    }
    public int valueCount(){
        return tagStorage.size();
    }
    public int valueCount(Object tag){
        if(tag != null){
            try{
                return ((Tags) tagStorage.get(tag)).Value.size();
            }catch (NullPointerException e){
                return 0;
            }
        }else{
            return 0;
        }
    }
    public static byte[] toBinaryCode(Object var){
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
    public static String byteToHex(byte[] var){
        if(var == null) return null;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < var.length; i++){
            sb.append(String.format("%02x", var[i]));
        }
        return sb.toString();
    }
    public static String toHex(Object var){
        if(var == null) return null;
        return byteToHex(toBinaryCode(var));
    }
}

