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
import com.github.javaparser.printer.DotPrinter;
import com.github.javaparser.printer.XmlPrinter;
//import com.github.javaparser.printer;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author PC USER
 */
public class Jsparserproject {
    private static final String FILE_PATH = "src/main/java/com/group4/jsparserproject/OrderTracker.java";
    
//    C:\Users\PC USER\Documents\NetBeansProjects\jsparserproject\src\main\java\com\group4\jsparserproject\Calculator.java
   
   public static void main(String[] args) throws Exception{
       CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
       String astXml = new XmlPrinter(true).output(cu);
//               System.out
//       VoidVisitor<Void> methodNameVistor = new MethodNamePrinter();
//       methodNameVistor.visit(cu, null);
         System.out.println(astXml);
         
         // Now comes the inspection code:
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(cu));
        }
   }  
   
//   private static class MethodNamePrinter extends VoidVisitorAdapter<Void>{
//       @Override public void visit(MethodDeclaration md, Void arg){
//           super.visit(md, arg);
//       
//            System.out.println("Method Name Printed: " + md.getName());
//       }
//   }
}

