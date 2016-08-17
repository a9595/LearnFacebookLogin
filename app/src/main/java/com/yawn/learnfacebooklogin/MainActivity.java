package com.yawn.learnfacebooklogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.filmsRecycler) RecyclerView mRecycler;

  private CallbackManager mCallbackManage;
  private List<Film> mFilms;
  private AdapterGridView mAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    initFacebook();
    initDrawer(toolbar);
    initRecycler();
  }

  private void initRecycler() {
    mRecycler = (RecyclerView) findViewById(R.id.filmsRecycler);
    final int spanCount = 2;
    final GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);

    mRecycler.setHasFixedSize(true);
    mRecycler.setLayoutManager(layoutManager);

    int spacing = 30; //px
    mRecycler.addItemDecoration(new ItemDecorationGridSpacing(spanCount, spacing, true));

    mFilms = Film.getDummy(60);
    mAdapter = new AdapterGridView(MainActivity.this, mFilms);
    mRecycler.setAdapter(mAdapter);
  }

  private void initDrawer(Toolbar toolbar) {
    Drawer build = new DrawerBuilder().withActivity(this).build();
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Films");
    SecondaryDrawerItem item2 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(2)
        .withName(R.string.item2)
        .withIcon(FontAwesome.Icon.faw_anchor);
    PrimaryDrawerItem item3 = new SecondaryDrawerItem().withName("News");
    AccountHeader accountHeader = getAccountHeader();

    Drawer resultDrawer = new DrawerBuilder().withActivity(MainActivity.this)
        .withToolbar(toolbar)
        .withAccountHeader(accountHeader)
        .addDrawerItems(item1, new DividerDrawerItem(), item2,
            //new DividerDrawerItem(),
            item3)
        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
          @Override public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            /*Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_SHORT)
                .show();*/
            return false;
          }
        })
        .build();

    resultDrawer.setSelection(-1);
    item1.withBadge("19")
        .withBadgeStyle(
            new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));

    resultDrawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Logout"));
    //resultDrawer.openDrawer();
  }

  private AccountHeader getAccountHeader() {
    return new AccountHeaderBuilder().withActivity(MainActivity.this)
        .withHeaderBackground(R.drawable.image_movies)
        .addProfiles(new ProfileDrawerItem().withName("Anna Kovbasiuk")
            .withEmail("annko2012@gmail.com")
            .withIcon(getResources().getDrawable(R.drawable.ic_account)))
        .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
          @Override public boolean onProfileChanged(View view, IProfile profile, boolean current) {
            return false;
          }
        })
        .build();
  }

  private void initFacebook() {
    FacebookSdk.sdkInitialize(getApplicationContext());
    mCallbackManage = CallbackManager.Factory.create();

    AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
    Profile currentProfile = Profile.getCurrentProfile();
    if (currentAccessToken == null) {
      startActivity(new Intent(this, LoginActivity.class));
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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
}
