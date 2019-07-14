package sg.edu.np.s10187744.reugularexpression;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    Toast tt;
    boolean u;
    boolean p;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView clickme = findViewById(R.id.textView8);

        clickme.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    Intent i = new Intent(MainActivity.this, createActivity.class);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });

    }

    public void onClick(View v){
        EditText text = findViewById(R.id.editText3);
        String userinput = text.getText().toString();

        EditText pass = findViewById(R.id.editText4);
        String userpass = pass.getText().toString();

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,12}$");
        Matcher matcher = pattern.matcher(userinput);

        Pattern patternpass = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[\\W])[\\Wa-zA-Z0-9]{6,12}$");
        Matcher matcherpass = patternpass.matcher(userpass);
        u = matcher.matches();
        p = matcherpass.matches();

        if (u && p) {
            /*part 2*/
            /*SharedPreferences prefs = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE);
            username = prefs.getString("Username", "");
            password = prefs.getString("Password", "");
            if (userinput.equals(username) && userpass.equals(password)){
                tt = Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_LONG);
                tt.show();
            }
            else if (!userinput.equals(username) || !userpass.equals(password)){
                tt = Toast.makeText(MainActivity.this, "Username or password is wrong", Toast.LENGTH_LONG);
                tt.show();
            }*/

            DBHandler dbHandler = new DBHandler((this), null, null, 1);
            UserData dbData = dbHandler.findUser(text.getText().toString());
            if (dbData.getMyPassword().equals(pass.getText().toString()) && dbData.getMyUserName().equals(text.getText().toString())){
                tt = Toast.makeText(MainActivity.this, "Valid. Login successfully", Toast.LENGTH_LONG);
                tt.show();
            }
            else if (!dbData.getMyPassword().equals(pass.getText().toString())){
                tt = Toast.makeText(MainActivity.this, "Invalid. Password is wrong", Toast.LENGTH_LONG);
                tt.show();
            }
        }
        else{
            tt = Toast.makeText(MainActivity.this, "Invalid. Login fail", Toast.LENGTH_LONG);
            tt.show();
        }
    }

}

