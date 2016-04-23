package com.example.engsara.graphtry5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private int month;
    private Date date;
    private double[] xValues;
    private double[] yValues;
    private String[] mMonth = new String[] {"Sun" , "Mon", "Tus", "Wed", "Thu","Fri","Sat"};
    //   static final String TAG = WeightCurveActivity.class.getName(); //DB
    //  private ArrayList<WeightRecord> records;  //table
    // int count = records.size();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button heartChart = (Button) findViewById(R.id.btn_heart); // open heart chart
      heartChart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openHeartGraph();
          }
      });
        Button tempChart = (Button) findViewById(R.id.btn_temp); // open temperature chart
        tempChart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openTempGraph();
            }
        });
        Button preChart = (Button) findViewById(R.id.btn_press); // open pressure chart
        preChart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openPreGraph();
            }
        });
    }
    ////heartBeats chart
    private void openHeartGraph(){
         /*int y[] = data;   //get values from database
          int x[] = new int[y.length];
         for (int i=0 ; i < y.length; i++){
            x[i] = i;
        }*/
        /*
        if (count > 0) {
            xValues = new double[count];
            yValues = new double[count];
            for (int i = 0; i < count; i++) {
                WeightRecord record = records.get(i);
                xValues[i] = DateHelper.getDay(record.record_on);
                yValues[i] = record.weight;
            }
        }
        */
        int[] x = {1,2,3,4,5,6,7};
        int[] Heart = {40,50,60,70,80,90,100,150}; //get values from database
        // Creating an  XYSeries for heartbeats
        XYSeries heartSeries = new XYSeries("Heartbeats");
        // Adding data to heartbeats  Series
        /*
        if (records.size() > 0) {
            int seriesLength = xValues.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(k * 3 + 1, yValues[k]);
            }
        }
         */
        for(int i=0; i < x.length; i++){
            heartSeries.add(x[i],Heart[i]);
        }
        // Creating a dataset to hold series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding heartbeats Series to the dataset
        dataset.addSeries(heartSeries);
        // Creating XYSeriesRenderer to customize heartSeries
        XYSeriesRenderer heartRenderer = new XYSeriesRenderer();
        heartRenderer.setColor(Color.GREEN);
        heartRenderer.setChartValuesTextSize(18);
        heartRenderer.setPointStyle(PointStyle.CIRCLE);
        heartRenderer.setFillPoints(true);
        heartRenderer.setLineWidth(2);
        heartRenderer.setDisplayChartValues(true);
        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        int length = xValues.length;
        for (int i = 0; i < length; i++) {
            multiRenderer.addXTextLabel(i * 3 + 1, month + "/" + (int) xValues[i]);
        }
        multiRenderer.setLabelsTextSize(18);
        multiRenderer.setShowGridX(true);
        multiRenderer.setShowLegend(false);
        multiRenderer.setGridColor(Color.LTGRAY);
        multiRenderer.setPointSize(5f);
        multiRenderer.setPanEnabled(true, false);
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        multiRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        multiRenderer.addSeriesRenderer(heartRenderer);
        multiRenderer.setChartTitle("Avg Heart beats");
        multiRenderer.setXTitle("date of measurement reading");
        multiRenderer.setYTitle("No. of heartbeats in one minute");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }
        // Adding incomeRenderer and expenseRenderer to multipleRenderer
       // The order of adding dataseries to dataset and renderers to multipleRenderer
        multiRenderer.addSeriesRenderer(heartRenderer);
        // Creating an intent to plot line chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);
        // Start Activity
        startActivity(intent);
    }
    /// temperature chart
    private void openTempGraph(){
        int[] x = {1,2,3,4,5,6,7};
        double[] Temp = {36.8,37,37.2,37.5,38,38.3,40,41.8}; //get values from database
        // Creating an  XYSeries for Income
        XYSeries tempSeries = new XYSeries("Temperature");
        // Adding data to temperature  Series
        for(int i=0; i < x.length; i++){
            tempSeries.add(x[i],Temp[i]);
        }
        // Creating a dataset to hold  series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding temperature Series to the dataset
        dataset.addSeries(tempSeries);
        // Creating XYSeriesRenderer to customize tempSeries
        XYSeriesRenderer tempRenderer = new XYSeriesRenderer();
        tempRenderer.setChartValuesTextSize(18);
        tempRenderer.setColor(Color.GREEN);
        tempRenderer.setPointStyle(PointStyle.CIRCLE);
        tempRenderer.setFillPoints(true);
        tempRenderer.setLineWidth(2);
        tempRenderer.setDisplayChartValues(true);
        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setLabelsTextSize(18);
        multiRenderer.setShowGridX(true);
        multiRenderer.setShowLegend(false);
        multiRenderer.setGridColor(Color.LTGRAY);
        multiRenderer.setPointSize(5f);
        multiRenderer.setPanEnabled(true, false);
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        multiRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        multiRenderer.addSeriesRenderer(tempRenderer);
        multiRenderer.setChartTitle("Avg temperature");
        multiRenderer.setXTitle("date of measurement reading");
        multiRenderer.setYTitle("Temperature degree");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }
        // Adding tempRenderer to multipleRenderer
       // The order of adding dataseries to dataset and renderer to multipleRenderer
        multiRenderer.addSeriesRenderer(tempRenderer);
        // Creating an intent to plot line chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);
        // Start Activity
        startActivity(intent);
    }
    //// pressure chart
    private void openPreGraph(){
        int[] x = {1,2,3,4,5,6,7};
        int[] dia = {60,80,85,90,100,110,120}; //get values from database
        int[] sys = {80,100,120,130,140,160,180,190};
        // Creating an  XYSeries for pressure
        XYSeries diaSeries = new XYSeries("Diastolic");
        XYSeries sysSeries = new XYSeries("Systolic");
        // Adding data to pressure  Series
        for(int i=0; i < x.length; i++){
            diaSeries.add(x[i],dia[i]);
            sysSeries.add(x[i],sys[i]);
        }
        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding pressure Series to the dataset
        dataset.addSeries(diaSeries);
        // Adding Expense Series to dataset
        dataset.addSeries(sysSeries);
        // Creating XYSeriesRenderer to customize presSeries
        XYSeriesRenderer diaRenderer = new XYSeriesRenderer();
        diaRenderer.setColor(Color.GREEN);
        diaRenderer.setPointStyle(PointStyle.CIRCLE);
        diaRenderer.setChartValuesTextSize(18);
        diaRenderer.setFillPoints(true);
        diaRenderer.setLineWidth(2);
        diaRenderer.setDisplayChartValues(true);
        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer sysRenderer = new XYSeriesRenderer();
        sysRenderer.setColor(Color.YELLOW);
        sysRenderer.setChartValuesTextSize(18);
        sysRenderer.setPointStyle(PointStyle.CIRCLE);
        sysRenderer.setFillPoints(true);
        sysRenderer.setLineWidth(2);
        sysRenderer.setDisplayChartValues(true);
        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setLabelsTextSize(18);
        multiRenderer.setShowGridX(true);
        multiRenderer.setShowLegend(false);
        multiRenderer.setGridColor(Color.LTGRAY);
        multiRenderer.setPointSize(5f);
        multiRenderer.setPanEnabled(true, false);
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        multiRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        multiRenderer.addSeriesRenderer(diaRenderer);
        multiRenderer.addSeriesRenderer(sysRenderer);
        multiRenderer.setChartTitle("pressure");
        multiRenderer.setXTitle("date of measurement reading");
        multiRenderer.setYTitle("systolic vs diastolic pressure");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }
        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // The order of adding dataseries to dataset and renderer to multipleRenderer
        multiRenderer.addSeriesRenderer(diaRenderer);
        multiRenderer.addSeriesRenderer(sysRenderer);
        // Creating an intent to plot line chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);
        // Start Activity
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
