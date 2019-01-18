package cn.lsu.chicken.room.entity;

import lombok.Data;

import java.util.Arrays;

@Data
public class ImageInfo {

    public byte[] rgbData;
    public int width;
    public int height;

    @Override
    public String toString() {
        return "ImageInfo{" +
                "rgbData=" + Arrays.toString(rgbData) +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

}
