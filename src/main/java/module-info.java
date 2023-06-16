module OPT3CodeSmellsPortfolio {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires MaterialFX;

    opens digimonbattlesimulator to javafx.fxml;
    opens digimonbattlesimulator.controller to javafx.fxml;
    opens digimonbattlesimulator.utils to javafx.fxml;
    exports digimonbattlesimulator;
    exports digimonbattlesimulator.controller;
    exports digimonbattlesimulator.digimon;
    exports digimonbattlesimulator.team;
    exports digimonbattlesimulator.utils;
}