package com.pgqq.dispatchertest.controller;

import com.pgqq.dispatchertest.annotations.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequestMapping("/controller2")
public class MyController2 {
    @RequestMapping("/test1")
    public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("mycontroller2 - test1");
    }

    @RequestMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("mycontroller2 - test2");
    }

    @RequestMapping("/test3")
    public void test3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("mycontroller2 - test3");
    }
}
