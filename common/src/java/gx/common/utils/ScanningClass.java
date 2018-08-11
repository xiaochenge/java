package gx.common.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.context.ApplicationContext;

import gx.common.annotation.InitProxyObject;
import gx.common.entity.SerializeObject;

public class ScanningClass{
	
	public static final Map<String,SerializeObject> concurrentHashMap=new ConcurrentHashMap<>(50);
	/**
	 * 启动容器调用此方法将相关属性生成代理对象
	 * @param path
	 * @param app
	 */
	public void InitProxyObject(String path,ApplicationContext app) {
		List<Class<?>> classes = getClasses(path);
		for (Class<?> class1 : classes) {
			Object obj=app.getBean(class1);
			if(obj == null)continue;
			//获取所有属性
			Field[] declaredFields = class1.getDeclaredFields();
			initProxyObject(obj,declaredFields);
		}
		
	}
	
	/**
	 * 生成动态代理对象
	 * @param obj
	 * @param declaredFields
	 */
	private void initProxyObject(Object obj,Field[] declaredFields) {
		for (Field field : declaredFields) {
			//获取该属性并且判断是否包含注解
			InitProxyObject annotation = field.getAnnotation(InitProxyObject.class);
			//该属性上包含指定注解时，需要从容器中获取实力并且赋值
			if(annotation == null)continue;
			Object bean=getInstance(field.getType());
			  try {
				field.setAccessible(true);
				field.set(obj, bean);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 给接口生成动态代理对象
	 * @param cls
	 * @return
	 */
	private Object getInstance(Class<?> cls){        
		DynamicProxy invocationHandler = new DynamicProxy();        
	        Object newProxyInstance = Proxy.newProxyInstance(  
	                cls.getClassLoader(),  
	                new Class[] { cls }, 
	                invocationHandler); 
	        return (Object)newProxyInstance;
	    }
	
	
	/** 
     * 从包package中获取所有的Class 
     * @param pack 
     * @return 
     */  
    private List<Class<?>> getClasses(String packageName){  
          
        //第一个class类的集合  
        List<Class<?>> classes = new ArrayList<Class<?>>();  
        //是否循环迭代  
        boolean recursive = true;  
        //获取包的名字 并进行替换  
        String packageDirName = packageName.replace('.', '/');  
        //定义一个枚举的集合 并进行循环来处理这个目录下的things  
        Enumeration<URL> dirs;  
        try {  
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);  
            //循环迭代下去  
            while (dirs.hasMoreElements()){  
                //获取下一个元素  
                URL url = dirs.nextElement();  
                //得到协议的名称  
                String protocol = url.getProtocol();  
                //如果是以文件的形式保存在服务器上  
                if ("file".equals(protocol)) {  
                    //获取包的物理路径  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");  
                    //以文件的方式扫描整个包下的文件 并添加到集合中  
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);  
                } else if ("jar".equals(protocol)){  
                    //如果是jar包文件   
                    //定义一个JarFile  
                    JarFile jar;  
                    try {  
                        //获取jar  
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();  
                        //从此jar包 得到一个枚举类  
                        Enumeration<JarEntry> entries = jar.entries();  
                        //同样的进行循环迭代  
                        while (entries.hasMoreElements()) {  
                            //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件  
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            //如果是以/开头的  
                            if (name.charAt(0) == '/') {  
                                //获取后面的字符串  
                                name = name.substring(1);  
                            }  
                            //如果前半部分和定义的包名相同  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                //如果以"/"结尾 是一个包  
                                if (idx != -1) {  
                                    //获取包名 把"/"替换成"."  
                                    packageName = name.substring(0, idx).replace('/', '.');  
                                }  
                                //如果可以迭代下去 并且是一个包  
                                if ((idx != -1) || recursive){  
                                    //如果是一个.class文件 而且不是目录  
                                    if (name.endsWith(".class") && !entry.isDirectory()) {  
                                        //去掉后面的".class" 获取真正的类名  
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);  
                                        try {  
                                            //添加到classes  
                                            classes.add(Class.forName(packageName + '.' + className));  
                                        } catch (ClassNotFoundException e) {  
                                            e.printStackTrace();  
                                        }  
                                      }  
                                }  
                            }  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }   
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
         
        return classes;  
    }  
    
    private void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){  
        //获取此包的目录 建立一个File  
        File dir = new File(packagePath);  
        //如果不存在或者 也不是目录就直接返回  
        if (!dir.exists() || !dir.isDirectory()) {  
            return;  
        }  
        //如果存在 就获取包下的所有文件 包括目录  
        File[] dirfiles = dir.listFiles(new FileFilter() {  
        //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)  
              public boolean accept(File file) {  
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));  
              }  
            });  
        //循环所有文件  
        for (File file : dirfiles) {  
            //如果是目录 则继续扫描  
            if (file.isDirectory()) {  
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),  
                                      file.getAbsolutePath(),  
                                      recursive,  
                                      classes);  
            }  
            else {  
                //如果是java类文件 去掉后面的.class 只留下类名  
                String className = file.getName().substring(0, file.getName().length() - 6);  
                try {  
                    //添加到集合中去  
                    classes.add(Class.forName(packageName + '.' + className));  
                } catch (ClassNotFoundException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
}
