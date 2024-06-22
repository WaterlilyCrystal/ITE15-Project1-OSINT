package Project1.dataprocessing;

public class Main {
    public static void main(String[] args) {
        GenderStatistic genderStatistic = new GenderStatistic("C:\\Users\\Hello\\IdeaProjects\\OSINT\\src\\main\\java\\Project1\\dataprocessing\\data.csv");
        genderStatistic.generateChart();

        SocialAccountStatus socialAccountStatus = new SocialAccountStatus("C:\\Users\\Hello\\IdeaProjects\\OSINT\\src\\main\\java\\Project1\\dataprocessing\\data.csv");
        socialAccountStatus.generateChart();

        ScoreDistribution scoreDistribution = new ScoreDistribution("C:\\Users\\Hello\\IdeaProjects\\OSINT\\src\\main\\java\\Project1\\dataprocessing\\data.csv");
        scoreDistribution.generateChart();
    }
}

