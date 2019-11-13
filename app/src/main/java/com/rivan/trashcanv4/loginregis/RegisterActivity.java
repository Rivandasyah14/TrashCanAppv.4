package com.rivan.trashcanv4.loginregis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
import com.rivan.trashcanv4.tipesampah.TipeSampah;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tnRegis, regisGoogle;
    private TextView signIn;

    private static final int RC_SIGN_UP = 123;
    private static final String TAG = "test";
    GoogleSignInClient mGoogleSignUpClient;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signIn = findViewById(R.id.tv_signin);
        signIn.setOnClickListener(this);
        tnRegis = findViewById(R.id.tn_regis);
        tnRegis.setOnClickListener(this);
        regisGoogle = findViewById(R.id.tn_regisgoogle);
        regisGoogle.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestEmail()
                .build();
        mGoogleSignUpClient = GoogleSignIn.getClient(this, gso);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tn_regisgoogle:
                signUpGoogle();
            case R.id.tn_regis:
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);
                finish();
                break;
            case R.id.tv_signin:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
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
        if (requestCode == RC_SIGN_UP){
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
                            Toast.makeText(RegisterActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                        }else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authention failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signUpGoogle() {
        Intent googleSignUp = mGoogleSignUpClient.getSignInIntent();
        startActivityForResult(googleSignUp, RC_SIGN_UP);
    }
}
