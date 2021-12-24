package com.example.apptravelvn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import android.view.Window;

import com.example.apptravelvn.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomnav);
        callFragment("Home");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.action_home:

                        callFragment("Home");
                        break;
                    case  R.id.action_fav:

                        callFragment("Fav");
                        break;
                    case R.id.action_mypage:
                        callFragment("MyPage");
                        break;
                    case R.id.action_video:
                        callFragment("Video");
                        break;
                }
                return true;
            }
        });
        preferenceManager= new PreferenceManager(getApplicationContext());

       // Toast.makeText(this, preferenceManager.getString(TaiKhoan.KEY_USER_NAME), Toast.LENGTH_SHORT).show();
    }
    void callFragment(String ten){
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        if(ten.equals("Home")){
            FragmentHome  fragmentA= new FragmentHome();
            fragmentTransaction.replace(R.id.fragmentcontenr,fragmentA);
        }
        else if(ten.equals("Fav")){
            FragmentFavorite fragmentHome= new FragmentFavorite();
            fragmentTransaction.replace(R.id.fragmentcontenr,fragmentHome);
        }else if(ten.equals("MyPage")){
            FragmentMyPage fragmentMyPage= new FragmentMyPage();
            fragmentTransaction.replace(R.id.fragmentcontenr,fragmentMyPage);
        }
        else if(ten.equals("Video")){
            Fragment_Video fragmentMyPage= new Fragment_Video();
            fragmentTransaction.replace(R.id.fragmentcontenr,fragmentMyPage);
        }
        fragmentTransaction.commit();
    }


}