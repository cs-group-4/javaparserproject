/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.group4.jsparserproject;

/**
 *
 * @author PC USER
 */

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.Token;
import com.github.javaparser.ast.token.Token;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.token.TokenType;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.DotPrinter;
import com.github.javaparser.printer.XmlPrinter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
//import com.github.javaparser.printer;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;
/**
 *
 * @author PC USER
 */
public class Jsparserproject {
    static File file = new File("src/main/java/com/group4/jsparserproject/OrderTracker.java");

//    C:\Users\PC USER\Documents\NetBeansProjects\jsparserproject\src\main\java\com\group4\jsparserproject\Calculator.java
   
   public static void main(String[] args) throws Exception{
       CompilationUnit cu = StaticJavaParser.parse(file);
       String astXml = new XmlPrinter(true).output(cu);
       System.out.println(astXml); 
//               System.out
//       VoidVisitor<Void> methodNameVistor = new MethodNamePrinter();
//       methodNameVistor.visit(cu, null);
//         System.out.println(astXml);
         
         // Now comes the inspection code:
//        DotPrinter printer = new DotPrinter(true);
//        try (FileWriter fileWriter = new FileWriter("ast.dot");
//            PrintWriter printWriter = new PrintWriter(fileWriter)) {
//            printWriter.print(printer.output(cu));
//        }

        // print out number of identifiers
        countNumberOfIdentifiers(cu);
        // print out number of literals
        countNumberOfLiterals(cu);
        countNumberOfOperators(cu);
        countNumberOfKeywords(cu);
//        countNumberOfSeparators(cu);
        countNumberOfComment(cu);
        
        
        
        
        
   }  
   
   public static void countNumberOfIdentifiers(CompilationUnit cu){
       int count = 0;
       for (FieldDeclaration field : cu.findAll(FieldDeclaration.class)) {
           for (VariableDeclarator variable : field.getVariables()) {
               count++;
           }
       }
       for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {
           count++;
       }
       System.out.println("Number of identifiers in " + file.getName() + ": " + count);
   }
    public static void countNumberOfLiterals(CompilationUnit cu){
       int count = 0;
            for (IntegerLiteralExpr integerLiteral : cu.findAll(IntegerLiteralExpr.class)) {
                count++;
            }
            for (LongLiteralExpr longLiteral : cu.findAll(LongLiteralExpr.class)) {
                count++;
            }
            for (DoubleLiteralExpr doubleLiteral : cu.findAll(DoubleLiteralExpr.class)) {
                count++;
            }
            for (CharLiteralExpr charLiteral : cu.findAll(CharLiteralExpr.class)) {
                count++;
            }
            for (StringLiteralExpr stringLiteral : cu.findAll(StringLiteralExpr.class)) {
                count++;
            }
            System.out.println("Number of literals in " + file.getName() + ": " + count);
 }
   public static void countNumberOfOperators(CompilationUnit cu){
       int count = 0;
       for (BinaryExpr binaryExpr : cu.findAll(BinaryExpr.class)) {
                count++;
            }
            for (UnaryExpr unaryExpr : cu.findAll(UnaryExpr.class)) {
                count++;
            }
            for (AssignExpr assignExpr : cu.findAll(AssignExpr.class)) {
                count++;
            }
            for (ConditionalExpr conditionalExpr : cu.findAll(ConditionalExpr.class)) {
                count++;
            }
            System.out.println("Number of operators in " + file.getName() + ": " + count);
 }
    public static void countNumberOfKeywords(CompilationUnit cu){
       int count = 0;
       Set<String> keywords = new HashSet<>();
       for (FieldDeclaration fieldDeclaration : cu.findAll(FieldDeclaration.class)) {
                fieldDeclaration.getVariables().forEach(variable -> keywords.add(variable.getNameAsString()));
            }
            for (MethodDeclaration methodDeclaration : cu.findAll(MethodDeclaration.class)) {
                keywords.add(methodDeclaration.getNameAsString());
                methodDeclaration.getParameters().forEach(parameter -> keywords.add(parameter.getNameAsString()));
                methodDeclaration.findAll(ReturnStmt.class).forEach(returnStmt -> {
                    if (returnStmt.getExpression().isPresent()) {
                        keywords.addAll(returnStmt.getExpression().get().findAll(NameExpr.class).stream()
                                .map(NameExpr::getNameAsString)
                                .toList());
                    }
                });
            }
            cu.findAll(NameExpr.class).stream()
                    .map(NameExpr::getNameAsString)
                    .forEach(keywords::add);
            System.out.println("Number of keywords in " + file.getName() + ": " + keywords.size());
 }
    public static void countNumberOfComment(CompilationUnit cu){
       List<Comment> comments = cu.findAll(Comment.class);
            System.out.println("Number of comments in " + file.getName() + ": " + comments.size());
 }
   private static class MethodNamePrinter extends VoidVisitorAdapter<Void>{
       @Override public void visit(MethodDeclaration md, Void arg){
           super.visit(md, arg);
       
            System.out.println("Method Name Printed: " + md.getName());
       }
   }
   
 
}

