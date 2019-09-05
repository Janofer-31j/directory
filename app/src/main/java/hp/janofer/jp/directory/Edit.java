package hp.janofer.jp.directory;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Edit extends AppCompatActivity {
    SearchView searchView;

    DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        searchView = (SearchView) findViewById(R.id.btnsearch);
        searchView.setIconifiedByDefault(false);

        //Select desirable staff obj to edit
        Staff staffAfterEdit=new Staff();
        staffAfterEdit.setPhn("12345");
        staffAfterEdit.setQualification("ME");
        staffAfterEdit.setMail("123@nec.edu.in");
        staffAfterEdit.setDesignation("AP(SG)");
        staffAfterEdit.setDepartment("CSE");
        staffAfterEdit.setName("Naskat.J");

        String editBy="mail";   //to edit by mail....select name/mail/phn/..
        String value="123@nec.edu.in"; //replace with the corresponding value of editBy
        edit(editBy, value, staffAfterEdit);

    }

    private void edit(String editBy, String value, final Staff staffAfterEdit) {
        Query query = mRef.child("users").orderByChild(editBy).equalTo(value);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    ds.getRef().child("name").setValue(staffAfterEdit.getName());
                    ds.getRef().child("department").setValue(staffAfterEdit.getDepartment());
                    ds.getRef().child("designation").setValue(staffAfterEdit.getDesignation());
                    ds.getRef().child("mail").setValue(staffAfterEdit.getMail());
                    ds.getRef().child("phn").setValue(staffAfterEdit.getPhn());
                    ds.getRef().child("qualification").setValue(staffAfterEdit.getQualification());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Failed "+databaseError.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
