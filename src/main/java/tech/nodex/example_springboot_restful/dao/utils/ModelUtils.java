package tech.nodex.example_springboot_restful.dao.utils;

import com.jfinal.plugin.activerecord.Model;
import tech.nodex.tutils2.beans.BeanBuilder;

import java.util.*;

/**
 * 该工具类用来弥补Active Record和普通Java Bean之间转化的Gap。
 *
 * 另外，在tutils2工具包中，提供了一个灵活易用的BeanBuilder类，用于进行类型间的数据转化和拷贝。
 * 本类中，有一部分功能是通过BeanBuilder实现的。
 * Created by cz on 2017-2-16.
 */
public final class ModelUtils extends Model{
    private ModelUtils(){}

    /**
     * 将Active Record Model转化为Map
     * @param model
     * @return
     */
    public static Map<String,Object> to(Model<?> model){
        Map<String,Object> copyOfAttributes = new HashMap<String,Object>();
        Set<Map.Entry<String, Object>> entrySet = model._getAttrsEntrySet();
        for(Map.Entry<String,Object> entry:entrySet){
            copyOfAttributes.put(entry.getKey(),entry.getValue());
        }
        return copyOfAttributes;
    }

    /**
     * 将Active Record 列表转化为Map列表
     * @param modelList
     * @return
     */
    public static List<Map<String,Object>> to(List modelList){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        if(modelList!=null){
            for(Object m:modelList){
                if(m instanceof Model){
                    list.add(to((Model)m));
                }
            }
        }
        return list;
    }

    /**
     * 将Active Record 转化为指定类型
     * @param model
     * @param targetType
     * @return
     */
    public static <T>T to(Model<?> model,Class<T> targetType){
        Map<String,Object> attributes = to(model);
        BeanBuilder<T> beanBuilder = BeanBuilder.instOf(targetType);
        beanBuilder.overwrite(attributes);
        return beanBuilder.build();
    }

    /**
     * 将Active Record 列表转化为指定类型
     * @param modelList
     * @param targetElementType - 目标列表元素类型
     * @return
     */
    public static <T>List<T> to(List modelList,Class<T> targetElementType){
        List<T> list = new ArrayList<T>();
        if(modelList!=null){
            for(Object m:modelList){
                if(m instanceof Model){
                    list.add(to((Model)m,targetElementType));
                }
            }
        }
        return list;
    }

    /**
     * 将普通对象转化为Model
     * @param sourceObj
     * @param targetModelType
     * @param <T>
     * @return
     */
    public static <T extends Model> T from(Object sourceObj,Class<T> targetModelType){
        BeanBuilder<Map> beanBuilder = BeanBuilder.instOf(Map.class);
        beanBuilder.overwrite(sourceObj);
        Map map = beanBuilder.build();
        return from(map,targetModelType);
    }

    /**
     * 将Map转化为Model
     * @param map
     * @param targetModelType
     * @param <T>
     * @return
     */
    public static <T extends Model> T from(Map map,Class<T> targetModelType){
        try {
            T targetModel = (T)targetModelType.newInstance();
            for(Object key:map.keySet()){
                targetModel.set((String)key,map.get(key));
            }
            return targetModel;
        } catch (Exception e) {
            throw new IllegalArgumentException("sourceObj can not convert to : " + targetModelType.getName(),e);
        }
    }
}
