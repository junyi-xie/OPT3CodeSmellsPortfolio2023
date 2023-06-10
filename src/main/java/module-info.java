module OPT3CodeSmellsPortfolio {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    opens digimonbattlesimulator to javafx.fxml;
    exports digimonbattlesimulator;
    exports digimonbattlesimulator.controller;
    opens digimonbattlesimulator.controller to javafx.fxml;
    exports digimonbattlesimulator.digimon;
    exports digimonbattlesimulator.team;
    exports digimonbattlesimulator.utils;
    opens digimonbattlesimulator.utils to javafx.fxml;
}