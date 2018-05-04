package nekono.inno.stepikapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//https://stepik.org/api/search-results?query=kotlin
//https://stackoverflow.com/questions/44419345/put-2-columns-of-recycler-view-every-row - put GridLayoutManager
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
