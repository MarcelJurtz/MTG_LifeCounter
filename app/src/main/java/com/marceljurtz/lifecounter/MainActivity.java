package com.marceljurtz.lifecounter;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainLayout;
    LinearLayout layoutHome;
    LinearLayout layoutGuest;

    ImageButton cmdPlusGuest;
    ImageButton cmdPlusHome;
    ImageButton cmdMinusGuest;
    ImageButton cmdMinusHome;
    ImageButton cmdResetLP;
    ImageButton cmdTogglePoison;
    ImageButton cmdPlusPoisonHome;
    ImageButton cmdPlusPoisonGuest;
    ImageButton cmdMinusPoisonHome;
    ImageButton cmdMinusPoisonGuest;
    ImageButton cmdBlackHome;
    ImageButton cmdBlackGuest;
    ImageButton cmdBlueHome;
    ImageButton cmdBlueGuest;
    ImageButton cmdGreenHome;
    ImageButton cmdGreenGuest;
    ImageButton cmdRedHome;
    ImageButton cmdRedGuest;
    ImageButton cmdWhiteHome;
    ImageButton cmdWhiteGuest;

    TextView txtLifeCountGuest;
    TextView txtLifeCountHome;
    TextView txtPoisonCountGuest;
    TextView txtPoisonCountHome;

    int LP_Home;
    int LP_Guest;
    int LP_Default;

    int PP_Home;
    int PP_Guest;
    int PP_Default;

    final int PLAYER_HOME = 0;
    final int PLAYER_GUEST = 1;

    final int black = Color.parseColor("#000000");
    final int blue = Color.parseColor("#378BC6");
    final int green = Color.parseColor("#5BD387");
    final int red = Color.parseColor("#AF1D1D");
    final int white = Color.parseColor("#FFFFDD");

    boolean poisonEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.hide();

        mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        layoutGuest = (LinearLayout)findViewById(R.id.layout_top);
        layoutHome = (LinearLayout)findViewById(R.id.layout_bottom);
        LP_Default = 20;
        PP_Default = 0;

        /* ### TextViews ### */
        txtLifeCountGuest = (TextView)findViewById(R.id.txtLifeCountGuest);
        txtLifeCountHome = (TextView)findViewById(R.id.txtLifeCountHome);

        /* ### ImageButtons ### */

        // Guest - Minus
        cmdMinusGuest = (ImageButton)findViewById(R.id.cmdMinusGuest);
        cmdMinusGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LP_Guest > 1) {
                    LP_Guest--;
                    update(LP_Guest, txtLifeCountGuest);
                }
                else {
                    setWinner(PLAYER_HOME);
                }
            }
        });

        // Home - Minus
        cmdMinusHome = (ImageButton)findViewById(R.id.cmdMinusHome);
        cmdMinusHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LP_Home > 1) {
                    LP_Home--;
                    update(LP_Home, txtLifeCountHome);
                }
                else {
                    setWinner(PLAYER_GUEST);
                }
            }
        });

        // Guest - Plus
        cmdPlusGuest = (ImageButton)findViewById(R.id.cmdPlusGuest);
        cmdPlusGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LP_Guest++;
                update(LP_Guest, txtLifeCountGuest);
            }
        });

        // Home - Plus
        cmdPlusHome = (ImageButton)findViewById(R.id.cmdPlusHome);
        cmdPlusHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    LP_Home++;
                    update(LP_Home, txtLifeCountHome);
            }
        });

        // Reset
        cmdResetLP = (ImageButton)findViewById(R.id.cmdResetLP);
        cmdResetLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        cmdTogglePoison = (ImageButton)findViewById(R.id.cmdTogglePoison);
        cmdTogglePoison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poisonEnabled = !poisonEnabled;
                togglePoison(poisonEnabled);
            }
        });

        // PoisonCount Home
        txtPoisonCountHome = (TextView)findViewById(R.id.txtPoisonCountHome);

        // PoisonCount Guest
        txtPoisonCountGuest = (TextView)findViewById(R.id.txtPoisonCountGuest);

        // Poison Home Plus
        cmdPlusPoisonHome = (ImageButton)findViewById(R.id.cmdPlusPoisonHome);
        cmdPlusPoisonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PP_Home < 9) {
                    PP_Home++;
                    update(PP_Home,txtPoisonCountHome);
                }
                else {
                    setWinner(PLAYER_GUEST);
                }
            }
        });

        // Poison Home Minus
        cmdMinusPoisonHome = (ImageButton)findViewById(R.id.cmdMinusPoisonHome);
        cmdMinusPoisonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PP_Home > 0) {
                    PP_Home--;
                    update(PP_Home, txtPoisonCountHome);
                }
            }
        });

        // Poison Guest Plus
        cmdPlusPoisonGuest = (ImageButton)findViewById(R.id.cmdPlusPoisonGuest);
        cmdPlusPoisonGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PP_Guest < 9) {
                    PP_Guest++;
                    update(PP_Guest,txtPoisonCountGuest);
                }
                else {
                    setWinner(PLAYER_HOME);
                }
            }
        });

        // Poison Guest Minus
        cmdMinusPoisonGuest = (ImageButton)findViewById(R.id.cmdMinusPoisonGuest);
        cmdMinusPoisonGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PP_Guest > 0) {
                    PP_Guest--;
                    update(PP_Guest, txtPoisonCountGuest);
                }
            }
        });

        cmdBlackHome = (ImageButton)findViewById(R.id.cmdBlackHome);
        cmdBlackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setLayoutColor(black,layoutHome);
            }
        });
        cmdBlackGuest = (ImageButton)findViewById(R.id.cmdBlackGuest);
        cmdBlackGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setLayoutColor(black,layoutGuest);
            }
        });
        cmdBlueHome = (ImageButton)findViewById(R.id.cmdBlueHome);
        cmdBlueHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(blue,layoutHome);
            }
        });
        cmdBlueGuest = (ImageButton)findViewById(R.id.cmdBlueGuest);
        cmdBlueGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(blue,layoutGuest);
            }
        });
        cmdGreenHome = (ImageButton)findViewById(R.id.cmdGreenHome);
        cmdGreenHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(green,layoutHome);
            }
        });
        cmdGreenGuest = (ImageButton)findViewById(R.id.cmdGreenGuest);
        cmdGreenGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(green, layoutGuest);
            }
        });
        cmdRedHome = (ImageButton)findViewById(R.id.cmdRedHome);
        cmdRedHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(red,layoutHome);
            }
        });
        cmdRedGuest = (ImageButton)findViewById(R.id.cmdRedGuest);
        cmdRedGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(red,layoutGuest);
            }
        });
        cmdWhiteHome = (ImageButton)findViewById(R.id.cmdWhiteHome);
        cmdWhiteHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(white,layoutHome);
            }
        });
        cmdWhiteGuest = (ImageButton)findViewById(R.id.cmdWhiteGuest);
        cmdWhiteGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutColor(white,layoutGuest);
            }
        });

        reset();
    }

    // Update TextView
    private void update(int points, TextView txtPoints) {
            txtPoints.setText(points+"");
    }

    // Beide Leben wieder auf 20 setzen
    private void reset() {
        cmdMinusGuest.setVisibility(View.VISIBLE);
        cmdMinusHome.setVisibility(View.VISIBLE);
        cmdPlusGuest.setVisibility(View.VISIBLE);
        cmdPlusHome.setVisibility(View.VISIBLE);
        LP_Guest = LP_Default;
        LP_Home = LP_Default;
        txtLifeCountGuest.setText(LP_Guest+"");
        txtLifeCountHome.setText(LP_Home+"");

        PP_Guest = PP_Default;
        PP_Home = PP_Default;
        txtPoisonCountGuest.setText(PP_Guest+"");
        txtPoisonCountHome.setText(PP_Home+"");

        poisonEnabled = false;
        togglePoison(false);
        // mainLayout.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_small));
    }

    private void setWinner(int player) {

        cmdMinusGuest.setVisibility(View.GONE);
        cmdMinusHome.setVisibility(View.GONE);
        cmdPlusGuest.setVisibility(View.GONE);
        cmdPlusHome.setVisibility(View.GONE);
        togglePoisonViews(false);

        if(player == PLAYER_HOME) {
            txtLifeCountHome.setText("WINNER");
            txtLifeCountGuest.setText("LOOSER");
            // mainLayout.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_small_winner_home));
        }
        else {
            txtLifeCountHome.setText("LOOSER");
            txtLifeCountGuest.setText("WINNER");
            // mainLayout.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_small_winner_guest));
        }
    }

    private void togglePoison(boolean toggle) {
        if(toggle) {
            cmdTogglePoison.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_trigger_poison_enabled));
        }
        else {
            cmdTogglePoison.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_trigger_poison_disabled));
        }
        togglePoisonViews(toggle);
    }
    private void togglePoisonViews(boolean toggle) {
        if(toggle) {
            txtPoisonCountGuest.setVisibility(View.VISIBLE);
            txtPoisonCountHome.setVisibility(View.VISIBLE);
            cmdPlusPoisonGuest.setVisibility(View.VISIBLE);
            cmdPlusPoisonHome.setVisibility(View.VISIBLE);
            cmdMinusPoisonGuest.setVisibility(View.VISIBLE);
            cmdMinusPoisonHome.setVisibility(View.VISIBLE);
        }
        else {
            txtPoisonCountGuest.setVisibility(View.INVISIBLE);
            txtPoisonCountHome.setVisibility(View.INVISIBLE);
            cmdPlusPoisonGuest.setVisibility(View.INVISIBLE);
            cmdPlusPoisonHome.setVisibility(View.INVISIBLE);
            cmdMinusPoisonGuest.setVisibility(View.INVISIBLE);
            cmdMinusPoisonHome.setVisibility(View.INVISIBLE);
        }
    }
    private void setLayoutColor(int color, LinearLayout layout) {
        layout.setBackgroundColor(color);
    }
}
