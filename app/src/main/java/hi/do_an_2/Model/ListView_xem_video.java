package hi.do_an_2.Model;

public class ListView_xem_video {
    public int imgVideo;
    public String tvTenVideo;

    public ListView_xem_video(int imageResourceId, String tenVideo) {
        this.imgVideo = imageResourceId;
        this.tvTenVideo = tenVideo;
    }

    public int getImgVideo() {
        return imgVideo;
    }

    public String getTvTenVideo() {
        return tvTenVideo;
    }

}
