package com.example.dicoding;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;

    TextView homeyellowcard, awayyellowcard, AwayLineupDefense, AwayLineupGoalkeeper, HomeLineupDefense,lineupgoalkepperhome,HomeLineupMidfield,AwayLineupMidfield , strEvent ;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.tl_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        this.setTitle("Detail " + getIntent().getStringExtra("events"));
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        //inisialisasi
        strEvent =  findViewById(R.id.strEvent);
        homeyellowcard =  findViewById(R.id.tv_homeyellowcard);
        awayyellowcard =  findViewById(R.id.tv_awayyellowcard);
//        AwayLineupDefense =  findViewById(R.id.tv_AwayLineupDefense);
//        AwayLineupGoalkeeper =  findViewById(R.id.tv_AwayLineupGoalkeeper);
//        HomeLineupDefense =  findViewById(R.id.tv_HomeLineupDefense);
        lineupgoalkepperhome =  findViewById(R.id.tv_lineupgoalkepperhome);
//        HomeLineupMidfield =  findViewById(R.id.tv_HomeLineupMidfield);
        AwayLineupMidfield =  findViewById(R.id.tv_AwayLineupMidfield);
         imageView=findViewById(R.id.imageview);

        final ModelJadwal surah = getIntent().getExtras().getParcelable("hasil");
        if(getIntent().getExtras() != null){

            String thumb = getIntent().getStringExtra("events4");
            String thumb1 = getIntent().getStringExtra("events2");
            System.out.println("eaea "+thumb1 + thumb);

            if (thumb1.equals("null") && thumb.equals("null")){
                Glide.with(this)
                        .load(R.drawable.ic_broken_image_black_24dp)
                        .apply(new RequestOptions().override(350, 550))
                        .into(imageView);

            }

            else if (thumb.equals("null")){
                Glide.with(this)
                        .load(thumb1)
                        .into(imageView);

            }


            else {

                Glide.with(this)
                        .load(thumb)
                        .into(imageView);
            }
            homeyellowcard.setText(getIntent().getStringExtra("events3"));
            awayyellowcard.setText(getIntent().getStringExtra("events5"));
//            AwayLineupDefense.setText(getIntent().getStringExtra("events4"));
//            AwayLineupGoalkeeper.setText(getIntent().getStringExtra("events7"));
//            HomeLineupDefense.setText(getIntent().getStringExtra("events10"));
            lineupgoalkepperhome.setText(getIntent().getStringExtra("events9"));
//            HomeLineupMidfield.setText(getIntent().getStringExtra("events6"));
            AwayLineupMidfield.setText(getIntent().getStringExtra("events8"));
            strEvent.setText(getIntent().getStringExtra("events"));

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
