package com.example.story;

public class StoryModel {

  private String stroytitle;
    private String stroy;

    public StoryModel(String stroytitle, String stroy) {
        this.stroytitle = stroytitle;
        this.stroy = stroy;
    }

    public String getStroytitle() {
        return stroytitle;
    }

    public void setStroytitle(String stroytitle) {
        this.stroytitle = stroytitle;
    }

    public String getStroy() {
        return stroy;
    }

    public void setStroy(String stroy) {
        this.stroy = stroy;
    }
}
