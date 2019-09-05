package hp.janofer.jp.directory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListStaffs extends AppCompatActivity {

    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("users");

    RecyclerView rvListStaffs;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    ProgressDialog progressDialog;

    List<Staff> mBinList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_staffs);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Staffs !");
        progressDialog.show();

        rvListStaffs=findViewById(R.id.rvListStaffs);
        layoutManager=new LinearLayoutManager(this);
        rvListStaffs.setLayoutManager(layoutManager);

        adapter=new StaffListAdapter(mBinList);
        rvListStaffs.setAdapter(adapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("FIREBASE READ", "Datasnapshot read.");
                mBinList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Staff bin=ds.getValue(Staff.class);
                    mBinList.add(bin);
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
