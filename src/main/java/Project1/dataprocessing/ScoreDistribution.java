package Project1.dataprocessing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class ScoreDistribution extends ChartGenerator{

    private LinkedHashMap<String, Integer> scoreRanges;

    public ScoreDistribution(String csvFile) {
        super(csvFile);
        scoreRanges = new LinkedHashMap<>();
        scoreRanges.put("0-100", 0);
        scoreRanges.put("100-200", 0);
        scoreRanges.put("200-500", 0);
        scoreRanges.put("500-1000", 0);
        scoreRanges.put(">1000", 0);
    }

    @Override
    public void generateChart() {
        try (FileReader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                int score = Integer.parseInt(record.get("Score"));
                updateScoreRange(score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CategoryChart chart = createChart();
        displayChart(chart);
    }

    private void updateScoreRange(int score) {
        if (score >= 0 && score < 100) {
            scoreRanges.put("0-100", scoreRanges.get("0-100") + 1);
        } else if (score >= 100 && score < 200) {
            scoreRanges.put("100-200", scoreRanges.get("100-200") + 1);
        } else if (score >= 200 && score < 500) {
            scoreRanges.put("200-500", scoreRanges.get("200-500") + 1);
        } else if (score >= 500 && score < 1000) {
            scoreRanges.put("500-1000", scoreRanges.get("500-1000") + 1);
        } else {
            scoreRanges.put(">1000", scoreRanges.get(">1000") + 1);
        }
    }

    private CategoryChart createChart() {
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Score Distribution")
                .xAxisTitle("Score Ranges")
                .yAxisTitle("Count")
                .build();

        ArrayList<String> xData = new ArrayList<>(scoreRanges.keySet());
        ArrayList<Integer> yData = new ArrayList<>(scoreRanges.values());

        chart.addSeries("Score Distribution", xData, yData);

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);

        return chart;
    }

    @Override
    protected String getChartName() {
        return "score_distribution";
    }
}
