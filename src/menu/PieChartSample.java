/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu ;



import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import pi.dao.classes.StatistiqueDAO;
import pi.dao.interfaces.IStatistiqueDAO;

/**
 *
 * @author Yasmina
 */
public class PieChartSample implements Initializable {
    
    private Connection c;
    @FXML
    private BorderPane p;
  
    
    public PieChartSample() {
    }

    //  private ObservableList data;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        IStatistiqueDAO statdao = StatistiqueDAO.getInstance();
        ObservableList<Statistique> dataS = null;
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        
            dataS = statdao.ByDomainEnvironement();
       
       for (int i = 0; i < dataS.size(); i++) {
            System.out.println(dataS.get(i).getDomaine());
            data.add(new PieChart.Data(dataS.get(i).getDomaine(), dataS.get(i).getCptdomaine()));
     }
        PieChart chart = new PieChart(data);
        
        for (PieChart.Data d : data) {
            d.getNode().setOnMouseEntered(new MouseHoverAnimation(d, chart));
            d.getNode().setOnMouseExited(new MouseExitAnimation());
        }
        
        chart.setClockwise(false);
        p.setCenter(chart);
       
        
        chart.setAnimated(true);
        chart.setMaxSize(600, 600);
        chart.setLabelsVisible(true);
        chart.setTitle("Statistics by application domains");
        
    }
    
    static class MouseHoverAnimation implements EventHandler<MouseEvent> {
        
        static final Duration ANIMATION_DURATION = new Duration(500);
        static final double ANIMATION_DISTANCE = 0.15;
        private double cos;
        private double sin;
        private PieChart chart;
        
        public MouseHoverAnimation(PieChart.Data d, PieChart chart) {
            this.chart = chart;
            double start = 0;
            double angle = calcAngle(d);
            for (PieChart.Data tmp : chart.getData()) {
                if (tmp == d) {
                    break;
                }
                start += calcAngle(tmp);
            }
            
            cos = Math.cos(Math.toRadians(0 - start - angle / 2));
            sin = Math.sin(Math.toRadians(0 - start - angle / 2));
        }
        
        @Override
        public void handle(MouseEvent arg0) {
            Node n = (Node) arg0.getSource();
            
            double minX = Double.MAX_VALUE;
            double maxX = Double.MAX_VALUE * -1;
            
            for (PieChart.Data d : chart.getData()) {
                minX = Math.min(minX, d.getNode().getBoundsInParent().getMinX());
                maxX = Math.max(maxX, d.getNode().getBoundsInParent().getMaxX());
            }
            
            double radius = maxX - minX;
            TranslateTransitionBuilder.create().toX((radius * ANIMATION_DISTANCE) * cos).toY((radius * ANIMATION_DISTANCE) * sin).duration(ANIMATION_DURATION).node(n).build().play();
        }
        
        private static double calcAngle(PieChart.Data d) {
            double total = 0;
            for (PieChart.Data tmp : d.getChart().getData()) {
                total += tmp.getPieValue();
            }
            
            return 360 * (d.getPieValue() / total);
        }
    }
    
    static class MouseExitAnimation implements EventHandler<MouseEvent> {
        
        @Override
        public void handle(MouseEvent event) {
            TranslateTransitionBuilder.create().toX(0).toY(0).duration(new Duration(500)).node((Node) event.getSource()).build().play();
        }
    }
    
}