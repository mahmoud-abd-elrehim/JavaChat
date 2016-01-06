/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author Koky
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.
 */
public class PieChartStat extends JFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public static int onLine;
    public static int offLine;
    public PieChartStat(String title,int on,int off) {
        super(title);
         onLine=on;
       offLine=off;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
        
        
        setContentPane(createDemoPanel());
        
        
            this.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                // dispose();
                //System.exit(0);
               //  PieChartStat.this.setVisible(false);
                }
                }
            );
            
            
         
          }
    public void setMyData(int on,int off)
    {
        onLine=on;
        offLine=off;

    
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Online", new Double(onLine));
        dataset.setValue("Offline",new Double(offLine));
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 1",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");

        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

   

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    /*public static void main(String[] args) {

        
        PieChartStat demo = new PieChartStat("Pie Chart Demo 1",5,5);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    
                
                }
*/
}
