package com.yexuman.tool;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.client.impl.CopyOnWriteHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import sun.text.CollatorUtilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yexuman
 * @version 1.0
 * @date 2021/2/4 10:58
 */
public class CityUtils {
    ReentrantLock reentrantLock = new ReentrantLock();
    public static final Map<String, List<JSONObject>> provincePinYin2Info = new ConcurrentHashMap<>(32);
    public static final Map<Integer, Map<String, JSONObject>> provinceId2ChildMap = new ConcurrentHashMap<>(32);

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        String path = JsonTest.class.getClassLoader().getResource("city.json").getPath();
        String s = readJsonFile(path);
        JSONObject jobJ = JSON.parseObject(s);
        JSONArray province = jobJ.getJSONArray("province");
        JSONArray cityArr = jobJ.getJSONArray("city");

        for (int i = 0; i < province.size(); i++) {
            JSONObject jb = (JSONObject) province.get(i);
            String pinYin = jb.getString("pinYin");
            if (StringUtils.isEmpty(pinYin)) {
                continue;
            }
            Integer id = jb.getInteger("id");
            List<JSONObject> provinceList = provincePinYin2Info.getOrDefault(pinYin, new ArrayList<>());
            provinceList.add(jb);
            provincePinYin2Info.put(pinYin, provinceList);
            provinceId2ChildMap.put(id, new ConcurrentHashMap<>(32));

        }
        for (Object object : cityArr) {
            JSONObject jb = (JSONObject) object;
            String pinYin = jb.getString("pinYin");
            if (StringUtils.isEmpty(pinYin)) {
                continue;
            }
            String name = jb.getString("name");
            Integer parentId = jb.getInteger("parentId");
            Integer id = jb.getInteger("id");
            if (!provinceId2ChildMap.containsKey(parentId)) {
                continue;
            }
            Map<String, JSONObject> cityId2Info = provinceId2ChildMap.get(parentId);
            cityId2Info.put(pinYin, jb);
        }
        System.out.println(JSON.toJSONString(provincePinYin2Info));
        System.out.println("!!!!!!!!!!!!!!!!!!!!");
        System.out.println(JSON.toJSONString(provinceId2ChildMap));
    }
}
