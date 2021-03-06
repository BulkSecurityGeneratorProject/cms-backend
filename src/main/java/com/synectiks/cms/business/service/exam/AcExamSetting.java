package com.synectiks.cms.business.service.exam;

import com.synectiks.cms.domain.enumeration.SemesterEnum;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;

public  class AcExamSetting implements Serializable, Comparable<AcExamSetting>{
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final long serialVersionUID = 1L;
    private Long id;
    private String examName;
    private String departmnt;
    private String bctch;
    private String sectn;
    private String brnch;
    private String sbjct;
    private String action;
    private String subject;
    private Date examDate;
    private String st;
    private String ed;
    private String subExamDate;
    private Long countvalue;
    private Integer total;
    private Integer passing;
    private String startTime;
    private String endTime;
    private SemesterEnum semester;

    public AcExamSetting() { }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDepartmnt() {
        return departmnt;
    }

    public void setDepartmnt(String departmnt) {
        this.departmnt = departmnt;
    }

    public String getBctch() {
        return bctch;
    }

    public void setBctch(String bctch) {
        this.bctch = bctch;
    }

    public String getSectn() {
        return sectn;
    }

    public void setSectn(String sectn) {
        this.sectn = sectn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getBrnch() {
        return brnch;
    }

    public void setBrnch(String brnch) {
        this.brnch = brnch;
    }

    public String getSbjct() {
        return sbjct;
    }

    public void setSbjct(String sbjct) {
        this.sbjct = sbjct;
    }

    public String getSubExamDate() {return subExamDate;}

    public void setSubExamDate(String subExamDate) {this.subExamDate = subExamDate;}

    public Long getCountvalue() {
        return countvalue;
    }

    public void setCountvalue(Long countvalue) {
        this.countvalue = countvalue;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPassing() {
        return passing;
    }

    public void setPassing(Integer passing) {
        this.passing = passing;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcExamSetting that = (AcExamSetting) o;
        return examName.equals(that.examName) &&
            departmnt.equals(that.departmnt) && bctch.equals(that.bctch)
            && countvalue.equals((this.countvalue));
    }


    @Override
    public int hashCode() {
        return Objects.hash(examName, departmnt, bctch);
    }


    public AcExamSetting(Long id, String examName, String departmnt, String bctch, String sectn, String brnch, String sbjct, String action, Date examDate, String st, String ed, String subExamDate, Long countvalue, Integer total, Integer passing, String startTime, String endTime, SemesterEnum semester) {
        this.id = id;
        this.examName = examName;
        this.departmnt = departmnt;
        this.bctch = bctch;
        this.sectn = sectn;
        this.brnch = brnch;
        this.sbjct = sbjct;
        this.action = action;
        this.subject = subject;
        this.examDate = examDate;
        this.st = st;
        this.ed = ed;
        this.subExamDate = subExamDate;
        this.countvalue = countvalue;
        this.total = total;
        this.passing = passing;
        this.startTime = startTime;
        this.endTime = endTime;
        this.semester = semester;
    }

    @Override
    public int compareTo(AcExamSetting u) {
        if (getExamDate() == null || u.getExamDate() == null) {
            return 0;
        }
        return getExamDate().compareTo(u.getExamDate());
    }

    public AcExamSetting merge(AcExamSetting other) {
        assert (this.equals(other));

        String sub1 =this.sbjct+","+other.sbjct;
//        String[] strWords = str.split("\\,+");
//        Arrays.sort(strWords);
//        LinkedHashSet<String> lhSetWords
//            = new LinkedHashSet<String>( Arrays.asList(strWords) );
//
//        StringBuilder sbTemp = new StringBuilder();
//        int index = 0;
//
//        for(String s : lhSetWords){
//
//            if(index > 0)
//                sbTemp.append(",");
//
//            sbTemp.append(s);
//            index++;
//        }
//        str = sbTemp.toString();


        String sec1 =this.sectn+","+other.sectn;
        String[] strWords1 = sec1.split("\\,+");
        Arrays.sort(strWords1);
        LinkedHashSet<String> lhSetWords1
            = new LinkedHashSet<String>( Arrays.asList(strWords1) );

        StringBuilder sbTemp1 = new StringBuilder();
        int index1 = 0;

        for(String s1 : lhSetWords1){

            if(index1 > 0)
                sbTemp1.append(",");

            sbTemp1.append(s1);
            index1++;
        }
        sec1 = sbTemp1.toString();

        String ids =this.action+","+other.action;
//        String[] idsWords = ids.split("\\,+");
//        Arrays.sort(idsWords);
//        LinkedHashSet<String> idslhSetWords
//            = new LinkedHashSet<String>( Arrays.asList(idsWords) );
//
//        StringBuilder idsbTemp = new StringBuilder();
//        int idsindex = 0;
//
//        for(String sids : idslhSetWords){
//
//            if(idsindex > 0)
//                idsbTemp.append(",");
//
//            idsbTemp.append(sids);
//            idsindex++;
//        }
//        ids = idsbTemp.toString();

        String strSubExam =this.subExamDate+","+other.subExamDate;
        String[] strSubExamWords = strSubExam.split("\\,+");
        Arrays.sort(strSubExamWords);
        LinkedHashSet<String> lhSubExamSetWords
            = new LinkedHashSet<String>( Arrays.asList(strSubExamWords) );

        StringBuilder sbstrSubExamWordsTemp = new StringBuilder();
        int indexstrSubExamWords = 0;

        for(String sstrSubExam : lhSubExamSetWords){

            if(indexstrSubExamWords > 0)
                sbstrSubExamWordsTemp.append(",");

            sbstrSubExamWordsTemp.append(sstrSubExam);
            indexstrSubExamWords++;
        }
        strSubExam = sbstrSubExamWordsTemp.toString();
        return new AcExamSetting(
            this.id,
            this.examName,
            this.departmnt,
            this.brnch,
            this.bctch,
            this.sectn=sec1,
            this.sbjct=sub1,
            this.action=ids,
            this.examDate,
            this.st=sdf.format(this.examDate),
            this.ed=sdf.format(other.examDate),
            this.subExamDate=strSubExam,
            this.countvalue=countvalue,
            this.total = total,
            this.passing = passing,
            this.startTime = startTime,
            this.endTime = endTime,
            this.semester = semester

            //=sdf.format(this.examDate)+","+sdf.format(other.examDate),



        );
    }


}
