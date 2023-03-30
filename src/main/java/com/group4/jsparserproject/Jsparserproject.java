/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.group4.jsparserproject;

/**
 *
 * @author PC USER
 */

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;

/**
 *
 * @author PC USER
 */
public class Jsparserproject {
    private static final String FILE_PATH = "src/main/java/com/group4/jsparserproject/OrderTracker.java";
    
//    C:\Users\PC USER\Documents\NetBeansProjects\jsparserproject\src\main\java\com\group4\jsparserproject\Calculator.java
   
   public static void main(String[] args) throws Exception{
       CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
       VoidVisitor<Void> methodNameVistor = new MethodNamePrinter();
       methodNameVistor.visit(cu, null);
   }  
   
   private static class MethodNamePrinter extends VoidVisitorAdapter<Void>{
       @Override public void visit(MethodDeclaration md, Void arg){
           super.visit(md, arg);
       
            System.out.println("Method Name Printed: " + md.getName());
       }
   }
}

