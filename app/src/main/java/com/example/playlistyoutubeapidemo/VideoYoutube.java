package com.example.playlistyoutubeapidemo;

public class VideoYoutube {
    private String title;
    private String idVideo;
    private String linkThumb;

    public VideoYoutube(String title, String idVideo, String linkThumb) {
        this.title = title;
        this.idVideo = idVideo;
        this.linkThumb = linkThumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getLinkThumb() {
        return linkThumb;
    }

    public void setLinkThumb(String linkThumb) {
        this.linkThumb = linkThumb;
    }
}
