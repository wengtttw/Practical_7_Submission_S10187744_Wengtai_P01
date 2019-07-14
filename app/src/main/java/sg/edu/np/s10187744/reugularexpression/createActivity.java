package sg.edu.np.s10187744.reugularexpression;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class createActivity extends AppCompatActivity {

    Toast tt;
    boolean u;
    boolean p;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

    }

    public void onClick(View v){
        EditText text = findViewById(R.id.editText);
        String userinput = text.getText().toString();

        EditText pass = findViewById(R.id.editText2);
        String userpass = pass.getText().toString();

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,12}$");
        Matcher matcher = pattern.matcher(userinput);

        Pattern patternpass = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[\\W])[\\Wa-zA-Z0-9]{6,12}$");
        Matcher matcherpass = patternpass.matcher(userpass);

        TextView result = findViewById(R.id.textView2);
        result.setText("" + matcher.matches()); //matches return true/false
        u = matcher.matches();

        TextView resultpass = findViewById(R.id.textView3);
        resultpass.setText("" + matcherpass.matches()); //matches return true/false
        p = matcherpass.matches();

        if (u && p){
            /*part 2*/
            //save data of User Name
            /*SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE).edit();
            username = text.getText().toString();
            password = pass.getText().toString();
            editor.putString("Username", username);
            editor.putString("Password", password);
            editor.commit();*/

            DBHandler dbHandler = new DBHandler((this), null, null, 1);
            UserData newguy = new UserData();
            newguy.setMyUserName(text.getText().toString());
            newguy.setMyPassword(pass.getText().toString());
            dbHandler.addUser(newguy);
            tt = Toast.makeText(createActivity.this, "New User Created Successfully.", Toast.LENGTH_LONG);
            tt.show();
        }

        else{
            tt = Toast.makeText(createActivity.this, "Invalid User Creation. Please Try Again.", Toast.LENGTH_LONG);
            tt.show();
        }
    }

    public void onBack(View v){
        createActivity.this.finish();
    }
}
