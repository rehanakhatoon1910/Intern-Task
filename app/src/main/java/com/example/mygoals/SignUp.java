package com.example.mygoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    EditText fullname, emailid, phone, pass, confpass;
    Button btnsignup;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("SIGN UP");

        firebaseAuth = FirebaseAuth.getInstance();
        fullname = findViewById(R.id.full_name);
        emailid = findViewById(R.id.email_id);
        phone = findViewById(R.id.phone_no);
        pass = findViewById(R.id.pass);
        confpass = findViewById(R.id.conf_pass);
        btnsignup = findViewById(R.id.btn_sign_up);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullname.getText().toString();
                String email = emailid.getText().toString();
                String phoneno = phone.getText().toString();
                String pwd = pass.getText().toString();
                String confpwd = confpass.getText().toString();

                if (name.isEmpty()) {
                    fullname.setError("Please enter full name");
                    fullname.requestFocus();
                } else if (email.isEmpty()) {
                    emailid.setError("Please enter email id");
                    emailid.requestFocus();
                } else if (phoneno.isEmpty()) {
                    phone.setError("Please enter phone no");
                    phone.requestFocus();
                } else if (pwd.isEmpty()) {
                    pass.setError("Please enter password");
                    pass.requestFocus();
                } else if (confpwd.isEmpty()) {
                    confpass.setError("Please enter confirm password");
                    confpass.requestFocus();
                } else if (name.isEmpty() && email.isEmpty() && phoneno.isEmpty() && pwd.isEmpty() && confpwd.isEmpty()) {
                    Toast.makeText(SignUp.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(name.isEmpty() && email.isEmpty() && phoneno.isEmpty() && pwd.isEmpty() && confpwd.isEmpty()))
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Map<String, Object> user = new HashMap<>();
                                user.put("Name", name);
                                user.put("Email", email);
                                user.put("Mobile", phoneno);
                                user.put("Password", pwd);
                                String userID = firebaseAuth.getCurrentUser().getUid();
                                CollectionReference usersCollection = FirebaseFirestore.getInstance().collection("users");
                                usersCollection.document(userID).set(user);
                                Toast.makeText(SignUp.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, Login.class));
                                startActivity(new Intent(SignUp.this, TabActivity.class));
                            } else {

                                Toast.makeText(SignUp.this, "Registration unsuccessful " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            }
        });
    }
}