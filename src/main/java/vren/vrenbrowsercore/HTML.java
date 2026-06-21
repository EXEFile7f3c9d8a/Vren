package vren.vrenbrowsercore;

import vren.vrenbrowsercore.storage.Elements;

import java.util.*;

public class HTML {
    private static final byte IS_TEXT = 0;
    private static final byte IS_CODE = 1;
    private static final byte IS_VALUE = 2;
    private static final byte IS_SPECIAL = 3;
    private final String html;
    private final Deque<Elements> stack = new ArrayDeque<>();
    private final Elements tree = new Elements();

    public HTML(String html){
        this.html = html;
    }
    public void HTMLSolve() {
        StringBuilder sb = new StringBuilder();
        byte Status = -1;
        char temp;
        Elements tmpEle = new Elements();
        for(int i = 0; i < html.length(); i++){
            temp = html.charAt(i);
            if(temp == '<'){
                if(html.charAt(i +1) != '!'){
                    Status = IS_CODE;

                }
            }
        }
    }
}
