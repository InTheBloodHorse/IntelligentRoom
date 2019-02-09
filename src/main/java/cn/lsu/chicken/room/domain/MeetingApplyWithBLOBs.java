package cn.lsu.chicken.room.domain;

public class MeetingApplyWithBLOBs extends MeetingApply {
    private String intro;

    private String documentList;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getDocumentList() {
        return documentList;
    }

    public void setDocumentList(String documentList) {
        this.documentList = documentList == null ? null : documentList.trim();
    }
}