package hp.janofer.jp.directory;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create extends AppCompatActivity {
    Button btnadd, btncancel;
    TextView name, dept, qualification, desig, phn, mail;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        name=findViewById(R.id.name);
        dept=findViewById(R.id.dept);
        qualification=findViewById(R.id.quali);
        desig=findViewById(R.id.desig);
        phn=findViewById(R.id.phone);
        mail=findViewById(R.id.mail);

        btnadd=(Button)findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Staff staff=new Staff();
                staff.setName(name.getText().toString());
                staff.setDepartment(dept.getText().toString());
                staff.setDesignation(desig.getText().toString());
                staff.setMail(mail.getText().toString());
                staff.setQualification(qualification.getText().toString());
                staff.setPhn(phn.getText().toString());

                mRef.child("users").push().setValue(staff).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Created successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Addeditdel.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Creation Failed!"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
        btncancel=(Button)findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Addeditdel.class);
                startActivity(intent);

            }

        });
    }
}
