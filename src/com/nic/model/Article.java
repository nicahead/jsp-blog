package com.nic.model;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Article {
    private int id;
    private String title;
    private String subtitle;
    private String md_content;
    private String html_content;
    private String createdate;
    private int sub_id=0;
    private int main_id;
    private String mname;
    private String sname;
    private int top;

    public Article() {
    }

    public Article(int id,String title, String subtitle, String md_content, String html_content, String createdate, int sub_id, int main_id, String mname,String sname, int top) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.md_content = md_content;
        this.html_content = html_content;
        this.createdate = createdate;
        this.sub_id = sub_id;
        this.main_id = main_id;
        this.mname = mname;
        this.sname = sname;
        this.top = top;
    }

    public Article(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.title = (String) map.get("title");
        this.subtitle = (String) map.get("subtitle");
        this.md_content = (String) map.get("md_content");
        this.html_content = (String) map.get("html_content");
        this.createdate = (String) map.get("createdate");
//        if (map.get("sub_id")!=null)
//            this.sub_id = (int) map.get("sub_id");
        this.main_id = (int) map.get("main_id");
        this.mname = (String) map.get("mname");
        this.sname = (String) map.get("sname");
        this.top = (int) map.get("top");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMd_content() {
        return md_content;
    }

    public void setMd_content(String md_content) {
        this.md_content = md_content;
    }

    public String getHtml_content() {
        return html_content;
    }

    public void setHtml_content(String html_content) {
        this.html_content = html_content;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public int getMain_id() {
        return main_id;
    }

    public void setMain_id(int main_id) {
        this.main_id = main_id;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", md_content='" + md_content + '\'' +
                ", html_content='" + html_content + '\'' +
                ", createdate=" + createdate +
                ", sub_id=" + sub_id +
                ", main_id=" + main_id +
                ", mname='" + mname + '\'' +
                ", sname='" + sname + '\'' +
                ", top=" + top +
                '}';
    }

    public List toList() {
        List list = new ArrayList();
        list.add(title);
        list.add(subtitle);
        list.add(md_content);
        list.add(html_content);
        list.add(createdate);
        if(sub_id!=0)
            list.add(sub_id);
        list.add(main_id);
        list.add(top);
        return list;
    }
}
