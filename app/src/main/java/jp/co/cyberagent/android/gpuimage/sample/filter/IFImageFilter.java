package jp.co.cyberagent.android.gpuimage.sample.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.OpenGlUtils;
import jp.co.cyberagent.android.gpuimage.sample.other.PathUtil;

import java.util.ArrayList;

/**
 * Created by sam on 14-8-9.
 */
public class IFImageFilter extends GPUImageFilter {
    private int filterInputTextureUniform2;
    private int filterInputTextureUniform3;
    private int filterInputTextureUniform4;
    private int filterInputTextureUniform5;
    private int filterInputTextureUniform6;
    public int filterSourceTexture2 = OpenGlUtils.NO_TEXTURE;
    public int filterSourceTexture3 = OpenGlUtils.NO_TEXTURE;
    public int filterSourceTexture4 = OpenGlUtils.NO_TEXTURE;
    public int filterSourceTexture5 = OpenGlUtils.NO_TEXTURE;
    public int filterSourceTexture6 = OpenGlUtils.NO_TEXTURE;
    private ArrayList<String> mResFiles;


    public IFImageFilter(String fragmentShaderString) {
        super(NO_FILTER_VERTEX_SHADER, fragmentShaderString);
    }

    public void onInit() {
        super.onInit();
        filterInputTextureUniform2 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture2");
        filterInputTextureUniform3 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture3");
        filterInputTextureUniform4 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture4");
        filterInputTextureUniform5 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture5");
        filterInputTextureUniform6 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture6");

        initInputTexture();
    }

    public void onDestroy() {
        super.onDestroy();

        if (filterSourceTexture2 != OpenGlUtils.NO_TEXTURE) {
            int[] arrayOfInt1 = new int[1];
            arrayOfInt1[0] = this.filterSourceTexture2;
            GLES20.glDeleteTextures(1, arrayOfInt1, 0);
            this.filterSourceTexture2 = OpenGlUtils.NO_TEXTURE;
        }

        if (filterSourceTexture3 != OpenGlUtils.NO_TEXTURE) {
            int[] arrayOfInt2 = new int[1];
            arrayOfInt2[0] = this.filterSourceTexture3;
            GLES20.glDeleteTextures(1, arrayOfInt2, 0);
            this.filterSourceTexture3 = OpenGlUtils.NO_TEXTURE;
        }

        if (filterSourceTexture4 != OpenGlUtils.NO_TEXTURE) {
            int[] arrayOfInt3 = new int[1];
            arrayOfInt3[0] = this.filterSourceTexture4;
            GLES20.glDeleteTextures(1, arrayOfInt3, 0);
            this.filterSourceTexture4 = OpenGlUtils.NO_TEXTURE;
        }

        if (filterSourceTexture5 != OpenGlUtils.NO_TEXTURE) {
            int[] arrayOfInt4 = new int[1];
            arrayOfInt4[0] = this.filterSourceTexture5;
            GLES20.glDeleteTextures(1, arrayOfInt4, 0);
            this.filterSourceTexture5 = OpenGlUtils.NO_TEXTURE;
        }

        if (filterSourceTexture6 != OpenGlUtils.NO_TEXTURE) {
            int[] arrayOfInt5 = new int[1];
            arrayOfInt5[0] = this.filterSourceTexture6;
            GLES20.glDeleteTextures(1, arrayOfInt5, 0);
            this.filterSourceTexture6 = OpenGlUtils.NO_TEXTURE;
        }

    }

    @Override
    protected void onDrawArraysPre() {
        super.onDrawArraysPre();

        if (filterSourceTexture2 != OpenGlUtils.NO_TEXTURE) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE3);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, filterSourceTexture2);
            GLES20.glUniform1i(filterInputTextureUniform2, 3);
        }

        if (filterSourceTexture3 != OpenGlUtils.NO_TEXTURE) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE4);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, filterSourceTexture3);
            GLES20.glUniform1i(filterInputTextureUniform3, 4);
        }

        if (filterSourceTexture4 != OpenGlUtils.NO_TEXTURE) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE5);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, filterSourceTexture4);
            GLES20.glUniform1i(filterInputTextureUniform4, 5);
        }

        if (filterSourceTexture5 != OpenGlUtils.NO_TEXTURE) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE6);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, filterSourceTexture5);
            GLES20.glUniform1i(filterInputTextureUniform5, 6);
        }

        if (filterSourceTexture6 != OpenGlUtils.NO_TEXTURE) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE7);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, filterSourceTexture6);
            GLES20.glUniform1i(filterInputTextureUniform6, 7);
        }

    }

    public void addInputTexture(String name) {
        if (mResFiles == null) {
            mResFiles = new ArrayList<>();
        }
        mResFiles.add(PathUtil.getPath(name));
    }

    public void initInputTexture() {
        if (mResFiles == null) {
            return;
        }
        if (mResFiles.size() > 0) {
            runOnDraw(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = BitmapFactory.decodeFile(mResFiles.get(0));
                    filterSourceTexture2 = OpenGlUtils.loadTexture(b, OpenGlUtils.NO_TEXTURE, true);
                }
            });
        }

        if (mResFiles.size() > 1) {
            runOnDraw(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = BitmapFactory.decodeFile(mResFiles.get(1));
                    filterSourceTexture3 = OpenGlUtils.loadTexture(b, OpenGlUtils.NO_TEXTURE, true);
                }
            });
        }

        if (mResFiles.size() > 2) {
            runOnDraw(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = BitmapFactory.decodeFile(mResFiles.get(2));
                    filterSourceTexture4 = OpenGlUtils.loadTexture(b, OpenGlUtils.NO_TEXTURE, true);
                }
            });
        }

        if (mResFiles.size() > 3) {
            runOnDraw(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = BitmapFactory.decodeFile(mResFiles.get(3));
                    filterSourceTexture5 = OpenGlUtils.loadTexture(b, OpenGlUtils.NO_TEXTURE, true);
                }
            });
        }

        if (mResFiles.size() > 4) {
            runOnDraw(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = BitmapFactory.decodeFile(mResFiles.get(4));
                    filterSourceTexture6 = OpenGlUtils.loadTexture(b, OpenGlUtils.NO_TEXTURE, true);
                }
            });
        }
    }


}
