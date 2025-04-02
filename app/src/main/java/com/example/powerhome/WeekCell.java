
package com.example.powerhome;

public class WeekCell {
    public String jour;
    public String heureDebut;
    public int consommation;
    public float pourcentageJour;

    public WeekCell(String jour, String heureDebut, int consommation, float pourcentageJour) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.consommation = consommation;
        this.pourcentageJour = pourcentageJour;
    }
}
