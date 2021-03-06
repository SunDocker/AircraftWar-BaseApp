package cn.edu.hit.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

import cn.edu.hit.R;
import cn.edu.hit.activity.MainActivity;
import cn.edu.hit.application.ImageManager;

public class GameBackground extends View {
    Context context;

    /**
     * 图片,
     * null 表示未设置
     */
    protected Bitmap image = null;

    /**
     * x 轴长度，根据图片尺寸获得
     * -1 表示未设置
     */
    protected int width = -1;

    /**
     * y 轴长度，根据图片尺寸获得
     * -1 表示未设置
     */
    protected int height = -1;

    public void setBackGroundTop(int backGroundTop) {
        this.backGroundTop = backGroundTop;
    }

    private int backGroundTop;

    public GameBackground(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap background = getImage();
        canvas.drawBitmap(background, 0, backGroundTop, new Paint());
        if (backGroundTop == 0) {
            return;
        }
        Bitmap background0 = Bitmap.createBitmap(background,
                0,
                MainActivity.WINDOW_HEIGHT - backGroundTop,
                MainActivity.WINDOW_WIDTH,
                backGroundTop);

        canvas.drawBitmap(background0, 0, 0, new Paint());

    }

    public Bitmap getImage() {
        if (image == null) {
            image = ImageManager.get(this);
        }
        return imageScale(image, MainActivity.WINDOW_WIDTH, MainActivity.WINDOW_HEIGHT);
    }

    /**
     * 调整图片大小
     *
     * @param bitmap 源
     * @param dst_w  输出宽度
     * @param dst_h  输出高度
     * @return
     */
    private Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
        return dstbmp;
    }

    public int getImageWidth() {
        if (width == -1) {
            // 若未设置，则查询图片宽度并设置
            width = ImageManager.get(this).getWidth();
        }
        return width;
    }

    public int getImageHeight() {
        if (height == -1) {
            // 若未设置，则查询图片高度并设置
            height = ImageManager.get(this).getHeight();
        }
        return height;
    }
}
