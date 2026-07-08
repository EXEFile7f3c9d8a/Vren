package vren.vrenbrowsercore.storage;

import java.util.*;

public class Elements{
    private boolean isSpecial = false;
    private boolean isClosed = false;
    private final Map<String, String> attitudes = new HashMap<>();

    private final List<Elements> children = new ArrayList<>();
    public Elements(){
        attitudes.put("type"   , "");
        attitudes.put("href"   , "");
        attitudes.put("rel"    , "");
        attitudes.put("src"    , "");
        attitudes.put("clazz"  , "");
        attitudes.put("id"     , "");
        attitudes.put("target" , "");
        attitudes.put("title"  , "");
        attitudes.put("charset", "");
        attitudes.put("lang"   , "");
        attitudes.put("text"   , "");
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('<').append(attitudes.get("type")).append(' ');
        List<Map.Entry<String, String>> entries = new ArrayList<>(attitudes.entrySet());
        for(int i = 0; i < attitudes.size(); i++){
            Map.Entry<String, String> entry = entries.get(i);
            String value = entry.getValue();
            if(!Objects.equals(value, ""))sb.append(entry.getKey()).append("=\"").append(value).append("\" ");
        }
        sb.replace(sb.length()-1, sb.length(), ">");
        for(int i = 0; i < children.size(); i++){
            sb.append(children.get(i).toString());
        }
        sb.append("</").append(attitudes.get("type")).append('>');
        return sb.toString();
    }
    public Elements set(String type, String msg){
        switch(type){
            case "type"    -> attitudes.replace("type"   , msg);
            case "href"    -> attitudes.replace("href"   , msg);
            case "rel"     -> attitudes.replace("rel"    , msg);
            case "src"     -> attitudes.replace("src"    , msg);
            case "id"      -> attitudes.replace("id"     , msg);
            case "target"  -> attitudes.replace("target" , msg);
            case "charset" -> attitudes.replace("charset", msg);
            case "lang"    -> attitudes.replace("lang"   , msg);
            case "text"    -> attitudes.replace("text"   , msg);
        }
        return this;
    }
    public String get(String type){
        return switch(type){
            case "type"    -> attitudes.get("type"   );
            case "href"    -> attitudes.get("href"   );
            case "rel"     -> attitudes.get("rel"    );
            case "src"     -> attitudes.get("src"    );
            case "id"      -> attitudes.get("id"     );
            case "target"  -> attitudes.get("target" );
            case "charset" -> attitudes.get("charset");
            case "lang"    -> attitudes.get("lang"   );
            case "text"    -> attitudes.get("text"   );
            default -> "null";
        };
    }
    public Elements set(String type, boolean bool){
        return this;
    }
    public Elements setHref(String href){
        this.set("href", href);
        return this;
    }
    public String getHref(){
        return get("href") ;
    }
    public Elements setType(String type){
        this.set("type", type);
        return this;
    }
    public String getType(){
        return get("type") ;
    }
    public Elements putChild(Elements ele){
        children.add(ele);
        return this;
    }
    public Elements putChildren(List<Elements> ele){
        children.addAll(ele);
        return this;
    }
    public List<Elements> getChildren() {
        return children;
    }
    public String getRel() {
        return get("rel");
    }
    public Elements setRel(String rel) {
        this.set("rel", rel);
        return this;
    }
    public String getSrc() {
        return get("src");
    }
    public Elements setSrc(String src) {
        this.set("src", src);
        return this;
    }
    public String getClazz() {
        return get("clazz");
    }
    public Elements setClazz(String clazz) {
        this.set("clazz", clazz);
        return this;
    }
    public String getId() {
        return get("id");
    }
    public Elements setId(String id) {
        this.set("id", id);
        return this;
    }
    public String getTarget() {
        return get("target");
    }
    public Elements setTarget(String target) {
        this.set("target", target);
        return this;
    }
    public String getTitle() {
        return get("title");
    }
    public Elements setTitle(String title) {
        this.set("title", title);
        return this;
    }
    public String getCharset() {
        return get("charset");
    }
    public Elements setCharset(String charset) {
        this.set("charset", charset);
        return this;
    }
    public String getLang() {
        return get("lang");
    }
    public Elements setLang(String lang) {
        this.set("lang", lang);
        return this;
    }
    public String getText() {
        return get("text");
    }
    public Elements setText(String text) {
        this.set("text", text);
        return this;
    }
    public boolean isSpecial(){
        return isSpecial;
    }
    public Elements setSpecial(boolean special){
        isSpecial = special;
        return this;
    }
    public boolean isClosed(){
        return isClosed;
    }
    public Elements setClosed(boolean closed){
        isClosed = closed;
        return this;
    }
}
