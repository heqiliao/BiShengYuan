package com.example.newbishengyuan.model;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2015/6/2.
 */
public class JsonDecoder {

    private static final String MESSAGE = "message";
    public static String MODEL_PACKAGE = "com.example.newbishengyuan.model.";
    public static String RETURN_STATE = "code";
    public static String RETURN_RESULT = "result";

    public static Object decodeList(String name, JSONArray jsonArray) {
        //System.out.println("arry"+jsonArray);

        ArrayList list = new ArrayList();
        int length = jsonArray.length();

        try {

            Class clazz = Class.forName(MODEL_PACKAGE + name);


            for (int i = 0; i < length; i++) {
                Object object = clazz.newInstance();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Iterator<String> keys = jsonObj.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Field field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    if (field.getType() == boolean.class) {
                        boolean bValue = jsonObj.getBoolean(key);
                        field.set(object, bValue);
                        continue;
                    }
                    String value = jsonObj.getString(key);
                    field.set(object, value);
                }
                list.add(object);

            }
        } catch (JSONException e) {
            System.out.println("decodeList exception:" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("decodeList exception:" + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("decodeList exception:" + e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("decodeList exception:" + e);
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            System.out.println("decodeList exception:" + e);
            e.printStackTrace();
        }

        return list;
    }

    public static Object decodeItem(String name, JSONObject jsonObj) {

        Object object = null;
        try {

            Class clazz = Class.forName(MODEL_PACKAGE + name);


            object = clazz.newInstance();

            Iterator<String> keys = jsonObj.keys();

            System.out.println(keys.hasNext());
            while (keys.hasNext()) {
                String key = keys.next();

                    Field field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    if (field.getType() == boolean.class) {
                        boolean bValue = jsonObj.getBoolean(key);
                        field.set(object, bValue);
                        continue;
                    }
                    String value = jsonObj.getString(key);
                    field.set(object, value);
                    System.out.println("set" + key + "=" + value);

            }


        } catch (JSONException e) {
            System.out.println("decodeItem exception:" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("decodeItem exception:" + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("decodeItem exception:" + e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("decodeItem exception:" + e);
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            System.out.println("decodeItem exception"+e);
            e.printStackTrace();
        }
        return object;
    }

    public static Object decode(String jsonStr)  {

        try {
            JSONObject json = new JSONObject(jsonStr);
            int code = json.getInt(RETURN_STATE);
//            }            String message = json.getString(MESSAGE);
            JSONObject result = json.getJSONObject(RETURN_RESULT);
            Iterator<String> keys = result.keys();
           if (keys.hasNext()) {
                String obj = keys.next();
                if (obj.contains(".List")) {
                    String name = obj.replace(".List", "");
                    return decodeList(name, result.getJSONArray(obj));
                } else {


                    String name = obj;
                    return decodeItem(name, result.getJSONObject(obj));
                }
            }
            else{
                return null;
            }



        } catch (JSONException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return null;

    }




}
