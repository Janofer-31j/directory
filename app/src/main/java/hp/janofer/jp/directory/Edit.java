package hp.janofer.jp.directory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

public class Edit extends AppCompatActivity {
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        searchView = (SearchView) findViewById(R.id.btnsearch);
        searchView.setIconifiedByDefault(false);


    }
}
