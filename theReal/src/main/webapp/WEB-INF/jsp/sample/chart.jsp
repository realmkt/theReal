<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jfree.chart.*" %>
<%@ page import="first.common.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.jfree.data.category.*"%>
<%@ page import="org.jfree.ui.RefineryUtilities"%>
<%@ page import="org.jfree.chart.plot.PlotOrientation"%>

<%@ page import="java.awt.Color"%>
<%@ page import="java.awt.Paint"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="org.jfree.chart.ChartFactory"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="org.jfree.chart.plot.PiePlot"%>
<%@ page import="org.jfree.chart.plot.CategoryPlot"%>
<%@ page import="org.jfree.chart.plot.PlotOrientation"%>
<%@ page import="org.jfree.chart.renderer.category.CategoryItemRenderer"%>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset"%>
 
<%

DefaultPieDataset dataset=new DefaultPieDataset();

dataset.setValue("first",100);
dataset.setValue("second",150);
dataset.setValue("third",100);

System.setProperty("java.awt.headless", "true");

JFreeChart chart = ChartFactory.createPieChart("title", dataset, false,true, false);

 chart.setBackgroundPaint(new Color(246, 246, 246));
 //backgroud color (RGB)
 chart.getTitle().setPaint(new Color(102, 102, 102));  
 //title color (RGB)

 PiePlot plot = (PiePlot)chart.getPlot();
 
 
 Paint paint0 = new Color(236,138,168);
 //RGB color
 Paint paint1 = new Color(231,204,100);
 Paint paint2 = new Color(140,211,115);
 Paint paint3 = new Color(142,202,222);
 Paint paint4 = new Color(174,110,196);
 //range color
 
 plot.setSectionPaint(0, paint0);
 plot.setSectionPaint(1, paint1);
 plot.setSectionPaint(2, paint2);
 plot.setSectionPaint(3, paint3);
 plot.setSectionPaint(4, paint4);

 File chartFile =  new File("C:\\testchart\\piechart.jpg");
 
 try{
  
  FileOutputStream fos=new FileOutputStream(chartFile);
  
  ChartUtilities.writeChartAsPNG(fos, chart, 200, 200);
  //FileOutputStream, JFreeChart, width, height
  fos.flush();
  
  fos.close();
  
 }catch(Exception e){
  e.printStackTrace();
 }
 
 
//**********************************************
 
 
// 2. bar chart
 
 DefaultCategoryDataset barDataset=new DefaultCategoryDataset ();
 
 barDataset.setValue(100,"first1","first2");
 //data, 주석 , data 표기
 barDataset.setValue(150,"second1","second2");
 barDataset.setValue(100,"third1","third3");
 
 
 System.setProperty("java.awt.headless", "true");
 
 
 
 chart =  ChartFactory.createBarChart("bar chart",null,null,barDataset,PlotOrientation.VERTICAL,true,true,false);

 chart.setBackgroundPaint(new Color(246, 246, 246));
 chart.getTitle().setPaint(new Color(102, 102, 102));
  CategoryPlot cplot = (CategoryPlot)chart.getPlot();
  Paint bpaint0 = new Color(236,138,168);
  Paint bpaint1 = new Color(231,204,100);
  Paint bpaint2 = new Color(140,211,115);
  Paint bpaint3 = new Color(142,202,222);
  Paint bpaint4 = new Color(174,110,196);
  
  CategoryItemRenderer renderer = cplot.getRenderer();
  renderer.setSeriesPaint(0, bpaint0);
  renderer.setSeriesPaint(1, bpaint1);
  renderer.setSeriesPaint(2, bpaint2);
  renderer.setSeriesPaint(3, bpaint3);
  renderer.setSeriesPaint(4, bpaint4);
  
 
  File chartFile2 =  new File("C:\\testchart\\barchart.jpg");
  	
  try{
   
   FileOutputStream fos=new FileOutputStream(chartFile2);
   
   ChartUtilities.writeChartAsPNG(fos, chart, 200, 200);
   fos.flush();
   
   fos.close();
   
  }catch(Exception e){
   e.printStackTrace();
  }

/* 
    ServletOutputStream sos = response.getOutputStream();
    BarChartBean bcb = new BarChartBean();
    JFreeChart chart = bcb.getBarChart();
    ChartUtilities.writeChartAsPNG(sos, chart, 700, 250);
 */ 
/*
    심각: Servlet.service() for servlet jsp threw exception
    java.lang.IllegalStateException: getOutputStream() has already been called for this response
    # Exception
    jsp에서 outputStream을 사용할때 servlet에서 생성된 writer 객체와 중복이 되어서 발생하는 에러.
    이때 servlet에서 생성된 writer 객체를 clear 시키고 jsp에서 다시 생성해서 사용하면 되는것.
     
*/
/*     out.clear();
    out = pageContext.pushBody();    */
 
%>