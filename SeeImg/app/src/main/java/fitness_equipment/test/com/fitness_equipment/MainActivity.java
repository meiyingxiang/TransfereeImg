package fitness_equipment.test.com.fitness_equipment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.hitomi.tilibrary.style.index.NumberIndexIndicator;
import com.hitomi.tilibrary.style.progress.ProgressBarIndicator;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
//import com.vansz.picassoimageloader.PicassoImageLoader;
import com.vansz.universalimageloader.UniversalImageLoader;

public class MainActivity extends AppCompatActivity {

    protected static final int READ_EXTERNAL_STORAGE = 100;
    protected static final int WRITE_EXTERNAL_STORAGE = 101;

    protected Transferee transferee;
    protected TransferConfig config;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).init();
        transferee = Transferee.getDefault(this);

        setContentView(R.layout.activity_main_layout);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferee.apply(TransferConfig.build()
                        .setSourceUrlList(SourceConfig.getOriginalSourceGroup())
                        .setProgressIndicator(new ProgressBarIndicator())
                        .setIndexIndicator(new NumberIndexIndicator())
                        .setImageLoader(UniversalImageLoader.with(getApplicationContext()))
                        .enableHideThumb(false)
                        .create()
                ).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        transferee.destroy();
    }
}
