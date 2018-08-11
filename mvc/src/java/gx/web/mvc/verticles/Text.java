/*package gx.web.mvc.verticles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import gx.common.utils.PathStringUtil;

public class Text {
	    private String basePackage;
	    private ClassLoader cl;

	    *//**
	     * Construct an instance and specify the base package it should scan.
	     * @param basePackage The base package to scan.
	     *//*
	    public Text(String basePackage) {
	        this.basePackage = basePackage;
	        this.cl = getClass().getClassLoader();

	    }

	    *//**
	     * Construct an instance with base package and class loader.
	     * @param basePackage The base package to scan.
	     * @param cl Use this class load to locate the package.
	     *//*
	    public Text(String basePackage, ClassLoader cl) {
	        this.basePackage = basePackage;
	        this.cl = cl;
	    }

	    *//**
	     * Get all fully qualified names located in the specified package
	     * and its sub-package.
	     *
	     * @return A list of fully qualified names.
	     * @throws IOException
	     *//*
	    public List<String> getFullyQualifiedClassNameList() throws IOException {

	    	List<String> doScan = doScan(basePackage, new ArrayList<String>());
	    	List<String> returnDoScan=new ArrayList<String>();
	    	String os = System.getProperty("os.name"); 
	    	for (String string : doScan) {
	    		if(os.toLowerCase().startsWith("win")){ 
	    			string=basePackage+"."+string;
				}else {
					string = string.replaceAll("/", ".");
				}
	    		string=string.replaceAll(".class", "");
	    		returnDoScan.add(string);
			}
	    	
	        return returnDoScan;
	    }

	    *//**
	     * Actually perform the scanning procedure.
	     *
	     * @param basePackage
	     * @param arrayList A list to contain the result.
	     * @return A list of fully qualified names.
	     *
	     * @throws IOException
	     *//*
	    private List<String> doScan(String basePackage, ArrayList<String> arrayList) throws IOException {
	        // replace dots with splashes
	        String splashPath = PathStringUtil.dotToSplash(basePackage);
	        // get file path
	        URL url = cl.getResource(splashPath);
	        String filePath = PathStringUtil.getRootPath(url);
	        
	        List<String> names = null;
	        if (isJarFile(filePath)) {
	            names = readFromJarFile(filePath, splashPath);
	        } else {
	            names = readFromDirectory(filePath);
	        }	        
	        for (String name : names) {
	            if (isClassFile(name)) {
	                arrayList.add(name);
	            } else {
	                doScan(basePackage + "." + name, arrayList);
	            }
	        }

	        return arrayList;
	    }

	    *//**
	     * Convert short class name to fully qualified name.
	     * e.g., String -> java.lang.String
	     *//*
	    private String toFullyQualifiedName(String shortName, String basePackage) {
	        StringBuilder sb = new StringBuilder(basePackage);
	        sb.append('.');
	        sb.append(PathStringUtil.trimExtension(shortName));

	        return sb.toString();
	    }

	    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {

	        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
	        JarEntry entry = jarIn.getNextJarEntry();

	        List<String> nameList = new ArrayList<String>();
	        while (null != entry) {
	            String name = entry.getName();
	            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
	                nameList.add(name);
	            }

	            entry = jarIn.getNextJarEntry();
	        }

	        return nameList;
	    }

	    private List<String> readFromDirectory(String path) {
	        File file = new File(path);
	        String[] names = file.list();

	        if (null == names) {
	            return null;
	        }

	        return Arrays.asList(names);
	    }

	    private boolean isClassFile(String name) {
	        return name.endsWith(".class");
	    }

	    private boolean isJarFile(String name) {
	        return name.endsWith(".jar");
	    }
	    
	    public static void main(String[] args) {
			int i=0;
			int sum=0;
			int n=2;
			for (int j = 0; j <= n; j++) {
				sum+=j;
			}
			System.out.println(sum);
		
			
			sum =(1+n)*n/2;
			System.out.println(sum);
		}
}
*/