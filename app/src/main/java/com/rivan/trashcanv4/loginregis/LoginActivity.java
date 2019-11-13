package com.rivan.trashcanv4.loginregis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rivan.trashcanv4.MainActivity;
import com.rivan.trashcanv4.R;
import com.rivan.trashcanv4.SharedPreferencesManager;
import com.rivan.trashcanv4.model.ResponseLogin;
import com.rivan.trashcanv4.network.ServiceClient;
import com.rivan.trashcanv4.network.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etPassword, etEmail;
    TextView signUp;
    ProgressDialog progressDialog;
    SharedPreferencesManager sharedPreferencesManager;

    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "test";
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    private Button tnLogingoogle, tnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.edt_login_email);
        etPassword = findViewById(R.id.edt_login_password);
        sharedPreferencesManager = new SharedPreferencesManager(this);
        tnLogin = findViewById(R.id.tn_login);
        tnLogingoogle = findViewById(R.id.tn_logingoogle);
        signUp = findViewById(R.id.tv_signup);
        signUp.setOnClickListener(this);
        tnLogin.setOnClickListener(this);
        tnLogingoogle.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);


        if (sharedPreferencesManager.getSigned()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }



        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_signup:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
            case R.id.tn_logingoogle :
                signIn();
                break;
            case R.id.tn_login:
                String messageEmail = "Email tidak boleh kosong";
                String messagePassword = "Password tidak boleh kosong";
                progressDialog.setMessage("Loading");
                progressDialog.show();

                if (etEmail.getText().toString().isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(this, messageEmail, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPassword.getText().toString().isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(this, messagePassword, Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailUser = etEmail.getText().toString().trim().toUpperCase();
                String passUser = etPassword.getText().toString().trim();

                ServiceClient serviceClient = ServiceGenerator.createService(ServiceClient.class);
                Call<ResponseLogin> responseLogin = serviceClient.loginUser("loginUser", "login", emailUser, passUser);

                responseLogin.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if (response.body().getHasil().equals("success")){
                            progressDialog.dismiss();
                            sharedPreferencesManager.saveSpBoolean(SharedPreferencesManager.SP_SIGNED, true);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Koneksi Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            }catch (ApiException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                        }else{
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn() {
        Intent googleSignIn = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(googleSignIn, RC_SIGN_IN);
    }
}
