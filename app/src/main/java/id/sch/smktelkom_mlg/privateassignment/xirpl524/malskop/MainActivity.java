package id.sch.smktelkom_mlg.privateassignment.xirpl524.malskop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import id.sch.smktelkom_mlg.privateassignment.xirpl524.malskop.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl524.malskop.sugar.FavoriteFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SourceAdapter.ISourceAdapter {

    public static final String RESULTPOSTER = "resultPoster";
    public static final String RESULTOVER = "resultOverview";
    public static final String RESULTRELEASE = "resultRelease";
    public static final String RESULTTITLE = "resultTitle";
    public static final String RESULTBACK = "resultBackdrop";
    public static final String RESULTVOTE = "resultOver";
    public static final String RESULTLANGUAGE = "resultLanguage";
    public static final String RESULTPOPULARITY = "resultPopularity";
    public static final String RESULTVOTECOUNT = "resultVote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        changePage(id);
        return true;
    }

    private void changePage(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment = new TopRatedFragment();
            setTitle("Top rated");
        } else if (id == R.id.nav_gallery) {
            fragment = new UpcomingFragment();
            setTitle("Coming Soon");
        } else if (id == R.id.nav_slideshow) {
            fragment = new NowPlayingFragment();
            setTitle("Now Playing");
        } else if (id == R.id.nav_fav) {
            fragment = new FavoriteFragment();
            setTitle("Favorite List");
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commitNow();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showArticles(String poster_path, String overview, String release_date, String title, String backdrop_path, String vote_average, String original_language, String popularity, String vote_count) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESULTPOSTER, poster_path);
        intent.putExtra(RESULTOVER, overview);
        intent.putExtra(RESULTRELEASE, release_date);
        intent.putExtra(RESULTTITLE, title);
        intent.putExtra(RESULTBACK, backdrop_path);
        intent.putExtra(RESULTVOTE, vote_average);
        intent.putExtra(RESULTLANGUAGE, original_language);
        intent.putExtra(RESULTPOPULARITY, popularity);
        intent.putExtra(RESULTVOTECOUNT, vote_count);
        startActivity(intent);
    }

}



