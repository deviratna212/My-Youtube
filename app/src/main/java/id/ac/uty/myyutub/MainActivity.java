package id.ac.uty.myyutub;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String DEVELOPER_KEY = "AIzaSyA8SEoLYS61KkOGlTsQmGRj7kTyZhZXh4U";
    private static final  String VIDEO_ID = "qeh2zscThVw";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    YouTubePlayerFragment myYoutubePlayerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myYoutubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.myyutub);
        myYoutubePlayerFragment.initialize(DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {

        if (errorReason.isUserRecoverableError()){
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }
        else {
            String errorMessage = String.format("There was error", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored){
            player.cueVideo(VIDEO_ID);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RECOVERY_DIALOG_REQUEST){
            getYoutubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return (YouTubePlayerView) findViewById(R.id.myyutub);
    }
}
