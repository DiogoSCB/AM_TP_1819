package amov1819.reversiisec.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

import amov1819.reversiisec.R;

public class Board extends View {

    private LinearLayout gameBoard;

    public Board(Context context) {
        super(context);
        gameBoard = findViewById(R.id.gameBoard);
    }

    public void displayBoard() {
        ImageView square = new ImageView(this.getContext());
        square.setBackgroundResource(R.drawable.square);

        List<String> letras = Arrays.asList("A","B","C","D","E","F","H");
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8);

        gameBoard.addView(square);
        for(int i=0; i!=letras.size(); i++){
            gameBoard.addView(square);
        }

    }
}
