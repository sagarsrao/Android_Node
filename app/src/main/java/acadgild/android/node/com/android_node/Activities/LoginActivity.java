package acadgild.android.node.com.android_node.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acadgild.android.node.com.android_node.R;
import acadgild.android.node.com.android_node.model.UserSignin;
import acadgild.android.node.com.android_node.server.ApiClient;
import acadgild.android.node.com.android_node.server.ClientSide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    /**
     * Globally declaring variables of all the android widgets
     */
    EditText mName, mEmailID, mPassword, res_Email;
    Button mLogin, mRegister, cont, cancel;
    SharedPreferences pref;
    String email, password;

    ApiClient apiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
/**
 * Android widgets used instantiation
 */
        mRegister = (Button) findViewById(R.id.buttonRegister);

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);
        mName = (EditText) findViewById(R.id.editTextName);
        mEmailID = (EditText) findViewById(R.id.editTextEmailID);
        mPassword = (EditText) findViewById(R.id.editTextPhoneNumber);
        mLogin = (Button) findViewById(R.id.buttonsignin);

        apiClient = new ApiClient();

        //OnClick of register button
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegisterScreen();
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = mEmailID.getText().toString();
                password = mPassword.getText().toString();

                if (!isValidEmail(email)) {

                    mEmailID.setError("Invalid Email");

                } else if (!isValidPassword(password)) {
                    mPassword.setError("Invalid Password");

                } else {
                    setToLogin();
                }
            }
        });


    }


    public void setToLogin() {

        email = mEmailID.getText().toString();

        password = mPassword.getText().toString();


        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("password", password);

        //We are creating the calling the REST service
        //getApiService is the method which has the return type of ClientClass
        ClientSide callClient = ApiClient.getApiService();

        Call<UserSignin> userSigninCall = callClient.request(parameters);

        userSigninCall.enqueue(new Callback<UserSignin>() {
            @Override
            public void onResponse(Call<UserSignin> call, Response<UserSignin> response) {


                startActivity(new Intent(LoginActivity.this, NavigationDrawer.class));
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserSignin> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

            }
        });


    }//end of method

    //Validating email

    /**
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //validating password

    /**
     * @param password
     * @return
     */
    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 6) {
            return true;
        }
        return false;
    }

    /**
     * Intent activity to the regsiter screen on the click of button
     */
    public void gotoRegisterScreen() {

        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();

    }//end of method

}//end of class


