package serial;

import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 
 * Implement Java FreeChart to draw the result of gyro and acc
 *
 */
public class Plot {

	public static CategoryDataset GetDataset(Double[] RollGyro, Double[] PitchGyro, Double[] YawGyro, Double[] RollAcc,
			Double[] PitchAcc, Double[] YawAcc) {
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		for (int i = 0; i < RollGyro.length; i++) {
			mDataset.addValue(RollGyro[i], "RollGyro", (Double) (i * 0.01));
			mDataset.addValue(PitchGyro[i], "PitchGyro", (Double) (i * 0.01));
			mDataset.addValue(YawGyro[i], "YawGyro", (Double) (i * 0.01));
			mDataset.addValue(RollAcc[i], "RollAcc", (Double) (i * 0.01));
			mDataset.addValue(PitchAcc[i], "PitchAcc", (Double) (i * 0.01));
			mDataset.addValue(YawAcc[i], "YawAcc", (Double) (i * 0.01));

		}

		return mDataset;
	}

	public static JFreeChart drawData(Double[] RollGyro, Double[] PitchGyro, Double[] YawGyro, Double[] RollAcc,
			Double[] PitchAcc, Double[] YawAcc) {
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("Arial", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("Arial", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("Arial", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = GetDataset(RollGyro, PitchGyro, YawGyro, RollAcc, PitchAcc, YawAcc);
		JFreeChart mChart = ChartFactory.createLineChart("chart", "time", "data", mDataset, PlotOrientation.VERTICAL,
				true, true, false);
		CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
		mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		mPlot.setRangeGridlinePaint(Color.BLUE);
		mPlot.setOutlinePaint(Color.RED);

		//ChartFrame mChartFrame = new ChartFrame("data", mChart);
		return mChart;
//		
//		JPanel jPanel1 = new JPanel();
//		jPanel1.setLayout(new java.awt.BorderLayout());	
//		ChartPanel CP = new ChartPanel(chart); 
//		jPanel1.add(CP,BorderLayout.CENTER);
//		jPanel1.validate();
	}

}
