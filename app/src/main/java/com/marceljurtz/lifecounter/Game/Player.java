package com.marceljurtz.lifecounter.Game;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marceljurtz.lifecounter.ValueService;

import org.w3c.dom.Text;

public class Player {
    private int lifePoints;
    private int poisonPoints;
    private int defaultLifepoints;
    private int defaultPoisonpoints;
    private TextView txtLifepoints;
    private TextView txtPoisonpoints;
    private RelativeLayout backgroundLayout;

    public void setDefaults(int defaultLP, int defaultPP) {
        this.defaultPoisonpoints = defaultPP;
        this.defaultLifepoints = defaultLP;
        this.lifePoints = this.defaultLifepoints;
        this.poisonPoints = this.defaultPoisonpoints;
        txtLifepoints.setText(String.valueOf(this.lifePoints));
        txtPoisonpoints.setText(String.valueOf(this.poisonPoints));
    }

    public Player(TextView lp, TextView pp) {
        this.lifePoints = 0;
        this.poisonPoints = 0;
        txtLifepoints = lp;
        txtPoisonpoints = pp;
    }

    // Neuladen von Standardwerten
    // Bei Änderung der Standardanzahl (Lang-Klick auf Textfeld)
    public void initPoints() {
        this.lifePoints = this.defaultLifepoints;
        this.poisonPoints = this.defaultPoisonpoints;
    }

    // Rücksetzen der Textfelder auf Standardwerte
    public void reset(TextView txtLifepoints, TextView txtPoisonpoints) {
        this.poisonPoints = defaultPoisonpoints;
        txtPoisonpoints.setText(this.poisonPoints+"");
        this.lifePoints = defaultLifepoints;
        txtLifepoints.setText(this.lifePoints+"");

    }

    // Update TextView "LifePoints"
    // Eingabe des Werts zur Änderung der aktuellen Zahl (+/-)
    public void updateLifepoints(int lp, TextView txtLifePoints) {
        this.lifePoints += lp;
        if(this.lifePoints > ValueService.getMaxLife()) {
            this.lifePoints = ValueService.getMaxLife();
        } else if(this.lifePoints < ValueService.getMinLife()) {
            this.lifePoints = ValueService.getMinLife();
        }
        txtLifePoints.setText(this.lifePoints+"");
    }

    // Update TextView "PoisonPoints"
    // Eingabe des Werts zur Änderung der aktuellen Zahl (+/-)
    public void updatePoisonPoints(int pp, TextView txtPoisonPoints) {
        this.poisonPoints += pp;
        if(this.poisonPoints > ValueService.getMaxPoison()) {
            this.poisonPoints = ValueService.getMaxPoison();
        } else if (poisonPoints < ValueService.getMinPoison()) {
            this.poisonPoints = ValueService.getMinPoison();
        }
        txtPoisonPoints.setText(poisonPoints+"");
    }


}
