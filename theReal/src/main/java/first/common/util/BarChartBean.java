package first.common.util;

import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.io.FileOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class BarChartBean {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1. pie chart

		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("first", 100);
		dataset.setValue("second", 150);
		dataset.setValue("third", 100);

		System.setProperty("java.awt.headless", "true");

		JFreeChart chart = ChartFactory.createPieChart("title", dataset, false, true, false);

		chart.setBackgroundPaint(new Color(246, 246, 246));
		// backgroud color (RGB)
		chart.getTitle().setPaint(new Color(102, 102, 102));
		// title color (RGB)

		PiePlot plot = (PiePlot) chart.getPlot();

		Paint paint0 = new Color(236, 138, 168);
		// RGB color
		Paint paint1 = new Color(231, 204, 100);
		Paint paint2 = new Color(140, 211, 115);
		Paint paint3 = new Color(142, 202, 222);
		Paint paint4 = new Color(174, 110, 196);
		// range color
		plot.setSectionPaint(0, paint0);
		plot.setSectionPaint(1, paint1);
		plot.setSectionPaint(2, paint2);
		plot.setSectionPaint(3, paint3);
		plot.setSectionPaint(4, paint4);

		File chartFile = new File("C:\\testchart\\piechart.jpg");

		try {

			FileOutputStream fos = new FileOutputStream(chartFile);

			ChartUtilities.writeChartAsPNG(fos, chart, 200, 200);
			// FileOutputStream, JFreeChart, width, height
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// **********************************************

		// 2. bar chart

		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

		barDataset.setValue(100, "first1", "first2");
		// data, 주석 , data 표기
		barDataset.setValue(150, "second1", "second2");
		barDataset.setValue(100, "third1", "third3");

		System.setProperty("java.awt.headless", "true");

		chart = ChartFactory.createBarChart("bar chart", null, null, barDataset, PlotOrientation.VERTICAL, true, true,
				false);

		chart.setBackgroundPaint(new Color(246, 246, 246));
		chart.getTitle().setPaint(new Color(102, 102, 102));
		CategoryPlot cplot = (CategoryPlot) chart.getPlot();
		Paint bpaint0 = new Color(236, 138, 168);
		Paint bpaint1 = new Color(231, 204, 100);
		Paint bpaint2 = new Color(140, 211, 115);
		Paint bpaint3 = new Color(142, 202, 222);
		Paint bpaint4 = new Color(174, 110, 196);

		CategoryItemRenderer renderer = cplot.getRenderer();
		renderer.setSeriesPaint(0, bpaint0);
		renderer.setSeriesPaint(1, bpaint1);
		renderer.setSeriesPaint(2, bpaint2);
		renderer.setSeriesPaint(3, bpaint3);
		renderer.setSeriesPaint(4, bpaint4);

		File chartFile2 = new File("C:\\testchart\\barchart.jpg");

		try {

			FileOutputStream fos = new FileOutputStream(chartFile2);

			ChartUtilities.writeChartAsPNG(fos, chart, 200, 200);
			fos.flush();

			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

/*
 * import org.jfree.chart.*; import org.jfree.data.category.*; import
 * org.jfree.chart.renderer.category.*; import
 * org.jfree.chart.axis.CategoryAxis; import
 * org.jfree.chart.axis.CategoryLabelPositions; import
 * org.jfree.chart.axis.NumberAxis; import
 * org.jfree.chart.labels.StandardCategoryItemLabelGenerator; import
 * org.jfree.chart.plot.*; import java.awt.*;
 * 
 *//**
	 * FileName : BarChartBean.java Comment :
	 * 
	 * @version : 1.0
	 * @author : sunshiny
	 * @date : 2011. 1. 5.
	 */
/*
 * public class BarChartBean {
 * 
 * public static void main(String arg[]){ BarChartBean bcb = new BarChartBean();
 * 
 * JFreeChart chart = bcb.getBarChart(); ChartFrame frame1 = new ChartFrame(
 * "Bar Chart",chart); frame1.setSize(500,250); frame1.setVisible(true);
 * frame1.setLocation(100, 200);
 * 
 * }
 * 
 * public JFreeChart getBarChart() { String titleStr = "차트 타이틀";
 * 
 * // row keys... final String series1 = "First"; // final String series2 =
 * "Second"; // final String series3 = "Third";
 * 
 * // column keys... final String category1 = "seoul"; final String category2 =
 * "pusan"; final String category3 = "대구"; final String category4 = "인천"; final
 * String category5 = "광주"; final String category6 = "대전"; final String
 * category7 = "울산"; final String category8 = "경기";
 * 
 * // create the dataset... final DefaultCategoryDataset dataset = new
 * DefaultCategoryDataset();
 * 
 * dataset.addValue(1.0, series1, category1); dataset.addValue(5.0, series1,
 * category2); dataset.addValue(4.0, series1, category3); dataset.addValue(27.0,
 * series1, category4); dataset.addValue(30.0, series1, category5);
 * dataset.addValue(10.0, series1, category6); dataset.addValue(11.0, series1,
 * category7); dataset.addValue(7.0, series1, category8);
 * 
 * JFreeChart chart = ChartFactory.createBarChart(titleStr, "", "" , dataset,
 * PlotOrientation.VERTICAL, true,true, false);
 * 
 *//**
	 * Chart 폰트 설정, 클래스 파일 위치 : package org.jfree.chart.StandardChartTheme.java
	 * 
	 *//*
	 * chart.getTitle().setFont(new Font("굴림", Font.BOLD, 20));
	 * chart.getLegend().setItemFont(new Font("굴림", Font.BOLD, 15));
	 * chart.getCategoryPlot().setNoDataMessageFont(new Font("굴림", Font.BOLD,
	 * 15)); chart.getPlot().setNoDataMessageFont(new Font("굴림", Font.BOLD,
	 * 15));
	 * 
	 * System.out.println(chart.getPlot().getNoDataMessageFont().getName());
	 * 
	 * chart.setBackgroundPaint(Color.WHITE);
	 * chart.getTitle().setPaint(Color.orange);
	 * 
	 * StandardCategoryItemLabelGenerator stdCateGen = new
	 * StandardCategoryItemLabelGenerator(); BarRenderer barRender = new
	 * BarRenderer();
	 * 
	 * CategoryPlot plot = chart.getCategoryPlot();
	 * 
	 * barRender.setItemLabelGenerator(stdCateGen); // 그래프별 값 출력
	 * plot.setRenderer(barRender); // 그래프별 값 출력
	 * 
	 * plot.setDomainAxis(new CategoryAxis("지역")); plot.setRangeAxis(new
	 * NumberAxis("수치")); plot.setOrientation(PlotOrientation.VERTICAL);
	 * 
	 * 
	 * // plot.setDomainGridlinesVisible(true);
	 * plot.setRangeGridlinesVisible(true); // 가로 그리드 라인 보이기
	 * 
	 * plot.setRangeGridlinePaint(Color.GRAY);
	 * plot.setBackgroundPaint(Color.WHITE);
	 * 
	 * // set the range axis to display integers only... final NumberAxis
	 * rangeAxis = (NumberAxis) plot.getRangeAxis();
	 * rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	 * rangeAxis.setUpperMargin(0.40);
	 * 
	 * // disable bar outlines... final CategoryItemRenderer renderer =
	 * plot.getRenderer(); renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
	 * 
	 * final CategoryAxis domainAxis = plot.getDomainAxis(); // x축 문자열
	 * 회전(STANDARD, UP_45, UP_90,DOWN_45,DOWN_90)
	 * domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
	 * domainAxis.setLowerMargin(0.01d); domainAxis.setUpperMargin(0.01d);
	 * domainAxis.setCategoryMargin(0.30);
	 * 
	 * return chart; } }
	 */