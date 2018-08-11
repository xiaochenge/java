/*package gx.web.mvc.verticles;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import gx.common.annotation.HttpRoute;
import gx.common.annotation.RouteController;

public class InitHttpRoute {
	
	public static Map<String,Class<?>> InitHttpRoute(String scanningPath) throws Exception {
        // 包下面的类
        Set<Class<?>> classes = getClasses(scanningPath);
        Map<String,Class<?>> map=null;
        if (classes == null || classes.size()==0) {
            System.out.printf("null");
        } else {
            //获取符合规范controller对象
        	map= getByInterface(classes);
      }
        return map;

    }
    *//**
     * 从包package中获取所有的Class
     *
     * @param pack
     * @return
     *//*
    public static Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace(".", File.separator);
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    System.err.println("file类型的扫描");
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    *//**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     *//*
    public static void findAndAddClassesInPackageByFile(String packageName,
           String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }
    
    *//**
     * 返回所有符合条件对象
     * @param classesAll
     * @return
     *//*
    private static Map<String,Class<?>> getByInterface( Set<Class<?>> classesAll) {
        Class<?> cls=null;
        Map<String,Class<?>> map=new HashMap<>();
        Set<String> set=new HashSet<>();
        //获取指定接口的实现类
            try {
                *//**
                 * 循环判断路径下的所有类是否继承了指定类
                 * 并且排除父类自己
                 *//*
                Iterator<Class<?>> iterator = classesAll.iterator();
                while (iterator.hasNext()) {
                	cls = iterator.next();
                    //此类包含RouteController注解
                    if(cls.isAnnotationPresent(RouteController.class)) {
                    	//获取所有注解
                    	HttpRoute annotation = cls.getAnnotation(HttpRoute.class);
                    	//判断类上的注解value是否重复
                    	if(set.add(annotation.value())){
                    		String className = cls.toString().substring(6);
                    		map.put(className, Class.forName(className));
                    	}else {
                    		//一旦重复抛出异常
                    		throw new RuntimeException();
                    	}
                    }
                }
            } catch(RuntimeException e) {
            	System.out.println(cls.getName()+"此对象HttpRoute注解的value重复");
            }catch (Exception e) {
                System.out.println(cls.getName()+"此对象添加RouteControlle注解必须同时使用HttpRoute注解");
            }
        return map;
    }
}
*/