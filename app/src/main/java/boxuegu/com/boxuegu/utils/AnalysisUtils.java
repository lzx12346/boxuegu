package boxuegu.com.boxuegu.utils;

import android.util.Xml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import boxuegu.com.boxuegu.bean.ExercisesDetailBean;
import boxuegu.com.boxuegu.bean.VideoListBean;

import org.xmlpull.v1.XmlPullParser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import java.io.InputStream;
import java.util.ArrayList;

public class AnalysisUtils {
    static public List<VideoListBean> pasarJson(InputStream inputStream) throws Exception {
        List<VideoListBean> beans;
        BufferedReader reader;
        StringBuffer sb = null;
        String line = "";
        sb = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        line = reader.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line=reader.readLine();
        }
        beans= JSONObject.parseArray(sb.toString(),VideoListBean.class);
        return beans;
    }
    static public List<ExercisesDetailBean> getExercisesInfos(InputStream inputStream) throws Exception {
        //该方法是解析inputStream所对应的文件，返回解析的选择题集合
        XmlPullParser parser = Xml.newPullParser();
        List<ExercisesDetailBean> beans = null;
        ExercisesDetailBean bean = null;
        parser.setInput(inputStream, "utf-8");
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG: {
                    if ("infos".equals(parser.getName())) {
                        beans = new ArrayList<ExercisesDetailBean>();
                    } else if ("exercises".equals(parser.getName())) {//表示读取到的是exercises
                        bean = new ExercisesDetailBean();
                        bean.id = Integer.parseInt(parser.getAttributeValue(0));
                    } else if ("subject".equals(parser.getName())) {
                        bean.subject = parser.nextText();//两个作用，指针后移，读取开始标签对应的内容
                    } else if ("a".equals(parser.getName())) {
                        bean.a = parser.nextText();
                    } else if ("b".equals(parser.getName())) {
                        bean.b = parser.nextText();
                    } else if ("c".equals(parser.getName())) {
                        bean.c = parser.nextText();
                    } else if ("d".equals(parser.getName())) {
                        bean.d = parser.nextText();
                    } else if ("answer".equals(parser.getName())) {
                        bean.answer = Integer.parseInt(parser.nextText());
                    }
                    break;
                }
                case XmlPullParser.END_TAG: {
                    if ("exercises".equals(parser.getName())) {
                        bean.selected = 0;
                        beans.add(bean);
                        bean = null;
                    }
                    break;
                }
            }
            type = parser.next();
        }
        return beans;
    }
}

