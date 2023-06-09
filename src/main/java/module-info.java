module OPT3CodeSmellsPortfolio {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    opens digimonbattlesimulator to javafx.fxml;
    exports digimonbattlesimulator;
    exports digimonbattlesimulator.controller;
    opens digimonbattlesimulator.controller to javafx.fxml;
    exports digimonbattlesimulator.util;
    opens digimonbattlesimulator.util to javafx.fxml;
}