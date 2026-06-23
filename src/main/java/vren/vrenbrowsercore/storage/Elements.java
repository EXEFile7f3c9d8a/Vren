package vren.vrenbrowsercore.storage;

import java.util.ArrayList;
import java.util.List;

public class Elements{
    private String type;
    private String href;
    private String rel;
    private String src;
    private String clazz;
    private String id;
    private String target;
    private String title;
    private String charset;
    private String lang;

    private String text;

    private final List<Elements> childs = new ArrayList<>();
    public Elements(){}
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(type).append(",").append(href).append(",");
        for(int i = 0; i < childs.size(); i++){
            sb.append(childs.get(i).toString());
        }
        sb.append(">");
        return sb.toString();
    }
    public Elements setHref(String href){
        this.href = href;
        return this;
    }
    public String getHref(){
        return this.href;
    }
    public Elements setType(String type){
        this.type = type;
        return this;
    }
    public String getType(){
        return this.type;
    }
    public Elements putChild(Elements ele){
        childs.add(ele);
        return this;
    }
    public Elements putChilds(List<Elements> ele){
        childs.addAll(ele);
        return this;
    }
    public List<Elements> getChilds() {
        return childs;
    }
    public String getRel() {
        return rel;
    }

    public Elements setRel(String rel) {
        this.rel = rel;
        return this;
    }

    public String getSrc() {
        return src;
    }

    public Elements setSrc(String src) {
        this.src = src;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public Elements setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getId() {
        return id;
    }

    public Elements setId(String id) {
        this.id = id;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public Elements setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Elements setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public Elements setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public Elements setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getText() {
        return text;
    }

    public Elements setText(String text) {
        this.text = text;
        return this;
    }
}
