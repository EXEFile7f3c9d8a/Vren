package vren.vrenbrowsercore;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.net.http.HttpRequest.*;
import java.util.*;
import vren.vrenbrowsercore.HTTP.Sink.*;

public class HTTP{
    public Sink sink = new Sink();
    public static final class Sink{
        public static final class Header{
            public String type;
            public String value;
            public String getType(){
                return this.type;
            }
            public String getValue(){
                return this.value;
            }
            public Header setType(String type){
                this.type = type;
                return this;
            }
            public Header setValue(String value){
                this.value = value;
                return this;
            }
        }
        public static final class In{
            public List<Header> headers = new ArrayList<>();
            public String url;
            public short port;
        }
        public static final class Out{
            public List<Header> headers = new ArrayList<>();
            public String msg;
        }
        public In input = new In();
        public Out output = new Out();
        public Sink(){}
        public void replace(String url, List<Header> headers, short port){
            this.input.url = url;
            this.input.headers = headers;
            this.input.port = port;
        }
    }
    public void get(){
        try{
            HttpClient client = HttpClient.newHttpClient();
            Builder temp = HttpRequest.newBuilder().uri(URI.create(getIn().url));
            for(int i = 0; i < getIn().headers.size(); i++){
                temp.header(getIn().headers.get(i).type, getIn().headers.get(i).value);
            }
            HttpRequest request = temp.GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            getOut().headers = headersSeparate(response.headers().toString());
            getOut().msg = response.toString();
        }catch(IOException | InterruptedException e){
            throw new RuntimeException("Http response failed: " + e);
        }
    }
    public Sink.In getIn(){
        return this.sink.input;
    }
    public Sink.Out getOut(){
        return this.sink.output;
    }
    public Sink getSink(){
        return this.sink;
    }
    public List<Header> headersSeparate(String headers){
        List<Header> temp = new ArrayList<>();
        String[] split = headers.split("\n");
        for(int i = 0; i < split.length; i++){
            String[] tempSp = split[i].split(": ");
            temp.add(new Header().setType(tempSp[0]).setValue(tempSp[1]));
        }
        return temp;
    }
}
