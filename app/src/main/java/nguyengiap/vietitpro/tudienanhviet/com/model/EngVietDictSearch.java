package nguyengiap.vietitpro.tudienanhviet.com.model;

import android.text.Spannable;
import android.text.style.ClickableSpan;

/**
 * Created by hanhphuckhicoem on 02/09/2015.
 */
public class EngVietDictSearch {
    private String content;

    public String getConentSpannable() {
        return conentSpannable;
    }

    public void setConentSpannable(String conentSpannable) {
        this.conentSpannable = conentSpannable;
    }

    private String conentSpannable;

    public EngVietDictSearch() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubcontent() {
        return subcontent;
    }

    public void setSubcontent(String subcontent) {
        this.subcontent = subcontent;
    }

    public EngVietDictSearch(String content, String subcontent, String conentSpannable) {
        this.conentSpannable = conentSpannable;
        this.content = content;
        this.subcontent = subcontent;
    }

    public EngVietDictSearch(String content, String subcontent) {
        this.content = content;
        this.subcontent = subcontent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    private String subcontent;

    private Spannable mySpannable;
    int id;

    public EngVietDictSearch(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    private int start;
    private int end;
    private ClickableSpan myClickableSpan;

    public EngVietDictSearch(String subcontent, int start, int end) {
        this.subcontent = subcontent;
        this.start = start;
        this.end = end;
    }

    public ClickableSpan getMyClickableSpan() {
        return myClickableSpan;
    }

    public void setMyClickableSpan(ClickableSpan myClickableSpan) {
        this.myClickableSpan = myClickableSpan;
    }

    public Spannable getMySpannable() {

        return mySpannable;
    }

    public void setMySpannable(Spannable mySpannable) {
        this.mySpannable = mySpannable;
    }
}
