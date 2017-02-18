package acadgild.android.node.com.android_node.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acadgild.android.node.com.android_node.R;
import acadgild.android.node.com.android_node.model.ReturnRes;
import acadgild.android.node.com.android_node.server.ApiClient;
import acadgild.android.node.com.android_node.server.ClientSide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText eduser, edmail, edpassword;
    Button butregister, btloginnow;

    String textuser;
    String textEmail;
    String txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityregister);

/**
 * Widgets control and initilization
 */
        eduser = (EditText) findViewById(R.id.editTextName);
        edmail = (EditText) findViewById(R.id.editTextemail);
        edpassword = (EditText) findViewById(R.id.editTextPassword);
        butregister = (Button) findViewById(R.id.buttonRegister);
        btloginnow = (Button) findViewById(R.id.butLoginNow);

/**
 * OnClick event for the login Button
 */
        btloginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

/**
 * OnClick event for the login Regsiter button
 */
        butregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                textuser = eduser.getText().toString();
                textEmail = edmail.getText().toString();
                txtPassword = edpassword.getText().toString();

                if (!isValidUser(textuser)) {

                    eduser.setError("User name should not be empty");
                } else if (!isValidEmail(textEmail)) {

                    edmail.setError("Invalid Email");

                } else if (!isValidPassword(txtPassword)) {
                    edpassword.setError("Invalid Password");

                } else {

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("name", textuser);
                    params.put("email", textEmail);
                    params.put("password", txtPassword);

                    ClientSide clientSide = ApiClient.getApiService();
                    Call<ReturnRes> call = clientSide.request3(params);
                    Log.d("Values inside the call", "" + call.toString());
                    call.enqueue(new Callback<ReturnRes>() {
                        @Override
                        public void onResponse(Call<ReturnRes> call, Response<ReturnRes> response) {
                            Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ReturnRes> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "failure", Toast.LENGTH_SHORT).show();
                        }

                    });
                }
            }

        });

    }



    /**
     *
     * @param textuser
     * @return
     */
    //Validating username
   public boolean isValidUser(String textuser) {

        if (textuser != null) {
            return true;
        }
        return false;
    }



    //Validating email

    /**
     *
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
     *
     * @param password
     * @return
     */
    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 6) {
            return true;
        }
        return false;
    }



}

