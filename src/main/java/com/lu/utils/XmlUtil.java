package com.lu.utils;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XmlUtil {
    private static String XML_TAG = "<?xml version='1.0' encoding='UTF-8'?>";

    public static String beanToXml(Object obj){
        XStream xstream = new XStream();
        xstream.processAnnotations(obj.getClass()); //通过注解方式的，一定要有这句话
        return xstream.toXML(obj);
//        return XML_TAG + xstream.toXML(obj);
    }

    public static <T> T  xmlToBean(String xmlStr, Class<T> cls){
        XStream xstream = new XStream() {
            //忽略xml中的未知节点
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        xstream.processAnnotations(cls);
        @SuppressWarnings("unchecked")
        T obj = (T)xstream.fromXML(xmlStr);
        return obj;
    }

}
