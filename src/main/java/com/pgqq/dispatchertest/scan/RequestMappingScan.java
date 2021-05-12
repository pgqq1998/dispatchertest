package com.pgqq.dispatchertest.scan;

import com.pgqq.dispatchertest.annotations.RequestMapping;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestMappingScan {
    public static Map<String,Method> scan(String packagePath) {
        Map<String,Method> map = new HashMap<>();
        Reflections reflections = new Reflections(packagePath);
        //    扫描包packagePath，获得带有RequestMapping注解的类的集合
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(RequestMapping.class);
        //    遍历集合，扫描类中的方法，判断是否含有RequestMapping注解
        //    若存在则添加(url,method)到map中
        for(Class<?> c : set) {
            for(Method m : c.getMethods()) {
                if(m.isAnnotationPresent(RequestMapping.class)) {
                    String path1 = c.getAnnotation(RequestMapping.class).value();
                    String path2 = m.getAnnotation(RequestMapping.class).value();
                    map.put(path1+path2,m);
                }
            }
        }
        return map;
    }
}
