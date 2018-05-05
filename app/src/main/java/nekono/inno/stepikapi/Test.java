package nekono.inno.stepikapi;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class Test extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
//        Picasso.get().load("https://avatars3.githubusercontent.com/u/13501372?s=400&u=33617e015c5dbd67dd1c615424ba89a8d9fe7d34&v=4")
//                .into((ImageView) findViewById(R.id.imageView));

//        Glide.with(this)
//                .load("https://avatars3.githubusercontent.com/u/13501372?s=400&u=33617e015c5dbd67dd1c615424ba89a8d9fe7d34&v=4")
//                .into((ImageView) findViewById(R.id.imageView));
    }
}
